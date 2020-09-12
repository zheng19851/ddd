package com.runssnail.ddd.pipeline.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 阶段
 *
 * @author zhengwei
 */
public abstract class BasePhase implements Phase {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 唯一标识
     */
    protected String phaseId;

    /**
     * 步骤唯一标识
     */
    protected List<String> steps;

    /**
     * 是否支持并行
     */
    protected boolean parallel;

    /**
     * 超时，单位毫秒
     */
    protected long timeout;

    /**
     * 异常处理器
     */
    protected PhaseErrorHandler phaseErrorHandler;

    /**
     * 线程池
     */
    protected ExecutorService executor;

    /**
     * 步骤仓储
     */
    protected StepRepository stepRepository;

    /**
     * 拦截器
     */
    private List<Interceptor> interceptors;

    /**
     * Default constructor
     */
    public BasePhase() {
    }

    /**
     * 创建阶段
     *
     * @param phaseId        阶段唯一标识
     * @param steps          步骤唯一标识
     * @param parallel       是否并行执行
     * @param stepRepository 步骤仓储
     */
    public BasePhase(String phaseId, List<String> steps, boolean parallel, StepRepository stepRepository) {
        Validate.notBlank(phaseId);
        Validate.notEmpty(steps);
        Validate.notNull(stepRepository);
        this.phaseId = phaseId;
        this.steps = steps;
        this.parallel = parallel;
        this.stepRepository = stepRepository;
    }

    /**
     * @param exchange
     */
    @Override
    public void execute(Exchange exchange) throws ExecuteException {
        log.info("execute phase start {}", this.phaseId);
        long start = System.currentTimeMillis();
        try {
            beforeExecuteIfNecessary(exchange);
            doExecute(exchange);
            afterExecuteIfNecessary(exchange);
        } finally {
            log.info("execute phase start {}, cost {} ms", this.phaseId, (System.currentTimeMillis() - start));
        }

    }

    protected void afterExecuteIfNecessary(Exchange exchange) {
        if (CollectionUtils.isNotEmpty(this.interceptors)) {
            for (Interceptor interceptor : this.interceptors) {
                interceptor.beforeExecute(exchange);
            }
        }
    }

    protected void beforeExecuteIfNecessary(Exchange exchange) {
        if (CollectionUtils.isNotEmpty(this.interceptors)) {
            for (Interceptor interceptor : this.interceptors) {
                interceptor.afterExecute(exchange);
            }
        }
    }

    protected void doExecute(Exchange exchange) throws ExecuteException {
        if (this.parallel) {
            doParallel(exchange);
        } else {
            doSerial(exchange);
        }
    }

    /**
     * 串行执行
     *
     * @param exchange
     */
    private void doSerial(Exchange exchange) throws ExecuteException {
        List<Step> steps = stepRepository.getSteps(this.steps);
        for (Step step : steps) {
            step.execute(exchange);
        }
    }

    /**
     * 并行执行
     *
     * @param exchange
     * @throws ExecuteException
     */
    private void doParallel(Exchange exchange) throws ExecuteException {
        final String pipelineId = exchange.getPipelineId();
        List<Step> steps = stepRepository.getSteps(this.steps);
        Collection<Callable<Void>> tasks = new ArrayList<>(steps.size());
        for (Step step : steps) {
            tasks.add(() -> {
                step.execute(exchange);
                // 不需要返回什么数据
                return null;
            });
        }

        List<Future<Void>> futures = null;
        try {
            if (timeout > 0) {
                futures = this.executor.invokeAll(tasks, timeout, TimeUnit.MILLISECONDS);
            } else {
                futures = this.executor.invokeAll(tasks);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.phaseErrorHandler.onException(this, exchange, e);
        } catch (Exception e) {
            this.phaseErrorHandler.onException(this, exchange, e);
        }

        if (CollectionUtils.isNotEmpty(futures)) {
            for (int i = 0; i < futures.size(); i++) {
                Future<Void> future = futures.get(i);
                final Step step = steps.get(i);
                final String stepId = step.getStepId();
                if (future.isDone() && !future.isCancelled()) {
                    String stepName = step.getClass().getCanonicalName();
                    try {
                        future.get();
                        log.debug("execute step end, pipeline:{}, phase:{}, step:{}({}), result:{}", pipelineId, this.phaseId, stepId,
                                stepName, exchange.getBody());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        step.getStepErrorHandler().onException(this.phaseId, step, exchange, e);
                    } catch (Exception e) {
                        step.getStepErrorHandler().onException(this.phaseId, step, exchange, e);
                    }
                } else {
                    step.getStepErrorHandler().onException(this.phaseId, step, exchange, "step execute canceled");
                }
            }
        }

    }

    @Override
    public void init() {
        log.info("phase init start {}", this.phaseId);
        doInit();
        log.info("phase init end {}", this.phaseId);
    }

    protected void doInit() {
        initErrorHandler();
    }

    protected void initErrorHandler() {
        if (this.phaseErrorHandler == null) {
            this.phaseErrorHandler = new DefaultPhaseErrorHandler();
        }
    }

    @Override
    public void close() {
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }

    /**
     * @return
     */
    @Override
    public String getPhaseId() {
        return this.phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    @Override
    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    @Override
    public boolean isParallel() {
        return parallel;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    public StepRepository getStepRepository() {
        return stepRepository;
    }

    public void setStepRepository(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
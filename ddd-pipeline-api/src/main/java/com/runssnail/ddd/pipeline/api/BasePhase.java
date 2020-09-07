package com.runssnail.ddd.pipeline.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.exception.PhaseException;

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
     * 线程池
     */
    protected ExecutorService executor;

    /**
     *
     */
    protected StepManager stepManager;

    /**
     * 拦截器
     */
    private List<Interceptor> interceptors;

    /**
     * Default constructor
     */
    public BasePhase() {
    }

    public BasePhase(String phaseId, List<String> steps, boolean parallel, StepManager stepManager) {
        Validate.notBlank(phaseId);
        Validate.notEmpty(steps);
        Validate.notNull(stepManager);
        this.phaseId = phaseId;
        this.steps = steps;
        this.parallel = parallel;
        this.stepManager = stepManager;
    }

    /**
     * @param exchange
     */
    @Override
    public void execute(Exchange exchange) throws ExecuteException {
        beforeExecuteIfNecessary(exchange);
        doExecute(exchange);
        afterExecuteIfNecessary(exchange);
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

    private void doSerial(Exchange exchange) {
        List<Step> steps = stepManager.getSteps(this.steps);
        for (Step step : steps) {
            step.execute(exchange);
        }
    }

    private void doParallel(Exchange exchange) throws ExecuteException {

        List<Step> steps = stepManager.getSteps(this.steps);
        Collection<Callable<Void>> tasks = new ArrayList<>(steps.size());
        for (Step step : steps) {
            tasks.add(() -> {
                step.execute(exchange);
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
            //log.warn("Thread is Interrupted, exchange={}", exchange, e);
            String msg = "Thread is Interrupted, pipeline:" + exchange.getPipelineId() + ", phase:" + this.phaseId + ", steps:" + this.steps;
            throw new PhaseException(this.phaseId, msg, e);
        } catch (Exception e) {
            //log.warn("Thread execute error, exchange={}", exchange, e);
            String msg = "Thread execute error, pipeline:" + exchange.getPipelineId() + ", phase:" + this.phaseId + ", steps:" + this.steps;
            throw new PhaseException(this.phaseId, msg, e);
        }

        if (CollectionUtils.isNotEmpty(futures)) {
            for (int i = 0; i < futures.size(); i++) {
                Future<Void> future = futures.get(i);
                Step step = steps.get(i);
                //Exception stepException = null;
                if (future.isDone() && !future.isCancelled()) {
                    try {
                        future.get();
                        log.debug("pipeline:{}, phase:{}, step:{}, result:{}",
                                exchange.getPipelineId(), this.phaseId, step.getClass().getCanonicalName());
                    } catch (InterruptedException | ExecutionException e) {
//                        log.warn("execute step error, pipeline:{}, phase:{}, step:{}",
//                                exchange.getPipelineId(), this.phaseId, step.getClass().getCanonicalName(), e);
                        String msg = "execute step error, pipeline:" + exchange.getPipelineId() + ", phase:" + this.phaseId + ", step:" + step.getClass().getCanonicalName();
                        throw new PhaseException(this.phaseId, msg, e);
                    } catch (Exception e) {
                        String msg = "execute step error, pipeline:" + exchange.getPipelineId() + ", phase:" + this.phaseId + ", step:" + step.getClass().getCanonicalName();
//                        log.warn("execute step error, pipeline:{}, phase:{}, step:{}",
//                                exchange.getPipelineId(), this.phaseId, step.getClass().getCanonicalName(), e);
                        throw new PhaseException(this.phaseId, msg, e);
                    }
                } else {
                    //stepException = executorExc != null ? executorExc : new CancellationException("task is cancelled");
//                    log.warn("execute canceled, pipeline:{}, phase:{}", exchange.getPipelineId(), this.phaseId);
                    throw new PhaseException(this.phaseId, "task is cancelled, pipeline:" + exchange.getPipelineId() + ", phase:" + this.phaseId);
                }
            }
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

    public StepManager getStepManager() {
        return stepManager;
    }

    public void setStepManager(StepManager stepManager) {
        this.stepManager = stepManager;
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
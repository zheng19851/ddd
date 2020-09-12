package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * 步骤
 *
 * @author zhengwei
 */
public abstract class BaseStep implements Step {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 唯一标识
     */
    protected String stepId;

    /**
     * 超时时间，单位毫秒
     */
    protected long timeout;

    /**
     * 错误处理器
     */
    private StepErrorHandler stepErrorHandler;

    /**
     * 拦截器
     */
    private List<Interceptor> interceptors;

    /**
     * 中断策略
     */
    private TerminateStrategy terminateStrategy;

    /**
     * Default constructor
     */
    public BaseStep() {
    }

    /**
     * 创建
     *
     * @param stepId       步骤唯一标识
     * @param interceptors 拦截器
     */
    public BaseStep(String stepId, List<Interceptor> interceptors) {
        this(stepId);
        this.interceptors = interceptors;
    }

    /**
     * 创建
     *
     * @param stepId 步骤唯一标识
     */
    public BaseStep(String stepId) {
        Validate.notBlank(stepId, "stepId is required");
        this.stepId = stepId;
    }

    /**
     * @param exchange
     */
    @Override
    public void execute(Exchange exchange) {
        log.info("execute step start {}", this.stepId);
        long start = System.currentTimeMillis();
        try {
            beforeExecuteIfNecessary(exchange);
            log.info("execute step start(doExecute) {}", this.stepId);
            this.doExecute(exchange);
            log.info("execute step end(doExecute) {}", this.stepId);
            afterExecuteIfNecessary(exchange);
        } catch (Exception e) {
            this.stepErrorHandler.onException(exchange.getPipelineId(), this, exchange, e);
        } finally {
            log.info("execute step end {}, cost {} ms", this.stepId, (System.currentTimeMillis() - start));
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

    /**
     * @return
     */
    @Override
    public String getStepId() {
        return this.stepId;
    }

    /**
     * 子类实现
     *
     * @param exchange 数据交换
     * @throws ExecuteException
     */
    protected abstract void doExecute(Exchange exchange) throws ExecuteException;

    @Override
    public StepErrorHandler getStepErrorHandler() {
        return this.stepErrorHandler;
    }

    @Override
    public void init() {
        log.info("init step start {}", this.stepId);
        doInit();
        log.info("init step end {}", this.stepId);
    }

    protected void doInit() {
        initErrorHandler();
    }

    protected void initErrorHandler() {
        if (this.stepErrorHandler == null) {
            this.stepErrorHandler = new DefaultStepErrorHandler();
        }
    }

    @Override
    public void close() {

    }

    public void setStepErrorHandler(StepErrorHandler stepErrorHandler) {
        this.stepErrorHandler = stepErrorHandler;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public TerminateStrategy getTerminateStrategy() {
        return terminateStrategy;
    }

    @Override
    public void setTerminateStrategy(TerminateStrategy terminateStrategy) {
        this.terminateStrategy = terminateStrategy;
    }
}
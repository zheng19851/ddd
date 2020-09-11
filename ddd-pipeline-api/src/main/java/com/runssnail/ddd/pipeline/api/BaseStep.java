package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 步骤
 *
 * @author zhengwei
 */
public abstract class BaseStep implements Step {

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
    protected StepErrorHandler stepErrorHandler;

    /**
     * 拦截器
     */
    private List<Interceptor> interceptors;

    /**
     * Default constructor
     */
    public BaseStep() {
    }

    public BaseStep(String stepId, List<Interceptor> interceptors) {
        this.stepId = stepId;
        this.interceptors = interceptors;
    }

    public BaseStep(String stepId) {
        this.stepId = stepId;
    }

    /**
     * @param exchange
     */
    @Override
    public void execute(Exchange exchange) {
        try {
            beforeExecuteIfNecessary(exchange);
            this.doExecute(exchange);
            afterExecuteIfNecessary(exchange);
        } catch (Exception e) {
            this.stepErrorHandler.onException(exchange.getPipelineId(), this, exchange, e);
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
        initErrorHandler();
    }

    private void initErrorHandler() {
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
}
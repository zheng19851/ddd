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
    private String stepId;

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
        beforeExecuteIfNecessary(exchange);
        this.doExecute(exchange);
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

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
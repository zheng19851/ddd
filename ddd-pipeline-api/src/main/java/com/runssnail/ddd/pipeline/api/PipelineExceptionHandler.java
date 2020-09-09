package com.runssnail.ddd.pipeline.api;

/**
 * 流程异常统一处理器
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public interface PipelineExceptionHandler {

    /**
     * 处理异常
     *
     * @param context 上下文
     * @param t       异常
     */
    void onException(Exchange context, Throwable t);
}

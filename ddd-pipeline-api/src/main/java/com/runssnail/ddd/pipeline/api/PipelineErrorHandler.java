package com.runssnail.ddd.pipeline.api;

/**
 * 流程异常统一处理器
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public interface PipelineErrorHandler {

    void onException(Exchange context, Throwable t);
}

package com.runssnail.ddd.pipeline.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengwei
 * Created on 2020-09-08
 */
public class DefaultPipelineErrorHandler implements PipelineErrorHandler {
    private final static Logger log = LoggerFactory.getLogger(DefaultPipelineErrorHandler.class);

    @Override
    public void onException(Exchange exchange, Throwable t) {
        printLog(exchange, t);
        exchange.setThrowable(t);
        // todo 设置错误码
    }

    private void printLog(Exchange exchange, Throwable t) {
        // todo 打印日志
    }
}

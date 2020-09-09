package com.runssnail.ddd.pipeline.api;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.json.Json;

/**
 * 默认的异常处理器
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class DefaultPipelineExceptionHandler implements PipelineExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(DefaultPipelineExceptionHandler.class);

    private Json json;

    @Override
    public void onException(Exchange exchange, Throwable t) {
        printLog(exchange, t);
        exchange.setThrowable(t);
        // todo 设置错误码
    }

    private void printLog(Exchange exchange, Throwable t) {
        log.error(buildErrorMsg(exchange, t), t);
    }

    private String buildErrorMsg(Exchange exchange, Throwable t) {
        Throwable rootCause = ExceptionUtils.getRootCause(t);
        String simpleName = rootCause.getClass().getSimpleName();
        String rootCauseMessage = ExceptionUtils.getMessage(rootCause);

        String format = "execute pipeline error(%s), request=%s, result=%s, exceptionMsg=%s";
        String requestString = json.toJson(exchange.getRequestBody());
        String responseString = json.toJson(exchange.getResponseBody());
        String msg = String.format(format, simpleName, requestString, responseString, rootCauseMessage);
        return msg;
    }
}

package com.runssnail.ddd.pipeline.api;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.common.exception.BasicErrorCode;
import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.spi.Json;

/**
 * 默认的异常处理器
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class DefaultPipelineErrorHandler implements PipelineErrorHandler {
    private final static Logger log = LoggerFactory.getLogger(DefaultPipelineErrorHandler.class);

    private Json json;

    @Override
    public void onException(Exchange exchange, Throwable t) {
        printLog(exchange, t);
        exchange.setThrowable(t);
        setErrorCode(exchange, t);
    }

    private void setErrorCode(Exchange exchange, Throwable t) {
        exchange.setErrorMsg(BasicErrorCode.SYS_ERROR.getErrorMsg());
        exchange.setErrorCode(BasicErrorCode.SYS_ERROR.getErrorCode());

        Throwable rootCause = ExceptionUtils.getRootCause(t);
        if (rootCause instanceof IllegalArgumentException) {
            exchange.setErrorCode(BasicErrorCode.PARAMS_ERROR.getErrorCode());
            exchange.setErrorMsg(rootCause.getMessage());
        } else if (rootCause instanceof ExecuteException) {
            ExecuteException e = (ExecuteException) rootCause;
            exchange.setErrorCode(e.getErrorCode());
            exchange.setErrorMsg(e.getErrorMsg());
        } else if (rootCause instanceof TimeoutException) {
            exchange.setErrorCode(BasicErrorCode.TIMEOUT.getErrorCode());
            exchange.setErrorMsg(BasicErrorCode.TIMEOUT.getErrorMsg());
        } else if (rootCause instanceof SocketTimeoutException) {
            exchange.setErrorCode(BasicErrorCode.SOCKET_TIMEOUT.getErrorCode());
            exchange.setErrorMsg(BasicErrorCode.SOCKET_TIMEOUT.getErrorMsg());
        } else {
            exchange.setErrorCode(BasicErrorCode.SYS_ERROR.getErrorCode());
            exchange.setErrorMsg(BasicErrorCode.SYS_ERROR.getErrorMsg());
        }

    }

    private void printLog(Exchange exchange, Throwable t) {
        log.error(buildErrorMsg(exchange, t), t);
    }

    private String buildErrorMsg(Exchange exchange, Throwable t) {
        Throwable rootCause = ExceptionUtils.getRootCause(t);
        String simpleName = rootCause.getClass().getSimpleName();
        String rootCauseMessage = ExceptionUtils.getMessage(rootCause);

        String format = "execute pipeline error(%s), result=%s, exceptionMsg=%s";
        String body = json.toJson(exchange.getBody());
        String msg = String.format(format, simpleName, body, rootCauseMessage);
        return msg;
    }

    public Json getJson() {
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }
}

package com.runssnail.ddd.pipeline.api.terminate;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.Exchange;
import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 打印日志
 * <p>
 * terminate.strategy=logging
 *
 * @author zhengwei
 * Created on 2020-09-12
 * @see com.runssnail.ddd.pipeline.api.constant.Constants#ATTRIBUTE_TERMINATE_STRATEGY
 */
public class LoggingTerminateStrategy implements TerminateStrategy {
    private static final Logger log = LoggerFactory.getLogger(LoggingTerminateStrategy.class);

    @Override
    public void onTerminate(Exchange exchange, Throwable t) throws ExecuteException {
        String pipelineId = exchange.getPipelineId();
        String exchangeId = exchange.getExchangeId();
        String exceptionMsg = ExceptionUtils.getRootCauseMessage(t);
        log.warn("execute pipeline fail pipelineId=[{}], exchangeId=[{}], exceptionMsg={}", pipelineId, exchangeId, exceptionMsg, t);
    }
}

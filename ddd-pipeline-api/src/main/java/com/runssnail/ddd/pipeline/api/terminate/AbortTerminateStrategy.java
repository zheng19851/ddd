package com.runssnail.ddd.pipeline.api.terminate;

import com.runssnail.ddd.pipeline.api.Exchange;
import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 默认直接终止
 * terminate.strategy=abort
 *
 * @author zhengwei
 * Created on 2020-09-12
 * @see com.runssnail.ddd.pipeline.api.constant.Constants#ATTRIBUTE_TERMINATE_STRATEGY
 */
public class AbortTerminateStrategy implements TerminateStrategy {

    @Override
    public void onTerminate(Exchange exchange, Throwable t) throws ExecuteException {

        if (t instanceof ExecuteException) {
            throw (ExecuteException) t;
        }
        throw new ExecuteException(t);
    }
}

package com.runssnail.ddd.pipeline.api.terminate;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 默认直接终止
 *
 * @author zhengwei
 * Created on 2020-09-12
 */
public class AbortTerminateStrategy implements TerminateStrategy {

    @Override
    public void onTerminate(Throwable t) throws ExecuteException {

        if (t instanceof ExecuteException) {
            throw (ExecuteException) t;
        }
        throw new ExecuteException(t);
    }
}

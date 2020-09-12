package com.runssnail.ddd.pipeline.api.terminate;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 终端策略
 *
 * @author zhengwei
 * Created on 2020-09-12
 */
public interface TerminateStrategy {

    /**
     * 处理中断
     *
     * @param t 异常
     * @throws ExecuteException
     */
    void onTerminate(Throwable t) throws ExecuteException;
}

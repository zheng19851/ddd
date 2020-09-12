package com.runssnail.ddd.pipeline.api.terminate;

import com.runssnail.ddd.pipeline.api.Exchange;
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
     * @param exchange 交换器上下文
     * @param t        异常
     * @throws ExecuteException
     */
    void onTerminate(Exchange exchange, Throwable t) throws ExecuteException;
}

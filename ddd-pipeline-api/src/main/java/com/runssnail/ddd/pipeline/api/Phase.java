package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 阶段执行对象
 *
 * @author zhengwei
 */
public interface Phase {

    /**
     * 执行
     *
     * @param exchange 上下文
     * @throws ExecuteException
     */
    void execute(Exchange exchange) throws ExecuteException;

    /**
     * 唯一标识
     *
     * @return 唯一标识
     */
    String getPhaseId();

    /**
     * 是否并行
     *
     * @return 是否并行
     */
    boolean isParallel();

}
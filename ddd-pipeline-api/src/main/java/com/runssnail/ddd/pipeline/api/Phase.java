package com.runssnail.ddd.pipeline.api;

import java.util.List;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 阶段执行对象
 *
 * @author zhengwei
 */
public interface Phase extends Lifecycle {

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
     * 步骤唯一标识
     *
     * @return
     */
    List<String> getSteps();

    /**
     * 是否并行
     *
     * @return 是否并行
     */
    boolean isParallel();

}
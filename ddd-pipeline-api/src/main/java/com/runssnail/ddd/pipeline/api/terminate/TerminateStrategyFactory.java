package com.runssnail.ddd.pipeline.api.terminate;

import com.runssnail.ddd.pipeline.api.exception.PipelineDefinitionException;

/**
 * 中断策略工厂
 *
 * @author zhengwei
 * Created on 2020-09-12
 */
public interface TerminateStrategyFactory {

    /**
     * 创建中断策略
     *
     * @param strategy 策略 {@link TerminateStrategyEnum}
     * @return 中断策略
     * @throws PipelineDefinitionException
     * @see TerminateStrategyEnum
     */
    TerminateStrategy create(String strategy) throws PipelineDefinitionException;
}

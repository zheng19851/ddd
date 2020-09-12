package com.runssnail.ddd.pipeline.api.terminate;

import com.runssnail.ddd.pipeline.api.exception.PipelineDefinitionException;

/**
 * 默认中断策略工厂
 *
 * @author zhengwei
 * Created on 2020-09-12
 */
public class DefaultTerminateStrategyFactory implements TerminateStrategyFactory {
    @Override
    public TerminateStrategy create(String strategy) throws PipelineDefinitionException {
        if ("abort".equalsIgnoreCase(strategy)) {
            return new AbortTerminateStrategy();
        }

        throw new PipelineDefinitionException("terminate strategy is unsupported '" + strategy + "'");
    }
}

package com.runssnail.ddd.pipeline.api.terminate;

import static com.runssnail.ddd.pipeline.api.terminate.TerminateStrategyEnum.ABORT;
import static com.runssnail.ddd.pipeline.api.terminate.TerminateStrategyEnum.LOGGING;
import static com.runssnail.ddd.pipeline.api.terminate.TerminateStrategyEnum.values;

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
        if (ABORT.name().equalsIgnoreCase(strategy)) {
            return new AbortTerminateStrategy();
        }

        if (LOGGING.name().equalsIgnoreCase(strategy)) {
            return new LoggingTerminateStrategy();
        }

        throw new PipelineDefinitionException("terminate strategy is unsupported '" + strategy + "', use " + values());
    }
}

package com.runssnail.ddd.pipeline.api;

import org.apache.commons.lang3.StringUtils;

import com.runssnail.ddd.pipeline.api.constant.Constants;
import com.runssnail.ddd.pipeline.api.exception.PipelineDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * 默认的流程执行对象工厂
 *
 * @author zhengwei
 */
public class DefaultPipelineFactory extends BaseFactory implements PipelineFactory {

    /**
     * 阶段仓储
     */
    private PhaseRepository phaseRepository;

    /**
     * Default constructor
     */
    public DefaultPipelineFactory() {
    }

    /**
     * @param pd 流程定义
     * @return
     */
    @Override
    public Pipeline create(PipelineDefinition pd) throws PipelineDefinitionException {
        DefaultPipeline defaultPipeline = new DefaultPipeline(pd.getPipelineId(), pd.getPhases(), this.phaseRepository);

        // 中断策略
        String strategy = pd.getAttribute(Constants.ATTRIBUTE_TERMINATE_STRATEGY);
        if (StringUtils.isNotBlank(strategy)) {
            TerminateStrategy terminateStrategy = terminateStrategyFactory.create(strategy);
            defaultPipeline.setTerminateStrategy(terminateStrategy);
        }

        return defaultPipeline;
    }

    public PhaseRepository getPhaseRepository() {
        return phaseRepository;
    }

    public void setPhaseRepository(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

}
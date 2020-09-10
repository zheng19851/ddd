package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.PipelineDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;

/**
 * 默认的流程执行对象工厂
 *
 * @author zhengwei
 */
public class DefaultPipelineFactory implements PipelineFactory {

    /**
     * Default constructor
     */
    public DefaultPipelineFactory() {
    }

    /**
     *
     */
    private PhaseRepository phaseRepository;

    /**
     * @param pd
     * @return
     */
    @Override
    public Pipeline create(PipelineDefinition pd) throws PipelineDefinitionException {
        DefaultPipeline defaultPipeline = new DefaultPipeline(pd.getPipelineId(), pd.getPhases(), this.phaseRepository);
        return defaultPipeline;
    }

    public PhaseRepository getPhaseRepository() {
        return phaseRepository;
    }

    public void setPhaseRepository(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }
}
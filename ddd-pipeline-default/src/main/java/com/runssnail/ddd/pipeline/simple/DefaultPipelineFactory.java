package com.runssnail.ddd.pipeline.simple;

import com.runssnail.ddd.pipeline.api.PhaseManager;
import com.runssnail.ddd.pipeline.api.Pipeline;
import com.runssnail.ddd.pipeline.api.PipelineFactory;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;

/**
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
    private PhaseManager phaseManager;

    /**
     * @param pd
     * @return
     */
    @Override
    public Pipeline create(PipelineDefinition pd) {
        DefaultPipeline defaultPipeline = new DefaultPipeline(pd.getPipelineId(), pd.getPhases(), this.phaseManager);
        return defaultPipeline;
    }

}
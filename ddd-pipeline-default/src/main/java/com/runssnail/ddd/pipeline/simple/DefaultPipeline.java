package com.runssnail.ddd.pipeline.simple;

import java.util.List;

import com.runssnail.ddd.pipeline.api.BasePipeline;
import com.runssnail.ddd.pipeline.api.PhaseRepository;

/**
 * 默认的流程执行对象
 *
 * @author zhengwei
 */
public class DefaultPipeline extends BasePipeline {

    public DefaultPipeline(String pipelineId, List<String> phases, PhaseRepository phaseRepository) {
        super(pipelineId, phases, phaseRepository);
    }
}
package com.runssnail.ddd.pipeline.api.metadata;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 流程定义
 *
 * @author zhengwei
 */
public class DefaultPipelineDefinition extends BaseDefinition implements PipelineDefinition {

    /**
     * 唯一标识
     */
    private String pipelineId;

    /**
     * 阶段定义
     */
    private List<PhaseDefinition> phaseDefinitions;

    @Override
    public List<String> getPhases() {
        if (CollectionUtils.isEmpty(phaseDefinitions)) {
            return Collections.emptyList();
        }
        List<String> phases = phaseDefinitions.stream().map(definition -> definition.getPhaseId()).collect(Collectors.toList());
        return phases;
    }

    @Override
    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    @Override
    public List<PhaseDefinition> getPhaseDefinitions() {
        return phaseDefinitions;
    }

    public void setPhaseDefinitions(List<PhaseDefinition> phaseDefinitions) {
        this.phaseDefinitions = phaseDefinitions;
    }

}
package com.runssnail.ddd.pipeline.api.metadata;

import java.util.List;

/**
 * 流程定义
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface PipelineDefinition extends Definition {

    /**
     * 唯一标识
     *
     * @return
     */
    String getPipelineId();

    /**
     * 阶段唯一标识
     *
     * @return
     */
    List<String> getPhases();

    /**
     * 阶段定义
     *
     * @return
     */
    List<PhaseDefinition> getPhaseDefinitions();
}

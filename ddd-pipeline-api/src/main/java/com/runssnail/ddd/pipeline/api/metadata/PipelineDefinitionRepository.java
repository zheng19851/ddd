package com.runssnail.ddd.pipeline.api.metadata;

import java.util.Map;

/**
 * 流程定义仓储
 *
 * @author zhengwei
 */
public interface PipelineDefinitionRepository {

    /**
     * key=name
     *
     * @return
     */
    Map<String, PipelineDefinition> getPipelineDefinitions();

    /**
     * @param name
     * @return
     */
    PipelineDefinition get(String name);

}
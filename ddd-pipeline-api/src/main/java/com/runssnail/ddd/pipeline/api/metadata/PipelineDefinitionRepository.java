package com.runssnail.ddd.pipeline.api.metadata;

import java.util.List;
import java.util.Map;

/**
 * 流程定义仓储
 *
 * @author zhengwei
 */
public interface PipelineDefinitionRepository {

    /**
     * key=pipelineId 唯一标识
     *
     * @return
     */
    Map<String, PipelineDefinition> getPipelineDefinitions();

    /**
     * 查询流程定义
     *
     * @param onlyEnabled     只包含有效的
     * @param updateTimeStart 最新更新时间-开始
     * @return
     */
    List<PipelineDefinition> getPipelineDefinitions(boolean onlyEnabled, long updateTimeStart);

    /**
     * 流程定义
     *
     * @param pipelineId 唯一标识
     * @return 流程定义
     */
    PipelineDefinition get(String pipelineId);

}
package com.runssnail.ddd.pipeline.api.metadata;

import java.util.Map;

/**
 * 阶段定义加载器
 *
 * @author zhengwei
 */
public interface PhaseDefinitionRepository {

    /**
     * 阶段定义
     *
     * @return 阶段定义
     */
    Map<String, PhaseDefinition> getPhaseDefinitions();

    /**
     * 阶段定义
     *
     * @param phaseId 阶段唯一标识
     * @return 阶段定义
     */
    PhaseDefinition get(String phaseId);

}
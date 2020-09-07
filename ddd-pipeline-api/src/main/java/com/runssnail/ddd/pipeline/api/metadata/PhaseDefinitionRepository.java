package com.runssnail.ddd.pipeline.api.metadata;

import java.util.Map;

/**
 * 阶段定义加载器
 *
 * @author zhengwei
 */
public interface PhaseDefinitionRepository {

    /**
     * @return
     */
    Map<String, PhaseDefinition> getPhaseDefinitions();

    /**
     * @param name
     * @return
     */
    PhaseDefinition get(String name);

}
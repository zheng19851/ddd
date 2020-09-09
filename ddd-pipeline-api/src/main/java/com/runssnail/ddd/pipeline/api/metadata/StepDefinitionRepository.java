package com.runssnail.ddd.pipeline.api.metadata;

import java.util.Map;

/**
 * 步骤定义仓储
 *
 * @author zhengwei
 */
public interface StepDefinitionRepository {

    /**
     * 步骤定义
     *
     * @return 步骤定义
     */
    Map<String, StepDefinition> getStepDefinitions();

    /**
     * 步骤定义
     *
     * @param stepId 步骤唯一标识
     * @return 步骤定义
     */
    StepDefinition get(String stepId);

}
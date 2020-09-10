package com.runssnail.ddd.pipeline.api.metadata;

import java.util.List;
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

    /**
     * 包含有效和失效的
     *
     * @param updateTimeStart 更新时间-开始，小于等于0时，表示查询所有
     * @return
     */
    List<StepDefinition> getStepDefinitions(long updateTimeStart);
}
package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;

/**
 * 步骤执行对象工厂
 *
 * @author zhengwei
 * Created on 2020-09-06
 */
public interface StepFactory extends Lifecycle {

    /**
     * 步骤执行对象
     *
     * @param sd 步骤定义
     * @return 步骤执行对象
     * @throws StepDefinitionException
     */
    Step create(StepDefinition sd) throws StepDefinitionException;

}

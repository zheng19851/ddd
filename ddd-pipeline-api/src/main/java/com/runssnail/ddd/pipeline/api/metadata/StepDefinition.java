package com.runssnail.ddd.pipeline.api.metadata;

/**
 * 步骤元数据定义
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface StepDefinition extends Definition {

    /**
     * 步骤ID
     *
     * @return
     */
    String getStepId();

    /**
     * 步骤类型
     *
     * @return
     */
    String getStepType();

}

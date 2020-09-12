package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.PipelineDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;

/**
 * 创建运行域流程执行对象工厂
 *
 * @author zhengwei
 */
public interface PipelineFactory extends Lifecycle {

    /**
     * 创建运行域流程执行对象
     *
     * @param pd 定义
     * @return 运行域流程执行对象
     * @throws PipelineDefinitionException
     */
    Pipeline create(PipelineDefinition pd) throws PipelineDefinitionException;

}
package com.runssnail.ddd.pipeline.api;

import java.util.List;


/**
 * 流程执行对象管理器
 *
 * @author zhengwei
 */
public interface PipelineRepository extends Lifecycle {

    /**
     * 所有流程执行对象
     *
     * @return 流程执行对象
     */
    List<Pipeline> getAllPipelines();

    /**
     * 流程执行对象
     *
     * @param pipelineId 唯一标识
     * @return 流程执行对象
     */
    Pipeline getPipeline(String pipelineId);

    /**
     * 添加
     *
     * @param pipelines 流程执行对象
     */
    void saveAll(List<Pipeline> pipelines);

    /**
     * 添加
     *
     * @param pipeline 流程执行对象
     */
    void save(Pipeline pipeline);

    /**
     * 删除
     *
     * @param pipelineId 唯一标识
     * @return 流程执行对象
     */
    Pipeline remove(String pipelineId);

    /**
     * 删除
     *
     * @param pipelineIds 唯一标识
     * @return 流程执行对象
     */
    List<Pipeline> removeAll(List<String> pipelineIds);

    /**
     * 是否存在
     *
     * @param pipelineId 唯一标识
     * @return
     */
    boolean contains(String pipelineId);
}
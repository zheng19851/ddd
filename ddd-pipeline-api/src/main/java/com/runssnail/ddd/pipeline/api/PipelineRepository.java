package com.runssnail.ddd.pipeline.api;

import java.util.List;


/**
 * 流程执行对象管理器
 *
 * @author zhengwei
 */
public interface PipelineRepository {

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
    void addAll(List<Pipeline> pipelines);

    /**
     * 添加
     *
     * @param pipeline 流程执行对象
     */
    void add(Pipeline pipeline);

    /**
     * 更新
     *
     * @param pipeline 流程执行对象
     */
    void update(Pipeline pipeline);

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

    boolean contains(String pipelineId);
}
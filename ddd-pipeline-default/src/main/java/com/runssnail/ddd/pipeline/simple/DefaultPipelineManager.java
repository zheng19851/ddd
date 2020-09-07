package com.runssnail.ddd.pipeline.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.runssnail.ddd.pipeline.api.Pipeline;
import com.runssnail.ddd.pipeline.api.PipelineManager;

/**
 * @author zhengwei
 */
public class DefaultPipelineManager implements PipelineManager {

    private ConcurrentMap<String, Pipeline> pipelines = new ConcurrentHashMap<>();

    /**
     * Default constructor
     */
    public DefaultPipelineManager() {
    }

    /**
     * @param pipelineId
     * @return
     */
    @Override
    public Pipeline getPipeline(String pipelineId) {
        return pipelines.get(pipelineId);
    }

    /**
     * @param pipelines
     */
    @Override
    public void addAll(List<Pipeline> pipelines) {
        for (Pipeline pipeline : pipelines) {
            this.add(pipeline);
        }
    }

    /**
     * @param pipeline
     */
    @Override
    public void add(Pipeline pipeline) {
        pipelines.put(pipeline.getPipelineId(), pipeline);
    }

    /**
     * @param pipeline
     */
    @Override
    public void update(Pipeline pipeline) {
        this.pipelines.put(pipeline.getPipelineId(), pipeline);
    }

    /**
     * @param pipelineId
     * @return
     */
    @Override
    public Pipeline remove(String pipelineId) {
        return pipelines.remove(pipelineId);
    }

    /**
     * @param pipelineIds
     * @return
     */
    @Override
    public List<Pipeline> removeAll(List<String> pipelineIds) {
        List<Pipeline> removedList = new ArrayList<>(pipelineIds.size());
        for (String name : pipelineIds) {
            Pipeline pipeline = this.pipelines.remove(name);
            if (pipeline != null) {
                removedList.add(pipeline);
            }
        }
        return removedList;
    }

    @Override
    public boolean contains(String pipelineId) {
        return this.pipelines.containsKey(pipelineId);
    }

    @Override
    public List<Pipeline> getAllPipelines() {
        return new ArrayList<>(this.pipelines.values());
    }

}
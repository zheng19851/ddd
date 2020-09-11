package com.runssnail.ddd.pipeline.definition.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.runssnail.ddd.pipeline.api.metadata.BasePipelineDefinitionRepository;
import com.runssnail.ddd.pipeline.api.metadata.DefaultPipelineDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinitionRepository;

/**
 * 从mysql数据库里加载Pipeline
 *
 * @author zhengwei
 */
public class MysqlPipelineDefinitionRepository extends BasePipelineDefinitionRepository implements PipelineDefinitionRepository {

    /**
     * Default constructor
     */
    public MysqlPipelineDefinitionRepository() {
    }

    /**
     * @return
     */
    @Override
    public Map<String, PipelineDefinition> getPipelineDefinitions() {
        // TODO implement here
        return null;
    }

    @Override
    public List<PipelineDefinition> getPipelineDefinitions(boolean onlyEnabled, long updateTimeStart) {

        // todo 待实现
        // 如果PipelineDefinition被删除了，那么Phase就不用加载了
        PipelineDefinition pipelineDefinition = new DefaultPipelineDefinition();
        List<PipelineDefinition> result = new ArrayList<>(1);
        result.add(pipelineDefinition);
        return result;
    }

    /**
     * @param pipelineId
     * @return
     */
    @Override
    public PipelineDefinition get(String pipelineId) {
        // TODO implement here
        return null;
    }

}
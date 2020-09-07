package com.runssnail.ddd.pipeline.definition.mysql;

import java.util.Map;

import com.runssnail.ddd.pipeline.api.metadata.BasePipelineDefinitionRepository;
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

    /**
     * @param name
     * @return
     */
    @Override
    public PipelineDefinition get(String name) {
        // TODO implement here
        return null;
    }

}
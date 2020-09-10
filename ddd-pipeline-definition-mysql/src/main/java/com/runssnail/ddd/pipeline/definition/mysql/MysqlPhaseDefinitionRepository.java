package com.runssnail.ddd.pipeline.definition.mysql;

import java.util.Map;

import com.runssnail.ddd.pipeline.api.metadata.BasePhaseDefinitionRepository;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;

/**
 * 阶段定义
 *
 * @author zhengwei <zhengwei03@kuaishou.com>
 * Created on 2020-09-10
 */
public class MysqlPhaseDefinitionRepository extends BasePhaseDefinitionRepository {

    @Override
    public Map<String, PhaseDefinition> getPhaseDefinitions() {
        // todo 待实现
        return null;
    }

    @Override
    public PhaseDefinition get(String phaseId) {
        // todo 待实现
        return null;
    }
}

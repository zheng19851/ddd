package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.PhaseDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;

/**
 * 阶段运行域对象工厂
 *
 * @author zhengwei
 * Created on 2020-09-06
 */
public interface PhaseFactory extends Lifecycle {

    /**
     * 创建Phase
     *
     * @param pd 阶段定义
     * @return 阶段运行域对象
     * @throws PhaseDefinitionException
     */
    Phase create(PhaseDefinition pd) throws PhaseDefinitionException;

}

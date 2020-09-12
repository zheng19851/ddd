package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.spi.StepFactory;

/**
 * StepFactory仓库
 *
 * @author zhengwei
 * Created on 2020-09-12
 * @see com.runssnail.ddd.pipeline.api.spi.StepFactory
 */
public interface StepFactoryRepository extends Lifecycle {

    /**
     * 根据step type找到对应的工厂
     *
     * @param type 类型
     * @return
     */
    StepFactory getStepFactory(String type) throws StepDefinitionException;
}

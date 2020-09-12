package com.runssnail.ddd.pipeline.api;

import org.apache.commons.lang3.StringUtils;

import com.runssnail.ddd.pipeline.api.constant.Constants;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;
import com.runssnail.ddd.pipeline.api.spi.StepFactory;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * @author zhengwei
 * Created on 2020-09-12
 */
public abstract class BaseStepFactory extends BaseFactory implements StepFactory {

    @Override
    public Step create(StepDefinition definition) throws StepDefinitionException {

        Step step = doCreate(definition);

        if (step != null) {
            // 中断策略
            String strategy = definition.getAttribute(Constants.ATTRIBUTE_TERMINATE_STRATEGY);
            if (StringUtils.isNotBlank(strategy)) {
                TerminateStrategy terminateStrategy = terminateStrategyFactory.create(strategy);
                step.setTerminateStrategy(terminateStrategy);
            }
            step.init();
        }

        if (step == null) {
            String msg = "create Step fail(type unsupported), stepId=" + definition.getStepId() + ", type=" + definition.getStepType();
            throw new StepDefinitionException(definition.getStepId(), msg);
        }

        return step;
    }

    /**
     * 创建step
     *
     * @param definition 定义
     * @return
     */
    protected abstract Step doCreate(StepDefinition definition);
}

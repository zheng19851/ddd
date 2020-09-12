package com.runssnail.ddd.pipeline.memory.bean;

import java.util.List;

import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;
import com.runssnail.ddd.pipeline.api.spi.BaseStepFactory;
import com.runssnail.ddd.pipeline.api.spi.StepFactory;
import com.runssnail.ddd.pipeline.memory.grpc.GrpcStep;

/**
 * 步骤执行对象工厂
 *
 * @author zhengwei
 * Created on 2020-09-08
 * @see GrpcStep
 */
public class BeanStepFactory extends BaseStepFactory implements StepFactory {

    @Override
    public Step doCreate(StepDefinition definition) throws StepDefinitionException {

        Step step = null;
        if ("bean".equalsIgnoreCase(definition.getStepType())) {
            return createBeanStep(definition);
        }

        return step;
    }

    private BeanStep createBeanStep(StepDefinition definition) {
        BeanStep beanStep = new BeanStep(definition.getStepId());
        beanStep.setInterceptors(createInterceptors(definition));
        return beanStep;
    }

    private List<Interceptor> createInterceptors(StepDefinition definition) {
        // 预留
        return null;
    }

}

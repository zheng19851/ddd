package com.runssnail.ddd.pipeline.memory;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.runssnail.ddd.pipeline.api.BaseFactory;
import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepFactory;
import com.runssnail.ddd.pipeline.api.constant.Constants;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;
import com.runssnail.ddd.pipeline.memory.bean.BeanStep;
import com.runssnail.ddd.pipeline.memory.grpc.GrpcStep;

/**
 * 步骤执行对象工厂
 *
 * @author zhengwei
 * Created on 2020-09-08
 * @see GrpcStep
 */
public class DefaultStepFactory extends BaseFactory implements StepFactory {

    /**
     * 默认的超时时间，单位毫秒
     */
    public static final long DEFAULT_TIMEOUT = 5000;

    @Override
    public Step create(StepDefinition definition) throws StepDefinitionException {

        // todo 后续可以优化成SPI机制，key=type，value=具体的实现
        Step step = null;
        if ("grpc".equalsIgnoreCase(definition.getStepType())) {
            step = createGrpcStep(definition);
        } else if ("bean".equalsIgnoreCase(definition.getStepType())) {
            return createBeanStep(definition);
        }

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

    private BeanStep createBeanStep(StepDefinition sd) {
        BeanStep beanStep = new BeanStep(sd.getStepId());
        return beanStep;
    }

    private GrpcStep createGrpcStep(StepDefinition sd) {
        String bizDef = sd.getAttribute("grpc.bizDef");
        String fullName = sd.getAttribute("grpc.fullName");
        String method = sd.getAttribute("grpc.method");
        long timeout = sd.getAttrLongValue("grpc.timeout", DEFAULT_TIMEOUT);
        GrpcStep grpcStep = new GrpcStep(sd.getStepId(), bizDef, fullName, method);
        grpcStep.setTimeout(timeout);

        List<Interceptor> interceptors = createInterceptors(sd);
        grpcStep.setInterceptors(interceptors);

        return grpcStep;
    }

    private List<Interceptor> createInterceptors(StepDefinition sd) {
        // 预留
        return null;
    }

}

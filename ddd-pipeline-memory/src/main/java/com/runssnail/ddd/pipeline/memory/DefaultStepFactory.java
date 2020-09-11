package com.runssnail.ddd.pipeline.memory;

import java.util.List;

import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepFactory;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;
import com.runssnail.ddd.pipeline.memory.bean.BeanStep;
import com.runssnail.ddd.pipeline.memory.grpc.GrpcStep;

/**
 * 步骤执行对象工厂
 *
 * @author zhengwei
 * Created on 2020-09-08
 * @see GrpcStep
 */
public class DefaultStepFactory implements StepFactory {

    /**
     * 默认的超时时间，单位毫秒
     */
    public static final long DEFAULT_TIMEOUT = 5000;

    @Override
    public Step create(StepDefinition sd) throws StepDefinitionException {

        // todo 后续可以优化成SPI机制，key=type，value=具体的实现
        Step step = null;
        if ("grpc".equalsIgnoreCase(sd.getStepType())) {
            step = createGrpcStep(sd);
        } else if ("bean".equalsIgnoreCase(sd.getStepType())) {
            return createBeanStep(sd);
        }

        if (step != null) {
            step.init();
        }

        if (step == null) {
            String msg = "create Step fail(type unsupported), stepId=" + sd.getStepId() + ", type=" + sd.getStepType();
            throw new StepDefinitionException(sd.getStepId(), msg);
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

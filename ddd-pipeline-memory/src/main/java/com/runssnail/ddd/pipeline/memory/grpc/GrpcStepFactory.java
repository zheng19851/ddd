package com.runssnail.ddd.pipeline.memory.grpc;

import java.util.List;

import com.runssnail.ddd.pipeline.api.BaseStepFactory;
import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;
import com.runssnail.ddd.pipeline.api.spi.StepFactory;

/**
 * grpc步骤执行对象工厂
 *
 * @author zhengwei
 * Created on 2020-09-08
 * @see GrpcStep
 */
public class GrpcStepFactory extends BaseStepFactory implements StepFactory {

    /**
     * 默认的超时时间，单位毫秒
     */
    public static final long DEFAULT_TIMEOUT = 5000;

    @Override
    public Step doCreate(StepDefinition definition) throws StepDefinitionException {

        Step step = null;
        if ("grpc".equalsIgnoreCase(definition.getStepType())) {
            step = createGrpcStep(definition);
        }

        return step;
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

    @Override
    public String getType() {
        return "grpc";
    }
}

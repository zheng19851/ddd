package com.runssnail.ddd.pipeline.simple;

import java.util.List;

import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepFactory;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;
import com.runssnail.ddd.pipeline.simple.grpc.GrpcStep;

/**
 * 步骤执行对象工厂
 *
 * @author zhengwei
 * Created on 2020-09-08
 * @see com.runssnail.ddd.pipeline.api.ParamMappingInterceptor
 * @see GrpcStep
 */
public class DefaultStepFactory implements StepFactory {

    /**
     * 默认的超时时间，单位毫秒
     */
    public static final long DEFAULT_TIMEOUT = 3000;

    @Override
    public Step create(StepDefinition sd) throws StepDefinitionException {
        Step step = null;
        if ("grpc".equalsIgnoreCase(sd.getStepType())) {
            step = createGrpcStep(sd);
        }

        if (step == null) {
            throw new StepDefinitionException(sd.getStepId(), "create Step fail, type=" + sd.getStepType());
        }

        return step;
    }

    private GrpcStep createGrpcStep(StepDefinition sd) {
        String fullName = sd.getAttribute("grpc.fullName");
        String method = sd.getAttribute("grpc.method");
        long timeout = sd.getAttrLongValue("grpc.timeout", DEFAULT_TIMEOUT);
        GrpcStep grpcStep = new GrpcStep(sd.getStepId(), fullName, method);
        grpcStep.setTimeout(timeout);

        // todo 设置参数映射配置

        List<Interceptor> interceptors = createInterceptors(sd);
        grpcStep.setInterceptors(interceptors);

        return grpcStep;
    }

    private List<Interceptor> createInterceptors(StepDefinition sd) {

        // todo 根据参数映射创建拦截器

        return null;
    }
}

package com.runssnail.ddd.pipeline.simple;

import java.util.List;

import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepFactory;
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

    @Override
    public Step create(StepDefinition sd) {

        // todo 根据step类型，创建不同的step

        GrpcStep step = new GrpcStep(sd.getStepId());

        List<Interceptor> interceptors = createInterceptors(sd);
        step.setInterceptors(interceptors);

        return step;
    }

    private List<Interceptor> createInterceptors(StepDefinition sd) {

        // todo 根据参数映射创建拦截器

        return null;
    }
}

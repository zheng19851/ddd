package com.runssnail.ddd.pipeline.memory;

import java.util.HashMap;
import java.util.Map;

import com.runssnail.ddd.pipeline.api.StepFactoryRepository;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.spi.StepFactory;
import com.runssnail.ddd.pipeline.memory.bean.BeanStepFactory;
import com.runssnail.ddd.pipeline.memory.grpc.GrpcStepFactory;

/**
 * StepFactoryRepository
 *
 * @author zhengwei
 * Created on 2020-09-12
 */
public class DefaultStepFactoryRepository implements StepFactoryRepository {
    private Map<String, StepFactory> stepFactoryMap = new HashMap<>();

    @Override
    public StepFactory getStepFactory(String type) throws StepDefinitionException {
        return stepFactoryMap.get(type);
    }

    @Override
    public void init() {
        // todo 提前加载所有StepFactory
        // 后续可以使用SPI自动找到对应的工厂
        stepFactoryMap.put("bean", new BeanStepFactory());
        stepFactoryMap.put("grpc", new GrpcStepFactory());
    }

    @Override
    public void close() {

    }
}

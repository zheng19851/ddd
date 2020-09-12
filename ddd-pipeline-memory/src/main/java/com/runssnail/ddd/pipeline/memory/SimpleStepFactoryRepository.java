package com.runssnail.ddd.pipeline.memory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @see SpiStepFactoryRepository
 */
public class SimpleStepFactoryRepository implements StepFactoryRepository {
    private static final Logger log = LoggerFactory.getLogger(SimpleStepFactoryRepository.class);
    private Map<String, StepFactory> stepFactoryMap = new HashMap<>();

    @Override
    public StepFactory getStepFactory(String type) throws StepDefinitionException {
        return stepFactoryMap.get(type);
    }

    @Override
    public void init() {
        stepFactoryMap.put("bean", new BeanStepFactory());
        stepFactoryMap.put("grpc", new GrpcStepFactory());
    }

    @Override
    public void close() {

    }
}

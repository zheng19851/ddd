package com.runssnail.ddd.pipeline.memory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.runssnail.ddd.pipeline.api.StepFactoryRepository;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.spi.StepFactory;

/**
 * StepFactoryRepository
 *
 * @author zhengwei
 * Created on 2020-09-12
 */
@Repository
public class SpiStepFactoryRepository implements StepFactoryRepository {
    private static final Logger log = LoggerFactory.getLogger(SpiStepFactoryRepository.class);

    /**
     * key=type
     */
    private Map<String, StepFactory> stepFactoryMap = new HashMap<>();

    @Override
    public StepFactory getStepFactory(String type) throws StepDefinitionException {
        return stepFactoryMap.get(type);
    }

    @Override
    public void init() {
        // 使用SPI自动找到对应的工厂
        ServiceLoader<StepFactory> stepFactories = ServiceLoader.load(StepFactory.class);
        Iterator<StepFactory> iterator = stepFactories.iterator();
        if (!iterator.hasNext()) {
            throw new RuntimeException("Cannot find any StepFactory from SPI files");
        }

        while (iterator.hasNext()) {
            StepFactory stepFactory = iterator.next();
            if (StringUtils.isBlank(stepFactory.getType())) {
                throw new RuntimeException("StepFactory type is blank, class=" + stepFactory.getClass().getCanonicalName());
            }
            if (stepFactoryMap.containsKey(stepFactory.getType())) {
                throw new RuntimeException("StepFactory duplicated, type=" + stepFactory.getType());
            }
            stepFactoryMap.put(stepFactory.getType(), stepFactory);
        }

        log.info("find {} StepFactory, {}", stepFactoryMap.size(), stepFactoryMap);
    }

    @Override
    public void close() {
        stepFactoryMap.clear();
    }
}

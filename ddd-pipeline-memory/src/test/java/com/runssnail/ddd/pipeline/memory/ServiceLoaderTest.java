package com.runssnail.ddd.pipeline.memory;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;

import com.runssnail.ddd.pipeline.api.spi.StepFactory;

/**
 * @author zhengwei
 * Created on 2020-09-12
 */
public class ServiceLoaderTest {

    @Test
    public void testServiceLoader() {
        ServiceLoader<StepFactory> stepFactories = ServiceLoader.load(StepFactory.class);
        Iterator<StepFactory> iterator = stepFactories.iterator();
        if (iterator.hasNext()) {
            System.out.println("has next");
        }

        while (iterator.hasNext()) {
            StepFactory stepFactory = iterator.next();
            System.out.println(stepFactory.getType());
            System.out.println(stepFactory);
        }
//
//        System.out.println(stepFactories);
    }

}

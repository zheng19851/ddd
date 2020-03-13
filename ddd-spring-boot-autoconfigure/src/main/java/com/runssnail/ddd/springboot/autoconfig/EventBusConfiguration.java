package com.runssnail.ddd.springboot.autoconfig;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.runssnail.ddd.spring.EventBusFactoryBean;
import lombok.extern.slf4j.Slf4j;

/**
 * EventBusConfiguration
 *
 * @author zhengwei
 * @date 2019-11-07 15:21
 *
 * @see EventBusFactoryBean
 **/
@Slf4j
public class EventBusConfiguration implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(EventBusFactoryBean.class);
        registry.registerBeanDefinition("eventBus", beanDefinition);
        log.info("Register EventBusFactoryBean success");
    }
}

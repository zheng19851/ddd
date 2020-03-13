package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.spring.CommandBusFactoryBean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import lombok.extern.slf4j.Slf4j;

/**
 * CommandBus Configuration
 *
 * @author zhengwei
 * @date 2019-11-07 20:13
 * @see CommandBusFactoryBean
 **/
@Slf4j
@Configuration
public class CommandBusConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(CommandBusFactoryBean.class);
//        beanDefinition.getPropertyValues().addPropertyValue("commandExceptionHandler", new RuntimeBeanReference("commandExceptionHandler"));
        registry.registerBeanDefinition("commandBus", beanDefinition);
        log.info("Register CommandBusFactoryBean success");
    }

}

package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.spring.ValidateCommandInterceptorFactoryBean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import lombok.extern.slf4j.Slf4j;

/**
 * ValidateCommandInterceptor Configuration
 *
 * @author zhengwei
 * @date 2019-11-07 15:21
 *
 * @see ValidateCommandInterceptorFactoryBean
 **/
@Slf4j
public class ValidateCommandInterceptorConfiguration implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(ValidateCommandInterceptorFactoryBean.class);
        registry.registerBeanDefinition("validateCommandInterceptor", beanDefinition);

        log.info("Register ValidateCommandInterceptorFactoryBean success");
    }
}

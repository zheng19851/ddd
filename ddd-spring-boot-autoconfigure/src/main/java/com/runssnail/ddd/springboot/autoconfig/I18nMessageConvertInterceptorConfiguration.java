package com.runssnail.ddd.springboot.autoconfig;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.runssnail.ddd.spring.interceptor.I18nMessageConvertInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 注册国际化需要的拦截器和message source
 *
 * @author zhengwei
 * @date 2020/3/5 11:50 上午
 *
 * @see I18nMessageConvertInterceptor
 *
 **/
@Slf4j
public class I18nMessageConvertInterceptorConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(I18nMessageConvertInterceptor.class);
        registry.registerBeanDefinition("i18nMessageConvertInterceptor", beanDefinition);
        log.info("Register I18nMessageConvertInterceptor success");
    }

}

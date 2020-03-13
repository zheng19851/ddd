package com.runssnail.ddd.springboot.autoconfig;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.runssnail.ddd.spring.SpringMessageSource;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * message source
 *
 * @author zhengwei
 * @date 2020/3/5 11:50 上午
 **/
@Slf4j
public class MessageSourceConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition springMessageSourceBean = new RootBeanDefinition(SpringMessageSource.class);
        registry.registerBeanDefinition("springMessageSource", springMessageSourceBean);

        log.info("Register SpringMessageSource success");
    }

}

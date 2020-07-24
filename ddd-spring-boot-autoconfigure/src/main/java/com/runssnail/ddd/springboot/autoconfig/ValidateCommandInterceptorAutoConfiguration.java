package com.runssnail.ddd.springboot.autoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.runssnail.ddd.commandhandling.interceptor.ValidateCommandInterceptor;
import com.runssnail.ddd.spring.ValidateCommandInterceptorFactoryBean;

/**
 * ValidateCommandInterceptor AutoConfiguration
 *
 * @author zhengwei
 * @date 2019-11-08 14:05
 * @see ValidateCommandInterceptor
 * @see ValidateCommandInterceptorFactoryBean
 **/
@Configuration
@Import(ValidateCommandInterceptorConfiguration.class)
public class ValidateCommandInterceptorAutoConfiguration {
}

package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.command.interceptor.ValidateCommandInterceptor;
import com.runssnail.ddd.spring.ValidateCommandInterceptorFactoryBean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

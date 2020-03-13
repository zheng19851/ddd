package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.spring.interceptor.I18nMessageConvertInterceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 动态配置i8n拦截器
 *
 * @author zhengwei
 * @date 2020/3/5 11:54 上午
 * @see I18nMessageConvertInterceptor
 **/
@Configuration
@ConditionalOnMissingBean(I18nMessageConvertInterceptor.class)
@ConditionalOnProperty(name = "ddd.i18n.convert.enabled", havingValue = "true")
@Import(I18nMessageConvertInterceptorConfiguration.class)
public class I18nMessageConvertInterceptorAutoConfiguration {


}

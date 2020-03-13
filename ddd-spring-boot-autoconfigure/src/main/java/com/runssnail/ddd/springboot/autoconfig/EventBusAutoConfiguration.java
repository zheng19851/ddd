package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.event.EventBus;
import com.runssnail.ddd.spring.EventBusFactoryBean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * EventBus AutoConfiguration
 *
 * @author zhengwei
 * @date 2019-11-07 15:21
 *
 * @see EventBus
 * @see EventBusFactoryBean
 **/
@Configuration
@Import(EventBusConfiguration.class)
public class EventBusAutoConfiguration {
}

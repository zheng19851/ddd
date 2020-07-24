package com.runssnail.ddd.springboot.autoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.runssnail.ddd.eventhandling.EventBus;
import com.runssnail.ddd.spring.EventBusFactoryBean;

/**
 * EventBus AutoConfiguration
 *
 * @author zhengwei
 * @date 2019-11-07 15:21
 * @see EventBus
 * @see EventBusFactoryBean
 **/
@Configuration
@Import(EventBusConfiguration.class)
public class EventBusAutoConfiguration {
}

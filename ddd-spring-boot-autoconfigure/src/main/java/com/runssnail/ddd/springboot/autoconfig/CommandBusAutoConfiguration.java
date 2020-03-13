package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.command.CommandBus;
import com.runssnail.ddd.spring.CommandBusFactoryBean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

/**
 * CommandBus AutoConfiguration
 *
 * @author zhengwei
 * @date 2019-11-07 15:21
 * @see CommandBus
 * @see CommandBusFactoryBean
 **/
@Configuration
@Import({CommandExceptionHandlerConfiguration.class, CommandBusConfiguration.class})
@Slf4j
public class CommandBusAutoConfiguration {


}

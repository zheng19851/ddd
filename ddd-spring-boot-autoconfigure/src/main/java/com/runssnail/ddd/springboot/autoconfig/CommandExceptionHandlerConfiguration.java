package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.command.handler.CommandExceptionHandler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.runssnail.ddd.command.handler.DefaultCommandExceptionHandler;
import com.runssnail.ddd.command.handler.RethrowCommandExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * CommandExceptionHandler Configuration
 *
 * @author zhengwei
 * @date 2020/3/7 4:24 下午
 *
 * @see CommandExceptionHandler
 * @see RethrowCommandExceptionHandler
 * @see DefaultCommandExceptionHandler
 **/
@Configuration
@Slf4j
public class CommandExceptionHandlerConfiguration {

    @ConditionalOnMissingBean(CommandExceptionHandler.class)
    @ConditionalOnProperty(name = "ddd.command.exception.handler.type", havingValue = "rethrow")
    @Bean("commandExceptionHandler")
    public RethrowCommandExceptionHandler rethrowCommandExceptionHandler() {
        log.info("Register CommandExceptionHandler(RethrowCommandExceptionHandler)");
        return new RethrowCommandExceptionHandler();
    }

    @ConditionalOnMissingBean(CommandExceptionHandler.class)
    @ConditionalOnProperty(name = "ddd.command.exception.handler.type", havingValue = "default", matchIfMissing = true)
    @Bean("commandExceptionHandler")
    public DefaultCommandExceptionHandler defaultCommandExceptionHandler() {
        log.info("Register CommandExceptionHandler(DefaultCommandExceptionHandler)");
        return new DefaultCommandExceptionHandler();
    }
}

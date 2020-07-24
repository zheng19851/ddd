package com.runssnail.ddd.commandhandling;

import javax.annotation.PostConstruct;

import com.runssnail.ddd.commandhandling.handler.CommandExceptionHandler;
import com.runssnail.ddd.commandhandling.handler.CommandHandler;
import com.runssnail.ddd.commandhandling.handler.CommandHandlerResolver;
import com.runssnail.ddd.commandhandling.handler.DefaultCommandExceptionHandler;
import com.runssnail.ddd.commandhandling.handler.DefaultCommandHandlerResolver;
import com.runssnail.ddd.commandhandling.interceptor.CommandInterceptor;
import com.runssnail.ddd.commandhandling.interceptor.CommandInterceptorResolver;
import com.runssnail.ddd.commandhandling.interceptor.DefaultCommandInterceptorResolver;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 命令总线
 *
 * @author zhengwei
 * @date 2019-11-04 15:36
 **/
@Slf4j
public class DefaultCommandBus implements CommandBus {

    private CommandHandlerResolver commandHandlerResolver = new DefaultCommandHandlerResolver();

    /**
     * 命令异常统一处理器
     */
    private CommandExceptionHandler commandExceptionHandler;

    private CommandInterceptorResolver commandInterceptorResolver = new DefaultCommandInterceptorResolver();

    private CommandInvocationFactory commandInvocationFactory;

    @Override
    public <T extends BaseResult> T dispatch(Command<T> command) {
        CommandInvocation commandInvocation = commandInvocationFactory.createCommandInvocation(command);
        return (T) commandInvocation.invoke();
    }

    @Override
    public void registerCommandHandler(CommandHandler commandHandler) {
        this.commandHandlerResolver.registerCommandHandler(commandHandler);
    }

    @Override
    public void registerCommandInterceptor(CommandInterceptor interceptor) {
        this.commandInterceptorResolver.registerCommandInterceptor(interceptor);
    }

    @PostConstruct
    public void init() {
        initCommandExceptionHandler();
        initCommandInvocationFactory();
        log.info("CommandBus init end");
    }

    private void initCommandInvocationFactory() {
        if (this.commandInvocationFactory == null) {
            log.debug("Using DefaultCommandInvocationFactory");
            this.commandInvocationFactory = new DefaultCommandInvocationFactory(this.commandHandlerResolver, this.commandInterceptorResolver, this.commandExceptionHandler);
        }
    }

    private void initCommandExceptionHandler() {
        if (this.commandExceptionHandler == null) {
            log.debug("Using DefaultCommandExceptionHandler");
            this.commandExceptionHandler = new DefaultCommandExceptionHandler();
        }
    }

    public void setCommandHandlerResolver(CommandHandlerResolver commandHandlerResolver) {
        this.commandHandlerResolver = commandHandlerResolver;
    }

    public void setCommandExceptionHandler(CommandExceptionHandler commandExceptionHandler) {
        this.commandExceptionHandler = commandExceptionHandler;
    }

    public void setCommandInterceptorResolver(CommandInterceptorResolver commandInterceptorResolver) {
        this.commandInterceptorResolver = commandInterceptorResolver;
    }

    public void setCommandInvocationFactory(CommandInvocationFactory commandInvocationFactory) {
        this.commandInvocationFactory = commandInvocationFactory;
    }
}

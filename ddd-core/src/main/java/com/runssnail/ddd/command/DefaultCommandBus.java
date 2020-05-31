package com.runssnail.ddd.command;

import javax.annotation.PostConstruct;

import com.runssnail.ddd.command.handler.CommandExceptionHandler;
import com.runssnail.ddd.command.handler.CommandHandler;
import com.runssnail.ddd.command.handler.CommandHandlerResolver;
import com.runssnail.ddd.command.handler.DefaultCommandExceptionHandler;
import com.runssnail.ddd.command.handler.DefaultCommandHandlerResolver;
import com.runssnail.ddd.command.interceptor.CommandInterceptor;
import com.runssnail.ddd.command.interceptor.CommandInterceptorResolver;
import com.runssnail.ddd.command.interceptor.DefaultCommandInterceptorResolver;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

/**
 * 命令总线
 *
 * @author zhengwei
 * @date 2019-11-04 15:36
 **/
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
    }

    private void initCommandInvocationFactory() {
        if (this.commandInvocationFactory == null) {
            this.commandInvocationFactory = new DefaultCommandInvocationFactory(this.commandHandlerResolver, this.commandInterceptorResolver, this.commandExceptionHandler);
        }
    }

    private void initCommandExceptionHandler() {
        if (this.commandExceptionHandler == null) {
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

package com.runssnail.ddd.commandhandling;

import java.util.List;

import com.runssnail.ddd.commandhandling.handler.CommandExceptionHandler;
import com.runssnail.ddd.commandhandling.handler.CommandHandler;
import com.runssnail.ddd.commandhandling.handler.CommandHandlerResolver;
import com.runssnail.ddd.commandhandling.interceptor.CommandInterceptor;
import com.runssnail.ddd.commandhandling.interceptor.CommandInterceptorResolver;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengwei
 */
@Slf4j
public class DefaultCommandInvocationFactory implements CommandInvocationFactory {

    private CommandHandlerResolver commandHandlerResolver;

    /**
     * 命令异常统一处理器
     */
    private CommandExceptionHandler commandExceptionHandler;

    private CommandInterceptorResolver commandInterceptorResolver;

    public DefaultCommandInvocationFactory(CommandHandlerResolver commandHandlerResolver, CommandInterceptorResolver commandInterceptorResolver, CommandExceptionHandler commandExceptionHandler) {
        this.commandExceptionHandler = commandExceptionHandler;
        this.commandHandlerResolver = commandHandlerResolver;
        this.commandInterceptorResolver = commandInterceptorResolver;
    }

    @Override
    public <C extends Command<T>, T extends BaseResult> CommandInvocation<C, T> createCommandInvocation(Command<T> command) {
        final CommandHandler<C, T> commandHandler = commandHandlerResolver.resolve(command);

        final List<CommandInterceptor> commandInterceptors = commandInterceptorResolver.resolveInterceptors(command.getClass());
        CommandInvocation ci = new CommandInvocation(command, commandHandler, this.commandExceptionHandler, commandInterceptors);

        return ci;
    }

}

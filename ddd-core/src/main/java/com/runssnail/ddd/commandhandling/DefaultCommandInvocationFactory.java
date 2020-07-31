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
 * DefaultCommandInvocationFactory
 *
 * @author zhengwei
 */
@Slf4j
public class DefaultCommandInvocationFactory implements CommandInvocationFactory {

    /**
     * 命令处理器解析器
     */
    private CommandHandlerResolver commandHandlerResolver;

    /**
     * 命令异常统一处理器
     */
    private CommandExceptionHandler commandExceptionHandler;

    /**
     * 命令拦截器解析器
     */
    private CommandInterceptorResolver commandInterceptorResolver;

    public DefaultCommandInvocationFactory(CommandHandlerResolver commandHandlerResolver, CommandInterceptorResolver commandInterceptorResolver, CommandExceptionHandler commandExceptionHandler) {
        this.commandExceptionHandler = commandExceptionHandler;
        this.commandHandlerResolver = commandHandlerResolver;
        this.commandInterceptorResolver = commandInterceptorResolver;
    }

    @Override
    public <T extends BaseResult> CommandInvocation<T> createCommandInvocation(Command<T> command) {
        final CommandHandler<Command<T>, T> commandHandler = commandHandlerResolver.resolve(command);

        final List<CommandInterceptor> commandInterceptors = commandInterceptorResolver.resolveInterceptors(command.getClass());
        CommandInvocation ci = new CommandInvocation(command, commandHandler, this.commandExceptionHandler, commandInterceptors);

        return ci;
    }

}

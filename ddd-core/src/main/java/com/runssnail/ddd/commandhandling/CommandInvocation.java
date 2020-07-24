package com.runssnail.ddd.commandhandling;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.commandhandling.handler.CommandExceptionHandler;
import com.runssnail.ddd.commandhandling.handler.CommandHandler;
import com.runssnail.ddd.commandhandling.handler.TransactionCommandHandler;
import com.runssnail.ddd.commandhandling.interceptor.CommandInterceptor;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

/**
 * 命令调用信息
 *
 * @param <T>
 * @author zhengwei
 */
public class CommandInvocation<C extends Command<T>, T extends BaseResult> {
    private static final Logger log = LoggerFactory.getLogger(CommandInvocation.class);

    private C command;

    private CommandHandler<C, T> commandHandler;

    private CommandExceptionHandler commandExceptionHandler;

    private Collection<CommandInterceptor> interceptors;

    public CommandInvocation(C command, CommandHandler<C, T> commandHandler, CommandExceptionHandler commandExceptionHandler, Collection<CommandInterceptor> interceptors) {
        this.command = command;
        this.commandHandler = commandHandler;
        this.commandExceptionHandler = commandExceptionHandler;
        this.interceptors = interceptors;
    }

    public T invoke() {
        T result = null;
        try {
            invokePreInterceptors(command);

            if (commandHandler instanceof TransactionCommandHandler) {
                result = (T) ((TransactionCommandHandler) this.commandHandler).handleInTransaction(this.command);
            } else {
                result = this.commandHandler.handle(command);
            }
        } catch (Exception e) {
            result = createResultInstance(command);
            result.setCode(BaseResult.SERVER_ERROR_CODE);
            commandExceptionHandler.onException(command, result, e);
        } finally {
            invokePostInterceptors(command, result);
        }
        return result;

    }

    private T createResultInstance(Command<T> command) {
        Class<T> responseClz = command.resultType();
        try {
            return responseClz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Make sure exists No Args Constructor", e);
        }
    }

    private void invokePostInterceptors(Command command, T response) {
        if (CollectionUtils.isEmpty(interceptors)) {
            return;
        }

        for (CommandInterceptor interceptor : interceptors) {
            try {
                interceptor.afterHandle(command, response);
            } catch (Exception e) {
                log.error("postInterceptor error, command={}, interceptor={}", command, interceptor, e);
            }
        }

    }

    private void invokePreInterceptors(Command command) {
        if (CollectionUtils.isEmpty(interceptors)) {
            return;
        }
        for (CommandInterceptor interceptor : interceptors) {
            interceptor.beforeHandle(command);
        }
    }
}

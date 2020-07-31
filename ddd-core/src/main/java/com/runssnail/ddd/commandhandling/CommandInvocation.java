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
 * @param <T> 结果类型
 * @author zhengwei
 * @see Command
 * @see CommandHandler
 * @see CommandExceptionHandler
 * @see CommandInterceptor
 */
public class CommandInvocation<T extends BaseResult> {
    private static final Logger log = LoggerFactory.getLogger(CommandInvocation.class);

    /**
     * 命令
     */
    private Command<T> command;

    /**
     * 命令处理器
     */
    private CommandHandler<Command<T>, T> commandHandler;

    /**
     * 命令异常处理器
     */
    private CommandExceptionHandler commandExceptionHandler;

    /**
     * 命令拦截器
     */
    private Collection<CommandInterceptor> interceptors;

    /**
     * 创建一个CommandInvocation
     *
     * @param command                 命令
     * @param commandHandler          命令处理器
     * @param commandExceptionHandler 命令异常处理器
     * @param interceptors            命令拦截器
     */
    public CommandInvocation(Command<T> command, CommandHandler<Command<T>, T> commandHandler, CommandExceptionHandler commandExceptionHandler, Collection<CommandInterceptor> interceptors) {
        this.command = command;
        this.commandHandler = commandHandler;
        this.commandExceptionHandler = commandExceptionHandler;
        this.interceptors = interceptors;
    }

    /**
     * 调用命令
     *
     * @return
     */
    public T invoke() {
        T result = null;
        try {
            invokeCommandInterceptorsBeforeHandle(command);

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
            invokeCommandInterceptorsAfterHandle(command, result);
        }
        return result;

    }

    private T createResultInstance(Command<T> command) {
        Class<T> resultType = command.resultType();
        try {
            return resultType.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("make sure exists no args constructor", e);
        }
    }

    private void invokeCommandInterceptorsAfterHandle(Command command, T response) {
        if (CollectionUtils.isEmpty(interceptors)) {
            return;
        }

        for (CommandInterceptor interceptor : interceptors) {
            try {
                interceptor.afterHandle(command, response);
            } catch (Exception e) {
                log.error("invoke afterHandle error, command={}, interceptor={}", command, interceptor, e);
//                throw new RuntimeException("invoke afterHandle error, command=" + command + ", interceptor=" + interceptor, e);
            }
        }

    }

    private void invokeCommandInterceptorsBeforeHandle(Command command) {
        if (CollectionUtils.isEmpty(interceptors)) {
            return;
        }
        for (CommandInterceptor interceptor : interceptors) {
            interceptor.beforeHandle(command);
        }
    }
}

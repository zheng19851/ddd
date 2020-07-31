package com.runssnail.ddd.commandhandling.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.eventhandling.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-06 16:30
 **/
public abstract class CommandHandlerAdaptor<C extends Command<R>, R extends Result> implements TransactionCommandHandler<C, R>, CommandHandler<C, R> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected EventBus eventBus;

    public CommandHandlerAdaptor() {
    }

    public CommandHandlerAdaptor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public R handleInTransaction(C command) {
        log.info("command handleInTransaction start, command={}", command);
        R resp = doHandleInTransaction(command);
        log.info("command handleInTransaction end, command={}, result={}", command, resp);
        return resp;
    }

    protected R doHandleInTransaction(C command) {
        return null;
    }

    @Override
    public R handle(C command) {
        log.info("command handle start, command={}", command);
        R resp = doHandle(command);
        log.info("command handle end, command={}, result={}", command, resp);
        return resp;
    }

    /**
     * 由子类实现
     *
     * @param command command
     * @return 结果
     */
    protected R doHandle(C command) {
        return null;
    }
}

package com.runssnail.ddd.command.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.event.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-06 16:30
 **/
public abstract class CommandHandlerAdaptor<C extends Command<T>, T extends Result> implements TransactionCommandHandler<C, T>, CommandHandler<C, T> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected EventBus eventBus;

    public CommandHandlerAdaptor() {
    }

    public CommandHandlerAdaptor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public T handleInTransaction(C command) {
        log.debug("command handleInTransaction start, command={}", command);
        T resp = doHandleInTransaction(command);
        log.debug("command handleInTransaction end, command={}, result={}", command, resp);
        return resp;
    }

    protected T doHandleInTransaction(C command) {
        return null;
    }

    @Override
    public T handle(C command) {
        log.debug("command handle start, command={}", command);
        T resp = doHandle(command);
        log.debug("command handle end, command={}, result={}", command, resp);
        return resp;
    }

    /**
     * 由子类实现
     *
     * @param command command
     * @return 结果
     */
    protected T doHandle(C command) {
        return null;
    }
}

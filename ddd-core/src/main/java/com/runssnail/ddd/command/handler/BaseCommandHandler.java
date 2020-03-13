package com.runssnail.ddd.command.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * @author zhengwei
 * @date 2019/3/12 5:16 PM
 **/
public abstract class BaseCommandHandler<C extends Command<T>, T extends Result> implements CommandHandler<C, T> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public BaseCommandHandler() {
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
    protected abstract T doHandle(C command);
}

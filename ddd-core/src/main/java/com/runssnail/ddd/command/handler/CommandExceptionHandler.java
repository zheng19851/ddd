package com.runssnail.ddd.command.handler;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * 异常处理器
 *
 * @author zhengwei
 */
public interface CommandExceptionHandler {

    /**
     * 处理异常
     *
     * @param command command
     * @param result  result
     * @param t       异常
     */
    <T extends Result> void onException(Command<T> command, T result, Throwable t);

}

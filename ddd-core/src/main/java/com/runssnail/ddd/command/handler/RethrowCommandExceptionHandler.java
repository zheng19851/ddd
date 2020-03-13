package com.runssnail.ddd.command.handler;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * 重新抛出异常
 *
 * @author zhengwei
 * @date 2020/3/7 3:41 下午
 **/
public class RethrowCommandExceptionHandler implements CommandExceptionHandler {

    @Override
    public <T extends Result> void onException(Command<T> command, T result, Throwable t) {

        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        }
        throw new RuntimeException(t);
    }
}

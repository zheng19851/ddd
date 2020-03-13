package com.runssnail.ddd.command.interceptor;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * @author zhengwei
 * @date 2019-11-08 13:31
 **/
public interface GlobalCommandInterceptor<C extends Command<T>, T extends Result> extends CommandInterceptor<C, T> {

    /**
     * global command不需要实现，默认支持所有command
     *
     * @return
     */
    @Override
    default Class supportCommandType() {
        return null;
    }
}

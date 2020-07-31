package com.runssnail.ddd.commandhandling.interceptor;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

/**
 * Global Command Interceptor
 * 用来实现全局拦截所有的命令请求，比如全局验证
 *
 * @author zhengwei
 * @date 2019-11-08 13:31
 * @see
 **/
public interface GlobalCommandInterceptor<C extends Command<R>, R extends BaseResult> extends CommandInterceptor<C, R> {

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

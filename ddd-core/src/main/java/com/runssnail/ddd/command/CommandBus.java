package com.runssnail.ddd.command;

import com.runssnail.ddd.command.handler.CommandHandler;
import com.runssnail.ddd.command.interceptor.CommandInterceptor;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * 命令总线
 *
 * @author zhengwei
 * @date 2019-11-04 15:35
 **/
public interface CommandBus {

    /**
     * 分发命令
     *
     * @param command command
     * @return 结果
     */
    <T extends Result> T dispatch(Command<T> command);

    /**
     * 注册命令处理器
     *
     * @param commandHandler 命令处理器
     */
    void registerCommandHandler(CommandHandler commandHandler);

    /**
     * 注册命令拦截器
     *
     * @param interceptor
     */
    void registerCommandInterceptor(CommandInterceptor interceptor);

}

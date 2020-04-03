package com.runssnail.ddd.command.handler;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * 命令处理器解析器
 *
 * @author zhengwei
 * @date 2019/3/12 4:56 PM
 **/
public interface CommandHandlerResolver {

    /**
     * 找到对应处理类
     *
     * @param command 请求
     * @return CommandHandler
     * @throws CannotFindCommandHandlerException
     */
    <C extends Command<T>, T extends Result> CommandHandler<C, T> resolve(Command<T> command) throws CannotFindCommandHandlerException;

    /**
     * 注册命令处理器(not threadsafe)
     *
     * @param commandHandler 命令处理器
     */
    void registerCommandHandler(CommandHandler commandHandler);
}

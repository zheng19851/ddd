package com.runssnail.ddd.command.interceptor;

import com.runssnail.ddd.common.command.Command;

import java.util.List;

/**
 * @author zhengwei
 * @date 2019-11-06 13:02
 **/
public interface CommandInterceptorResolver {

    /**
     * 找到命令对应的拦截器
     *
     * @param aCommandClass 命令类型
     * @return 拦截器
     */
    List<CommandInterceptor> resolveInterceptors(Class<? extends Command> aCommandClass);

    /**
     * 注册命令拦截器(not threadsafe)
     *
     * @param interceptor
     */
    void registerCommandInterceptor(CommandInterceptor interceptor);
}

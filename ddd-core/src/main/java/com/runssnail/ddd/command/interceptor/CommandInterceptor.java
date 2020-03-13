package com.runssnail.ddd.command.interceptor;


import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * 命令拦截器
 *
 * 支持排序
 *
 * @see org.springframework.core.annotation.Order
 * @see org.springframework.core.Ordered
 *
 * @author zhengwei
 * @param <T> Result
 */
public interface CommandInterceptor<C extends Command<T>, T extends Result> {

    /**
     * 支持的命令类型，返回null表示是一个全局CommandInterceptor（拦截所有command）
     *
     * @return 命令类型 or null
     */
    Class<C> supportCommandType();

    /**
     * 前置处理
     * @param command 命令
     */
    void beforeHandle(C command);

    /**
     * 后置处理
     * @param command 命令
     * @param result 结果
     */
    void afterHandle(C command, T result);
}

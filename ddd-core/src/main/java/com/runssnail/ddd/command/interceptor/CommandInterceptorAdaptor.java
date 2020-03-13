package com.runssnail.ddd.command.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * 命令拦截器
 * <p>
 * 支持排序
 *
 * @param <T> Result
 * @author zhengwei
 * @see org.springframework.core.annotation.Order
 * @see org.springframework.core.Ordered
 */
public abstract class CommandInterceptorAdaptor<C extends Command<T>, T extends Result> implements CommandInterceptor<C, T> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 前置处理
     *
     * @param command 命令
     */
    @Override
    public void beforeHandle(C command) {

    }

    /**
     * 后置处理
     *
     * @param command 命令
     * @param result  结果
     */
    @Override
    public void afterHandle(C command, T result) {

    }
}

package com.runssnail.ddd.commandhandling;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

/**
 * CommandInvocationFactory
 *
 * @author zhengwei
 * @date 2019-11-04 22:12
 **/
public interface CommandInvocationFactory {

    /**
     * 创建一个CommandInvocation
     *
     * @param command 命令
     * @param <T>     结果类型
     * @return
     */
    <T extends BaseResult> CommandInvocation<T> createCommandInvocation(Command<T> command);
}

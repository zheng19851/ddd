package com.runssnail.ddd.commandhandling;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

/**
 * @author zhengwei
 * @date 2019-11-04 22:12
 **/
public interface CommandInvocationFactory {

    <C extends Command<T>, T extends BaseResult> CommandInvocation<C, T> createCommandInvocation(Command<T> command);
}

package com.runssnail.ddd.command;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * @author zhengwei
 * @date 2019-11-04 22:12
 **/
public interface CommandInvocationFactory {

    <C extends Command<T>, T extends Result> CommandInvocation<C, T> createCommandInvocation(Command<T> command);
}

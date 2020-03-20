package com.runssnail.ddd.command.handler;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

/**
 * 默认开启事务的CommandHandler
 *
 * @author zhengwei
 * @date 2019-11-06 16:11
 **/
public interface TransactionCommandHandler<C extends Command<T>, T extends BaseResult> extends CommandHandler<C, T> {

    /**
     * 处理command
     *
     * @param command command
     * @return 结果
     */
    T handleInTransaction(C command);

}

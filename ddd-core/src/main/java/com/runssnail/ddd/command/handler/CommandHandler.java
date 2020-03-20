package com.runssnail.ddd.command.handler;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

/**
 * 命令处理器
 *
 * @author zhengwei
 * @date 2019/3/12 3:41 PM
 **/
public interface CommandHandler<C extends Command<T>, T extends BaseResult> {

    /**
     * 是否支持当前命令
     *
     * @return 类型
     */
    Class<C> supportCommand();

    /**
     * 处理command
     *
     * @param command command
     * @return 结果
     */
    T handle(C command);

}

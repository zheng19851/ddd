package com.runssnail.ddd.common.validator;

import com.runssnail.ddd.common.command.Command;

/**
 * 全局命令验证器
 * 可以用来统一验证使用注解的command
 *
 * @author zhengwei
 * @date 2019-12-23 20:26
 **/
public interface GlobalValidator<T extends Command> extends Validator<T> {

    /**
     * 默认都支持
     *
     * @return
     */
    @Override
    default Class<T> supportType() {
        return null;
    }

    /**
     * 是否支持当前命令，默认都支持
     *
     * @param command 命令对象
     * @return
     */
    default boolean supportCommand(T command) {
        return true;
    }

}

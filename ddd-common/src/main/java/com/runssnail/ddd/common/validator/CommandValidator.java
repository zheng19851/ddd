package com.runssnail.ddd.common.validator;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.exception.BizException;

/**
 * 验证命令
 *
 * 一个命令对应一个Validator
 *
 * @author zhengwei
 * @date 2019-11-05 20:14
 **/
public interface CommandValidator<T extends Command> {

    /**
     * 支持的类型
     *
     * @return
     */
    Class<T> supportType();

    /**
     * 验证
     *
     * @param command 需要验证的参数
     * @throws IllegalArgumentException
     * @throws BizException
     */
    void validate(T command) throws IllegalArgumentException, BizException;
}

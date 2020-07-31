package com.runssnail.ddd.common.command;

import java.io.Serializable;

import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.result.BaseResult;
import com.runssnail.ddd.common.validator.CommandValidator;

/**
 * 命令
 *
 * @author zhengwei
 * @date 2019/3/12 3:40 PM
 **/
public interface Command<R extends BaseResult> extends Serializable {

    /**
     * 检查参数是否正确
     *
     * @param validator 验证器
     * @throws IllegalArgumentException
     * @throws BizException
     */
    void validate(CommandValidator<Command<R>> validator) throws IllegalArgumentException, BizException;

    /**
     * 结果类型
     *
     * @return
     */
    Class<R> resultType();
}

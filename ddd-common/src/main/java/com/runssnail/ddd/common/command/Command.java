package com.runssnail.ddd.common.command;

import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.validator.Validator;

import java.io.Serializable;

import com.runssnail.ddd.common.result.Result;

/**
 * 命令
 *
 * @author zhengwei
 * @date 2019/3/12 3:40 PM
 **/
public interface Command<T extends Result> extends Serializable {

    /**
     * 检查参数是否正确
     *
     * @throws IllegalArgumentException
     * @throws BizException
     * @param validator 验证器
     */
    void validate(Validator validator) throws IllegalArgumentException, BizException;

    /**
     * 结果类型
     *
     * @return
     */
    Class<T> resultType();
}

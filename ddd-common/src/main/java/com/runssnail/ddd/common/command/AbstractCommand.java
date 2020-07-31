package com.runssnail.ddd.common.command;

import com.runssnail.ddd.common.dto.BaseDTO;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.result.BaseResult;
import com.runssnail.ddd.common.validator.CommandValidator;

/**
 * @author zhengwei
 * @date 2019-11-07 15:12
 **/
public abstract class AbstractCommand<T extends BaseResult> extends BaseDTO implements Command<T> {

    @Override
    public void validate(CommandValidator<Command<T>> validator) throws IllegalArgumentException, BizException {
        if (validator != null) {
            validator.validate(this);
        }
    }

}

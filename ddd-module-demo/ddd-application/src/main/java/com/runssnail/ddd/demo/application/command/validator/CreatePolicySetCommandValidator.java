package com.runssnail.ddd.demo.application.command.validator;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.validator.Validator;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;

/**
 * 创建策略集CommandValidator
 *
 * @author zhengwei
 * @date 2019-11-08 16:30
 **/
@Component
public class CreatePolicySetCommandValidator implements Validator<CreatePolicySetCommand> {

    @Override
    public Class<CreatePolicySetCommand> supportType() {
        return CreatePolicySetCommand.class;
    }

    @Override
    public void validate(CreatePolicySetCommand createPolicySetCommand) throws IllegalArgumentException, BizException {
        Validate.notNull(createPolicySetCommand);
        Validate.notNull(createPolicySetCommand.getName());
        Validate.isTrue(createPolicySetCommand.getName().length() <= 10);
    }
}

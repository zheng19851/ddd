package com.runssnail.ddd.demo.application.command.validator;

import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.validator.CommandValidator;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * 创建策略CommandValidator
 *
 * @author zhengwei
 * @date 2019-11-08 16:30
 **/
@Component
public class CreatePolicyCommandValidator implements CommandValidator<CreatePolicyCommand> {

    @Override
    public Class<CreatePolicyCommand> supportType() {
        return CreatePolicyCommand.class;
    }

    @Override
    public void validate(CreatePolicyCommand command) throws IllegalArgumentException, BizException {
        Validate.notNull(command);
        Validate.notNull(command.getName());
        Validate.isTrue(command.getName().length() <= 20, "name cannot over length 20");
    }
}

package com.runssnail.ddd.demo.application.commandhandling.validator;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.validator.CommandValidator;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;

/**
 * @author zhengwei
 * @date 2019-11-08 16:30
 **/
@Component
public class CreateProductCommandValidator implements CommandValidator<CreateProductCommand> {

    @Override
    public Class<CreateProductCommand> supportType() {
        return CreateProductCommand.class;
    }

    @Override
    public void validate(CreateProductCommand createProductCommand) throws IllegalArgumentException, BizException {
        Validate.notNull(createProductCommand);
        Validate.notNull(createProductCommand.getName());
        Validate.isTrue(createProductCommand.getName().length() <= 10);
    }
}

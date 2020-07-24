package com.runssnail.ddd.commandhandling.validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.exception.BasicErrorCode;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.validator.GlobalCommandValidator;

import lombok.Getter;
import lombok.Setter;

/**
 * 注解验证命令参数
 *
 * @author zhengwei
 * @date 2019-12-28 14:26
 **/
@Setter
@Getter
public class AnnotationParamGlobalCommandValidator implements GlobalCommandValidator<Command> {

    private Validator validator;

    @Override
    public void validate(Command command) throws IllegalArgumentException, BizException {
        Set<ConstraintViolation<Command>> constraintViolations = this.validator.validate(command);
        if (CollectionUtils.isNotEmpty(constraintViolations)) {

            List<String> messages = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

            String message = StringUtils.join(messages, ";");
            throw new BizException(BasicErrorCode.PARAMS_ERROR.getErrorCode(), message, message);
        }
    }
}

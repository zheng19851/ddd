package com.runssnail.ddd.command.interceptor;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import com.runssnail.ddd.command.validator.CommandValidatorResolver;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.common.validator.GlobalValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * 验证command
 *
 * @author zhengwei
 * @date 2019-11-06 16:33
 **/
@Slf4j
public class ValidateCommandInterceptor implements GlobalCommandInterceptor {

    private CommandValidatorResolver commandValidatorResolver;

    @Override
    public void beforeHandle(Command command) {
        log.debug("CommandValidateInterceptor.beforeHandle start, command={}", command);

        List<GlobalValidator> globalValidators = commandValidatorResolver.getGlobalValidators();
        if (CollectionUtils.isNotEmpty(globalValidators)) {
            for (GlobalValidator globalValidator : globalValidators) {
                if (globalValidator.supportCommand(command)) {
                    globalValidator.validate(command);
                }
            }
        }

        command.validate(commandValidatorResolver.resolve(command));
        log.debug("CommandValidateInterceptor.beforeHandle end, command={}", command);
    }

    @Override
    public void afterHandle(Command command, Result result) {

    }

    public CommandValidatorResolver getCommandValidatorResolver() {
        return commandValidatorResolver;
    }

    public void setCommandValidatorResolver(CommandValidatorResolver commandValidatorResolver) {
        this.commandValidatorResolver = commandValidatorResolver;
    }
}

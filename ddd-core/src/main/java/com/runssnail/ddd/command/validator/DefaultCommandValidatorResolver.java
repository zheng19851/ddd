package com.runssnail.ddd.command.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.common.validator.GlobalValidator;
import com.runssnail.ddd.common.validator.Validator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengwei
 * @date 2019-11-07 15:37
 **/
@Slf4j
public class DefaultCommandValidatorResolver implements CommandValidatorResolver {

    private Map<Class, Validator> commandValidatorMapping = new ConcurrentHashMap<>();

    private List<GlobalValidator> globalValidators = new ArrayList<>();

    @Override
    public <C extends Command<T>, T extends Result> Validator<C> resolve(Command<T> command) {
        if (command == null) {
            return null;
        }
        return commandValidatorMapping.get(command.getClass());
    }

    @Override
    public <C extends Command> void registerValidator(Validator<C> validator) {
        if (validator instanceof GlobalValidator) {
            this.globalValidators.add((GlobalValidator) validator);
            return;
        }

        Class requestType = validator.supportType();
        if (requestType == null) {
            throw new RuntimeException("The Validator's support type is required, '" + validator + "'");
        }

        if (commandValidatorMapping.containsKey(requestType)) {
            throw new RuntimeException("The Validator duplicated, '" + requestType + "'");
        }

        this.commandValidatorMapping.put(validator.supportType(), validator);
    }

    @Override
    public List<GlobalValidator> getGlobalValidators() {
        return this.globalValidators;
    }

    @PostConstruct
    public void init() {

    }

}

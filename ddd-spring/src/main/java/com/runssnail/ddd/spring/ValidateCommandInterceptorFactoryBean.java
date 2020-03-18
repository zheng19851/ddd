package com.runssnail.ddd.spring;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.runssnail.ddd.command.interceptor.GlobalCommandInterceptor;
import com.runssnail.ddd.command.interceptor.ValidateCommandInterceptor;
import com.runssnail.ddd.command.validator.CommandValidatorResolver;
import com.runssnail.ddd.command.validator.DefaultCommandValidatorResolver;
import com.runssnail.ddd.common.validator.CommandValidator;
import com.runssnail.ddd.spring.interceptor.OrderedValidateCommandInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author zhengwei
 * @date 2019-11-07 17:56
 **/
@Component
@Slf4j
public class ValidateCommandInterceptorFactoryBean implements FactoryBean<GlobalCommandInterceptor>, ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private GlobalCommandInterceptor validateCommandInterceptor;

    @Override
    public GlobalCommandInterceptor getObject() throws Exception {
        return validateCommandInterceptor;
    }

    @Override
    public Class<?> getObjectType() {
        return GlobalCommandInterceptor.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidateCommandInterceptor validateCommandInterceptor = new ValidateCommandInterceptor();

        CommandValidatorResolver commandValidatorResolver = createCommandValidatorResolver();
        validateCommandInterceptor.setCommandValidatorResolver(commandValidatorResolver);

        this.validateCommandInterceptor = new OrderedValidateCommandInterceptor(validateCommandInterceptor);
    }

    private CommandValidatorResolver createCommandValidatorResolver() {
        CommandValidatorResolver commandValidatorResolver = new DefaultCommandValidatorResolver();

        Map<String, CommandValidator> beansOfValidators = this.applicationContext.getBeansOfType(CommandValidator.class);

        if (MapUtils.isEmpty(beansOfValidators)) {
            log.info("can not find any Validator in spring context");
            return commandValidatorResolver;
        }

        log.info("find Validator in spring context {}", beansOfValidators.size());

        List<CommandValidator> validators = new ArrayList<>(beansOfValidators.values());

        AnnotationAwareOrderComparator.sort(validators);

        for (CommandValidator validator : validators) {
            commandValidatorResolver.registerValidator(validator);
        }

        log.info("find Validator final {}", validators);

        return commandValidatorResolver;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.command.validator.AnnotationParamGlobalCommandValidator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 注解参数命令验证器自动配置
 *
 * @author zhengwei
 * @date 2020/3/5 11:27 上午
 **/
@Configuration
@Slf4j
public class AnnotationParamCommandValidatorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AnnotationParamGlobalCommandValidator.class)
    public AnnotationParamGlobalCommandValidator paramGlobalCommandValidator() {

        AnnotationParamGlobalCommandValidator commandValidator = new AnnotationParamGlobalCommandValidator();
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().failFast(true)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        commandValidator.setValidator(validator);

        log.info("Register AnnotationParamGlobalCommandValidator success");

        return commandValidator;
    }


}

package com.runssnail.ddd.spring.interceptor;

import com.runssnail.ddd.command.interceptor.GlobalCommandInterceptor;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证command
 *
 * @author zhengwei
 * @date 2019-11-06 16:33
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class OrderedValidateCommandInterceptor implements GlobalCommandInterceptor {

    private GlobalCommandInterceptor validateCommandInterceptor;

    public OrderedValidateCommandInterceptor(GlobalCommandInterceptor validateCommandInterceptor) {
        this.validateCommandInterceptor = validateCommandInterceptor;
    }

    @Override
    public void beforeHandle(Command command) {
        validateCommandInterceptor.beforeHandle(command);
    }

    @Override
    public void afterHandle(Command command, BaseResult result) {

    }

    public GlobalCommandInterceptor getValidateCommandInterceptor() {
        return validateCommandInterceptor;
    }

    public void setValidateCommandInterceptor(GlobalCommandInterceptor validateCommandInterceptor) {
        this.validateCommandInterceptor = validateCommandInterceptor;
    }
}

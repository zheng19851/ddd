package com.runssnail.ddd.demo.application.command.interceptor.policyset;

import com.runssnail.ddd.command.interceptor.CommandInterceptor;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-06 12:03
 **/
@Component
@Order(1)
public class CreatePolicySetInterceptor implements CommandInterceptor<CreatePolicySetCommand, Result> {

    private static final Logger log = LoggerFactory.getLogger(CreatePolicySetInterceptor.class);

    @Override
    public Class<CreatePolicySetCommand> supportCommandType() {
        return CreatePolicySetCommand.class;
    }

    @Override
    public void beforeHandle(CreatePolicySetCommand command) {
        log.info("CreatePolicySetInterceptor.preHandle");
    }

    @Override
    public void afterHandle(CreatePolicySetCommand command, Result result) {
        log.info("CreatePolicySetInterceptor.postHandle");
    }
}

package com.runssnail.ddd.demo.application.command.interceptor.policyset;

import com.runssnail.ddd.command.interceptor.CommandInterceptor;
import com.runssnail.ddd.common.result.SingleResult;
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
@Order(2)
public class Order2CreatePolicySetInterceptor implements CommandInterceptor<CreatePolicySetCommand, SingleResult> {

    private static final Logger log = LoggerFactory.getLogger(Order2CreatePolicySetInterceptor.class);

    @Override
    public Class<CreatePolicySetCommand> supportCommandType() {
        return CreatePolicySetCommand.class;
    }

    @Override
    public void beforeHandle(CreatePolicySetCommand command) {
        log.info("Order2CreatePolicySetInterceptor.preHandle");
    }

    @Override
    public void afterHandle(CreatePolicySetCommand command, SingleResult result) {
        log.info("Order2CreatePolicySetInterceptor.postHandle");
    }
}

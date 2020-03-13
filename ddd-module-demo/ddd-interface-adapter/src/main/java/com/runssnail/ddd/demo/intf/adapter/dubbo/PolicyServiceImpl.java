package com.runssnail.ddd.demo.intf.adapter.dubbo;

import com.runssnail.ddd.command.CommandBus;
import com.runssnail.ddd.common.result.SingleResult;
import com.runssnail.ddd.demo.client.api.PolicyService;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-07 10:03
 **/
@Component
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private CommandBus commandBus;

    @Override
    public SingleResult<String> createPolicy(CreatePolicyCommand command) {
        return commandBus.dispatch(command);
    }
}

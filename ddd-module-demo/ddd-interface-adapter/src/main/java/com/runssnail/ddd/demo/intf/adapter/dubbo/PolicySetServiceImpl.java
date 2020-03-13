package com.runssnail.ddd.demo.intf.adapter.dubbo;

import com.runssnail.ddd.command.CommandBus;
import com.runssnail.ddd.common.result.SingleResult;
import com.runssnail.ddd.demo.client.api.PolicySetService;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.ActivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetVersionCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.DeactivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.RemovePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.result.policy.CreatePolicyResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.ActivatePolicySetResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.CreatePolicySetVersionResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.DeactivatePolicySetResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.RemovePolicySetResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-07 10:03
 **/
@Component
public class PolicySetServiceImpl implements PolicySetService {

    @Autowired
    private CommandBus commandBus;

    @Override
    public SingleResult<String> createPolicySet(CreatePolicySetCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public SingleResult<String> createPolicy(CreatePolicyCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public RemovePolicySetResult removePolicySet(RemovePolicySetCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public ActivatePolicySetResult activatePolicySet(ActivatePolicySetCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public DeactivatePolicySetResult deactivatePolicySet(DeactivatePolicySetCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public CreatePolicySetVersionResult createPolicySetVersion(CreatePolicySetVersionCommand command) {
        return commandBus.dispatch(command);
    }
}

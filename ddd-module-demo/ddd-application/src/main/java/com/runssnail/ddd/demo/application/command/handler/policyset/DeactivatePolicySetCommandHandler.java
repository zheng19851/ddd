package com.runssnail.ddd.demo.application.command.handler.policyset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.repository.PolicySetRepository;
import com.runssnail.ddd.demo.client.dto.command.policyset.DeactivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.DeactivatePolicySetResult;
import com.runssnail.ddd.demo.domain.event.policyset.PolicySetDeactivatedEvent;
import com.runssnail.ddd.event.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class DeactivatePolicySetCommandHandler extends BaseCommandHandler<DeactivatePolicySetCommand, DeactivatePolicySetResult> {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<DeactivatePolicySetCommand> supportCommand() {
        return DeactivatePolicySetCommand.class;
    }

    @Override
    public DeactivatePolicySetResult doHandle(DeactivatePolicySetCommand command) {

        PolicySet policySet = policySetRepository.selectById(command.getPolicySetId());

        // 禁用
        policySet.deactivate(command);

        // 保存数据
        policySetRepository.deactivate(policySet);

        // 发布领域事件
        eventBus.publish(new PolicySetDeactivatedEvent(policySet.getPolicySetId()));

        DeactivatePolicySetResult result = new DeactivatePolicySetResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setPolicySetId(policySet.getPolicySetId());
        return result;
    }
}

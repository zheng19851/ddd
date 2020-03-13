package com.runssnail.ddd.demo.application.command.handler.policyset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.repository.PolicySetRepository;
import com.runssnail.ddd.demo.client.dto.command.policyset.RemovePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.RemovePolicySetResult;
import com.runssnail.ddd.demo.domain.event.policyset.PolicySetRemovedEvent;
import com.runssnail.ddd.event.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class RemovePolicySetCommandHandler extends BaseCommandHandler<RemovePolicySetCommand, RemovePolicySetResult> {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<RemovePolicySetCommand> supportCommand() {
        return RemovePolicySetCommand.class;
    }

    @Override
    public RemovePolicySetResult doHandle(RemovePolicySetCommand command) {

        PolicySet policySet = policySetRepository.selectById(command.getPolicySetId());

        policySet.remove(command);

        // 删除
        policySetRepository.remove(policySet);

        // 发布领域事件
        eventBus.publish(new PolicySetRemovedEvent(policySet.getPolicySetId()));

        RemovePolicySetResult result = new RemovePolicySetResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setPolicySetId(policySet.getPolicySetId());
        return result;
    }
}

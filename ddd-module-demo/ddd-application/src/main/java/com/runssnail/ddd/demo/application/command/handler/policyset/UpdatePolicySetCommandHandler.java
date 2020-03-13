package com.runssnail.ddd.demo.application.command.handler.policyset;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.policyset.UpdatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.UpdatePolicySetResult;
import com.runssnail.ddd.demo.domain.event.policyset.PolicySetUpdatedEvent;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.repository.PolicySetRepository;
import com.runssnail.ddd.event.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class UpdatePolicySetCommandHandler extends BaseCommandHandler<UpdatePolicySetCommand, UpdatePolicySetResult> {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public UpdatePolicySetResult doHandle(UpdatePolicySetCommand command) {

        // 转换领域对象
        PolicySet policySet = policySetRepository.selectById(command.getId());
        policySet.update(command);

        policySetRepository.update(policySet);

        // 发布领域事件
        eventBus.publish(new PolicySetUpdatedEvent(policySet.getPolicySetId()));

        UpdatePolicySetResult result = new UpdatePolicySetResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setPolicySetId(policySet.getPolicySetId());
        return result;
    }

    @Override
    public Class<UpdatePolicySetCommand> supportCommand() {
        return UpdatePolicySetCommand.class;
    }

}

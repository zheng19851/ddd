package com.runssnail.ddd.demo.application.command.handler.policyset;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.policyset.ActivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.ActivatePolicySetResult;
import com.runssnail.ddd.demo.domain.event.policyset.PolicySetDeactivatedEvent;
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
public class ActivatePolicySetCommandHandler extends BaseCommandHandler<ActivatePolicySetCommand, ActivatePolicySetResult> {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<ActivatePolicySetCommand> supportCommand() {
        return ActivatePolicySetCommand.class;
    }

    @Override
    public ActivatePolicySetResult doHandle(ActivatePolicySetCommand command) {

        PolicySet policySet = policySetRepository.selectById(command.getPolicySetId());

        // 启用
        policySet.activate(command);

        // 保存数据
        policySetRepository.activate(policySet);

        // 发布领域事件
        eventBus.publish(new PolicySetDeactivatedEvent(policySet.getPolicySetId()));

        ActivatePolicySetResult result = new ActivatePolicySetResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setPolicySetId(policySet.getPolicySetId());
        return result;
    }
}

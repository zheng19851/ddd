package com.runssnail.ddd.demo.application.command.handler.policyset;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetVersionCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.CreatePolicySetVersionResult;
import com.runssnail.ddd.demo.domain.event.policyset.PolicySetVersionCreatedEvent;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.repository.PolicySetRepository;
import com.runssnail.ddd.demo.domain.service.PolicySetDomainService;
import com.runssnail.ddd.event.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class CreatePolicySetVersionCommandHandler extends BaseCommandHandler<CreatePolicySetVersionCommand, CreatePolicySetVersionResult> {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private PolicySetDomainService policySetDomainService;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<CreatePolicySetVersionCommand> supportCommand() {
        return CreatePolicySetVersionCommand.class;
    }

    @Override
    public CreatePolicySetVersionResult doHandle(CreatePolicySetVersionCommand command) {

        PolicySet policySet = policySetRepository.selectById(command.getPolicySetId());

        policySet.createVersion(this.policySetDomainService.nextId(), command);

        // 保存数据
        policySetRepository.save(policySet);

        // 发布领域事件
        eventBus.publish(new PolicySetVersionCreatedEvent(policySet.getPolicySetId()));

        CreatePolicySetVersionResult result = new CreatePolicySetVersionResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setPolicySetId(policySet.getPolicySetId());
        return result;
    }
}

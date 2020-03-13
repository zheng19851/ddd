package com.runssnail.ddd.demo.application.command.handler.policy;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.SingleResult;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;
import com.runssnail.ddd.demo.domain.event.policy.PolicyCreatedEvent;
import com.runssnail.ddd.demo.domain.model.policy.Policy;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.repository.PolicyRepository;
import com.runssnail.ddd.demo.domain.repository.PolicySetRepository;
import com.runssnail.ddd.demo.domain.service.PolicyDomainService;
import com.runssnail.ddd.event.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创建策略
 *
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class CreatePolicyCommandHandler extends BaseCommandHandler<CreatePolicyCommand, SingleResult> {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyDomainService policyDomainService;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<CreatePolicyCommand> supportCommand() {
        return CreatePolicyCommand.class;
    }

    @Override
    public SingleResult<String> doHandle(CreatePolicyCommand command) {

        PolicySet policySet = policySetRepository.selectById(command.getPolicySetId());

        // 领域对象
        Policy policy = policySet.createPolicy(policyDomainService.nextId(), command.getName(), command.getDescription());

        // 保存数据
        policyRepository.save(policy);

        // 发布领域事件
        eventBus.publish(new PolicyCreatedEvent(policy.getPolicyId()));

        return SingleResult.create(policy.getPolicyId().getId());
    }
}

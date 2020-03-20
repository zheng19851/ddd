package com.runssnail.ddd.demo.application.command.handler.policyset;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;
import com.runssnail.ddd.demo.domain.event.policyset.PolicySetCreatedEvent;
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
public class CreatePolicySetCommandHandler extends BaseCommandHandler<CreatePolicySetCommand, Result> {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private PolicySetDomainService policySetDomainService;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<CreatePolicySetCommand> supportCommand() {
        return CreatePolicySetCommand.class;
    }

    @Override
    public Result<String> doHandle(CreatePolicySetCommand command) {

        // 转换领域对象
        PolicySet policySet = this.policySetDomainService.createPolicySet(command);

        // 保存数据
        policySetRepository.save(policySet);

        // 发布领域事件
        eventBus.publish(new PolicySetCreatedEvent(policySet.getPolicySetId()));

        return Result.success(policySet.getPolicySetId());
    }
}

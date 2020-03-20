package com.runssnail.ddd.demo.application.command.handler.rule;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;
import com.runssnail.ddd.demo.domain.event.rule.RuleCreatedEvent;
import com.runssnail.ddd.demo.domain.model.policy.Policy;
import com.runssnail.ddd.demo.domain.model.rule.Rule;
import com.runssnail.ddd.demo.domain.repository.PolicyRepository;
import com.runssnail.ddd.demo.domain.repository.RuleRepository;
import com.runssnail.ddd.demo.domain.service.RuleDomainService;
import com.runssnail.ddd.event.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class CreateRuleCommandHandler extends BaseCommandHandler<CreateRuleCommand, Result> {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleDomainService ruleDomainService;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<CreateRuleCommand> supportCommand() {
        return CreateRuleCommand.class;
    }

    @Override
    protected Result doHandle(CreateRuleCommand command) {

        // 获取策略领域对象
        Policy policy = policyRepository.selectById(command.getPolicyId());

        // 创建规则领域对象，"policy.createRule"表达"策略下面可以添加规则"业务规则
        Rule rule = policy.createRule(ruleDomainService.nextId(), command.getRuleName(), command.getRiskWeightConfig(), command.getConditions(), command.getActions());

        // 保存规则领域对象
        ruleRepository.saveRule(rule);

        //log.info("create rule finished, ruleId={}", rule.getRuleId().getId());

        // 发布领域事件
        eventBus.publish(new RuleCreatedEvent(rule.getRuleId()));

        return Result.success(rule.getRuleId());
    }

}

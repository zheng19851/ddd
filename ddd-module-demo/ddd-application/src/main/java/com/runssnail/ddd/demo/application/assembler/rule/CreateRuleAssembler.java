package com.runssnail.ddd.demo.application.assembler.rule;

import com.runssnail.ddd.assembler.Assembler;
import com.runssnail.ddd.demo.domain.service.RuleDomainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;
import com.runssnail.ddd.demo.domain.model.rule.Rule;

/**
 * @author zhengwei
 * @date 2019-11-05 18:10
 **/
@Component
public class CreateRuleAssembler implements Assembler<CreateRuleCommand, Rule> {

    @Autowired
    private RuleDomainService ruleDomainService;

    @Override
    public Rule assemble(CreateRuleCommand command) {
        // todo 实现规则命令到规则领域对象的转换
        Rule rule = new Rule(ruleDomainService.nextId());
        return rule;
    }
}

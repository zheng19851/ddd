package com.runssnail.ddd.demo.intf.adapter.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.ddd.demo.application.service.RuleApplicationService;
import com.runssnail.ddd.demo.client.api.RuleService;
import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;
import com.runssnail.ddd.demo.client.dto.result.rule.CreateRuleResult;

/**
 * @author zhengwei
 * @date 2019-11-09 14:20
 **/
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleApplicationService ruleApplicationService;

    @Override
    public CreateRuleResult createRule(CreateRuleCommand command) {
        return ruleApplicationService.createRule(command);
    }
}

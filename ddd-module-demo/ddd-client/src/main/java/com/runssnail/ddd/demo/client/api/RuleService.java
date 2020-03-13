package com.runssnail.ddd.demo.client.api;

import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;
import com.runssnail.ddd.demo.client.dto.result.rule.CreateRuleResult;

/**
 * @author zhengwei
 * @date 2019-11-06 10:54
 **/
public interface RuleService {

    CreateRuleResult createRule(CreateRuleCommand command);
}

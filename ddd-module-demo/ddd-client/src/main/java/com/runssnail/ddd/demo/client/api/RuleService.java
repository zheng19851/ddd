package com.runssnail.ddd.demo.client.api;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;

/**
 * @author zhengwei
 * @date 2019-11-06 10:54
 **/
public interface RuleService {

    Result<String> createRule(CreateRuleCommand command);
}

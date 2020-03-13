package com.runssnail.ddd.demo.client.dto.result.rule;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;

import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class CreateRuleResult extends Result {

    private RuleId ruleId;

}

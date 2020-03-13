package com.runssnail.ddd.demo.domain.model.policy;

import com.runssnail.ddd.common.domain.Entity;
import com.runssnail.ddd.demo.client.dto.domain.policy.PolicyId;
import com.runssnail.ddd.demo.client.dto.domain.policyset.PolicySetId;
import com.runssnail.ddd.demo.client.dto.domain.rule.RiskWeightConfig;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleAction;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleConditionDTO;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;
import com.runssnail.ddd.demo.domain.model.rule.Rule;

import java.util.List;

import lombok.Data;

/**
 * 策略
 *
 * @author zhengwei
 * @date 2019-11-05 14:20
 **/
@Data
public class Policy extends Entity {

    private PolicyId policyId;

    /**
     * 策略集ID
     */
    private PolicySetId policySetId;

    private String partnerCode;

    private String name;

    private String eventType;

    private String description;

    public Policy(PolicyId policyId, String partnerCode, String eventType, String name, String description) {
        this.policyId = policyId;
        this.partnerCode = partnerCode;
        this.eventType = eventType;
        this.name = name;
        this.description = description;

    }

    public Policy(PolicyId policyId) {
        this.policyId = policyId;
    }


    public Rule createRule(RuleId ruleId, String ruleName, RiskWeightConfig riskWeightConfig, List<RuleConditionDTO> conditions, List<RuleAction> actions) {
        // todo 验证策略状态是否能创建规则

        Rule rule = new Rule(ruleId);
        rule.setName(ruleName);

        // todo 设置其他属性
        return rule;
    }
}

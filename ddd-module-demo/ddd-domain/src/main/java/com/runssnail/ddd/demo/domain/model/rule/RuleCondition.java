package com.runssnail.ddd.demo.domain.model.rule;

import com.runssnail.ddd.common.domain.Entity;
import com.runssnail.ddd.demo.client.dto.domain.Property;
import com.runssnail.ddd.demo.client.dto.domain.rule.LogicOperatorEnum;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;

import java.util.List;

import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 15:51
 **/
@Data
public class RuleCondition extends Entity {

    private RuleConditionId ruleConditionId;

    private RuleId ruleId;

    private LogicOperatorEnum LogicOperator;

    private Property leftValue;
    private Property rightValue;

    private List<RuleCondition> subConditions;
}

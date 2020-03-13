package com.runssnail.ddd.demo.domain.model.rule;

import org.apache.commons.lang3.Validate;

import java.util.List;

import com.runssnail.ddd.common.domain.Entity;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.demo.client.dto.domain.policy.PolicyId;
import com.runssnail.ddd.demo.client.dto.domain.rule.LimitScore;
import com.runssnail.ddd.demo.client.dto.domain.rule.OperateCodeEnum;
import com.runssnail.ddd.demo.client.dto.domain.rule.RiskWeightConfig;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleAction;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleStatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * 规则
 *
 * @author zhengwei
 * @date 2019-11-05 14:29
 **/
@Getter
@ToString
@Setter
public class Rule extends Entity {

    private RuleId ruleId;

    private PolicyId policyId;

    private String name;

    private OperateCodeEnum operateCode;

    private String riskType;
    private String description;
    private String displayOrder;
    private String template;
    private Boolean pilotRun;

    private LimitScore limitScore;
    private RiskWeightConfig riskWeightConfig;

    private List<RuleCondition> conditions;

    private List<RuleAction> actions;

    private RuleStatusEnum status;

    public Rule(RuleId ruleId) {
        Validate.notNull(ruleId);
        this.ruleId = ruleId;
    }

    public void name(String name) {
        Validate.isTrue(name.length() <= 60);
        this.name = name;
    }

    @Override
    public void validate() throws BizException {

    }
}

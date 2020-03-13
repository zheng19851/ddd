package com.runssnail.ddd.demo.client.dto.command.rule;

import java.util.List;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.demo.client.dto.domain.rule.OperateCodeEnum;
import com.runssnail.ddd.demo.client.dto.domain.rule.RiskWeightConfig;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleAction;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleConditionDTO;
import com.runssnail.ddd.demo.client.dto.result.rule.CreateRuleResult;

import lombok.Data;

/**
 * 创建规则
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class CreateRuleCommand extends AbstractCommand<CreateRuleResult> implements Command<CreateRuleResult> {

    private String policyId;

    private String partnerCode;

    private String ruleName;

    private String riskType;

    private String description;

    /**
     * @see OperateCodeEnum
     */
    private String operateCode;

    private Integer displayOrder;

    private String template;

    /**
     * 是否试运行
     */
    private Boolean pilotRun;

    private RiskWeightConfig riskWeightConfig;

    /**
     * 条件
     */
    private List<RuleConditionDTO> conditions;

    /**
     * 动作
     */
    private List<RuleAction> actions;

    @Override
    public Class<CreateRuleResult> resultType() {
        return CreateRuleResult.class;
    }
}

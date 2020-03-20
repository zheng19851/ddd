package com.runssnail.ddd.demo.bootstrap;


import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.command.handler.rule.CreateRuleCommandHandler;
import com.runssnail.ddd.demo.application.service.RuleApplicationService;
import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;
import com.runssnail.ddd.demo.client.dto.domain.rule.LogicOperatorEnum;
import com.runssnail.ddd.demo.client.dto.domain.rule.OperateCodeEnum;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleConditionDTO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @see CreateRuleCommandHandler
 *
 * @author zhengwei
 * @date 2019-11-05 17:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleApplicationServiceTest {

    @Autowired
    private RuleApplicationService ruleApplicationService;

    @Test
    public  void testCreateRule() {
        CreateRuleCommand command = new CreateRuleCommand();
        command.setPolicyId("policyId");
        command.setPartnerCode("demo");
        command.setDescription("demo");
        command.setOperateCode(OperateCodeEnum.accept.name());
        command.setDisplayOrder(1);

        command.setRuleName("test");
        command.setPilotRun(false);
        command.setTemplate("custom/custom");

        List<RuleConditionDTO> conditions = new ArrayList<>();
        RuleConditionDTO conditionDTO = new RuleConditionDTO();
        conditionDTO.setLogicOperator(LogicOperatorEnum.gt.name());
        conditionDTO.setLeftProperty("idNumber");
        conditionDTO.setLeftPropertyType("STRING");
        conditionDTO.setRightProperty("fff");
        conditionDTO.setRightPropertyType("input");

        command.setConditions(conditions);

        Result result = ruleApplicationService.createRule(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData());
    }
}

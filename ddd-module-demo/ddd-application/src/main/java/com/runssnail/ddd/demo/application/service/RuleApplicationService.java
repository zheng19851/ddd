package com.runssnail.ddd.demo.application.service;

import com.runssnail.ddd.command.CommandBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;
import com.runssnail.ddd.demo.client.dto.result.rule.CreateRuleResult;

/**
 * @author zhengwei
 * @date 2019-11-05 14:38
 **/
@Component
public class RuleApplicationService {

    @Autowired
    private CommandBus commandBus;

    /**
     * 创建规则
     *
     * @param command
     * @return
     */
    public CreateRuleResult createRule(CreateRuleCommand command) {
        return commandBus.dispatch(command);
    }
}

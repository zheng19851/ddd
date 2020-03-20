package com.runssnail.ddd.demo.application.service;

import com.runssnail.ddd.command.CommandBus;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.rule.CreateRuleCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public Result<String> createRule(CreateRuleCommand command) {
        return commandBus.dispatch(command);
    }
}

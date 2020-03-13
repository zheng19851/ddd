package com.runssnail.ddd.demo.application.service;

import com.runssnail.ddd.command.CommandBus;
import com.runssnail.ddd.common.result.SingleResult;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengwei
 * @date 2019-11-05 14:38
 **/
@Component
public class PolicySetApplicationService {

    @Autowired
    private CommandBus commandBus;

    /**
     * 创建策略集
     *
     * @param command
     * @return
     */
    @Transactional
    public SingleResult createPolicySet(CreatePolicySetCommand command) {
        return commandBus.dispatch(command);
    }
}

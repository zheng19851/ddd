package com.runssnail.ddd.demo.client.api;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;

/**
 * @author zhengwei
 * @date 2019-11-06 10:54
 **/
public interface PolicyService {

    Result<String> createPolicy(CreatePolicyCommand command);
}

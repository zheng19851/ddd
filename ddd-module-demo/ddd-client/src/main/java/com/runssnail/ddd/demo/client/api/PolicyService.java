package com.runssnail.ddd.demo.client.api;

import com.runssnail.ddd.common.result.SingleResult;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;

/**
 * @author zhengwei
 * @date 2019-11-06 10:54
 **/
public interface PolicyService {

    SingleResult<String> createPolicy(CreatePolicyCommand command);
}

package com.runssnail.ddd.demo.client.api;

import com.runssnail.ddd.common.result.SingleResult;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.ActivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetVersionCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.DeactivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.RemovePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.ActivatePolicySetResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.CreatePolicySetVersionResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.DeactivatePolicySetResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.RemovePolicySetResult;

/**
 * @author zhengwei
 * @date 2019-11-06 10:54
 **/
public interface PolicySetService {

    SingleResult<String> createPolicySet(CreatePolicySetCommand command);

    SingleResult<String> createPolicy(CreatePolicyCommand command);

    RemovePolicySetResult removePolicySet(RemovePolicySetCommand command);

    ActivatePolicySetResult activatePolicySet(ActivatePolicySetCommand command);

    DeactivatePolicySetResult deactivatePolicySet(DeactivatePolicySetCommand command);

    CreatePolicySetVersionResult createPolicySetVersion(CreatePolicySetVersionCommand command);
}

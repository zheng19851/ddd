package com.runssnail.ddd.demo.client.dto.result.policy;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.domain.policy.PolicyId;

import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class CreatePolicyResult extends Result {

    private PolicyId policyId;

}

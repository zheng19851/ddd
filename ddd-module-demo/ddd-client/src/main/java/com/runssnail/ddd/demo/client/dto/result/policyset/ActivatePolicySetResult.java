package com.runssnail.ddd.demo.client.dto.result.policyset;

import com.runssnail.ddd.common.result.Result;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class ActivatePolicySetResult extends Result {

    private String policySetId;

}

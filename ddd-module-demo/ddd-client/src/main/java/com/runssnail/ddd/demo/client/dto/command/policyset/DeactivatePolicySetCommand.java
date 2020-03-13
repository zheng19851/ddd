package com.runssnail.ddd.demo.client.dto.command.policyset;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.DeactivatePolicySetResult;

import lombok.Data;

/**
 * 禁用策略集
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class DeactivatePolicySetCommand extends AbstractCommand<DeactivatePolicySetResult> {

    private String policySetId;

    private String operator;

    @Override
    public Class<DeactivatePolicySetResult> resultType() {
        return DeactivatePolicySetResult.class;
    }
}

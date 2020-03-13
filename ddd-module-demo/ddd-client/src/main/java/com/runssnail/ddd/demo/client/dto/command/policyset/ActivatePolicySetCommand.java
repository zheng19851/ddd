package com.runssnail.ddd.demo.client.dto.command.policyset;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.ActivatePolicySetResult;

import lombok.Data;

/**
 * 启用策略集
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class ActivatePolicySetCommand extends AbstractCommand<ActivatePolicySetResult> {

    private String policySetId;

    private String operator;

    @Override
    public Class<ActivatePolicySetResult> resultType() {
        return ActivatePolicySetResult.class;
    }
}

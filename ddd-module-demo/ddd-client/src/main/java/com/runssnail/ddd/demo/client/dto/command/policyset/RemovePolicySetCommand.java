package com.runssnail.ddd.demo.client.dto.command.policyset;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.RemovePolicySetResult;

import lombok.Data;

/**
 * 删除策略集
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class RemovePolicySetCommand extends AbstractCommand<RemovePolicySetResult> {

    private String policySetId;

    private String operator;

    @Override
    public Class<RemovePolicySetResult> resultType() {
        return RemovePolicySetResult.class;
    }
}

package com.runssnail.ddd.demo.client.dto.command.policyset;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.CreatePolicySetVersionResult;

import lombok.Data;

/**
 * 创建策略集版本
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class CreatePolicySetVersionCommand extends AbstractCommand<CreatePolicySetVersionResult> {

    private String policySetId;

    private String version;

    private String operator;

    @Override
    public Class<CreatePolicySetVersionResult> resultType() {
        return CreatePolicySetVersionResult.class;
    }
}

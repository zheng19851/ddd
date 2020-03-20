package com.runssnail.ddd.demo.client.dto.command.policy;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.result.Result;

import lombok.Data;

/**
 * 创建策略
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class CreatePolicyCommand extends AbstractCommand<Result> {

    /**
     * 策略集ID
     */
    private String policySetId;

    private String partnerCode;

    private String name;

    private String description;

    public CreatePolicyCommand(String policySetId, String partnerCode, String name) {
        this.policySetId = policySetId;
        this.partnerCode = partnerCode;
        this.name = name;
    }

    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}

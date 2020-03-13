package com.runssnail.ddd.demo.client.dto.command.policyset;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.result.SingleResult;

import lombok.Data;

/**
 * 创建策略集
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class CreatePolicySetCommand extends AbstractCommand<SingleResult> {

    private String partnerCode;

    private String name;

    private String eventType;

    private String eventId;

    private String version;

    private String description;

    @Override
    public Class<SingleResult> resultType() {
        return SingleResult.class;
    }
}

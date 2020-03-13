package com.runssnail.ddd.demo.client.dto.command.policyset;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.demo.client.dto.result.policyset.UpdatePolicySetResult;

import lombok.Data;

/**
 * 修改策略集
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class UpdatePolicySetCommand extends AbstractCommand<UpdatePolicySetResult> implements Command<UpdatePolicySetResult> {

    /**
     * 策略集id
     */
    private String id;

    private String name;

    private String version;

    private String description;

    @Override
    public Class<UpdatePolicySetResult> resultType() {
        return UpdatePolicySetResult.class;
    }
}

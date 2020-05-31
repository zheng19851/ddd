package com.runssnail.ddd.demo.client.dto.command.product;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.result.Result;

import lombok.Data;

/**
 * 禁用
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class DeactivateProductCommand extends AbstractCommand<Result> {

    private String productId;

    private String operator;

    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}

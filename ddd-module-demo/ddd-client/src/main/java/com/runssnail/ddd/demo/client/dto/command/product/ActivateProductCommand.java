package com.runssnail.ddd.demo.client.dto.command.product;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.result.Result;

import lombok.Data;

/**
 * 启用
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class ActivateProductCommand extends AbstractCommand<Result> {

    private String productId;

    private String operator;

    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}

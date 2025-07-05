package com.runssnail.ddd.demo.client.command.product;

import com.runssnail.ddd.common.command.BaseCommand;
import com.runssnail.ddd.common.result.Result;

import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class UpdateProductCommand extends BaseCommand<Result> {

    private String productId;

    private String name;

    private String description;

    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}

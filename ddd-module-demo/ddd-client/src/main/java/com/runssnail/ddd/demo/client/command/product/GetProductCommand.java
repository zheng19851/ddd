package com.runssnail.ddd.demo.client.command.product;

import com.runssnail.ddd.common.command.BaseCommand;
import com.runssnail.ddd.common.result.Result;

import lombok.Data;

/**
 * 获取商品信息
 *
 * @author zhengwei
 */
@Data
public class GetProductCommand extends BaseCommand<Result> {

    private String productId;


    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}

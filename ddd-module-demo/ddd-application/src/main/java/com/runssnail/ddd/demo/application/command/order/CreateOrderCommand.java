package com.runssnail.ddd.demo.application.command.order;

import com.runssnail.ddd.common.command.BaseCommand;
import com.runssnail.ddd.common.result.Result;
import lombok.Data;

@Data
public class CreateOrderCommand extends BaseCommand<Result> {

    // 立即购买，只有1个sku，做个demo

    private Long uid;
    private String skuId;
    private Integer skuQuantity;


    @Override
    public Class<Result> resultType() {
        return null;
    }
}

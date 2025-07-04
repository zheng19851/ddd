package com.runssnail.ddd.demo.application.command.order;

import com.runssnail.ddd.common.command.BaseCommand;
import com.runssnail.ddd.common.result.Result;
import lombok.Data;

@Data
public class OrderPayCommand extends BaseCommand<Result> {

    private String orderId;


    @Override
    public Class<Result> resultType() {
        return null;
    }
}

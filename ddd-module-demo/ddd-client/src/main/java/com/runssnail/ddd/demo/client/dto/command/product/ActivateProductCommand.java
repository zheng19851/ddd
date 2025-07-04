package com.runssnail.ddd.demo.client.dto.command.product;

import javax.validation.constraints.NotBlank;

import com.runssnail.ddd.common.command.BaseCommand;
import com.runssnail.ddd.common.result.Result;

import lombok.Data;

/**
 * 启用
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class ActivateProductCommand extends BaseCommand<Result> {

    @NotBlank
    private String productId;

    private String operator;

    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}

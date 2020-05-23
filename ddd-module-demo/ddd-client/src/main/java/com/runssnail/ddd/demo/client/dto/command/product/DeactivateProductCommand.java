package com.runssnail.ddd.demo.client.dto.command.product;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.demo.client.dto.result.product.DeactivateProductResult;

import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class DeactivateProductCommand extends AbstractCommand<DeactivateProductResult> {

    private String productId;

    private String operator;

    @Override
    public Class<DeactivateProductResult> resultType() {
        return DeactivateProductResult.class;
    }
}

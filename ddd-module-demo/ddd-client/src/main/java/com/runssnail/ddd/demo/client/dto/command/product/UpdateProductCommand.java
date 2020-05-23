package com.runssnail.ddd.demo.client.dto.command.product;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.demo.client.dto.result.product.UpdateProductResult;

import lombok.Data;

/**
 *
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class UpdateProductCommand extends AbstractCommand<UpdateProductResult> implements Command<UpdateProductResult> {

    private String productId;

    private String name;

    private String version;

    private String description;

    @Override
    public Class<UpdateProductResult> resultType() {
        return UpdateProductResult.class;
    }
}

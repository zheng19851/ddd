package com.runssnail.ddd.demo.client.api;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.dto.result.product.ActivateProductResult;
import com.runssnail.ddd.demo.client.dto.result.product.DeactivateProductResult;
import com.runssnail.ddd.demo.client.dto.result.product.RemoveProductResult;

/**
 * @author zhengwei
 * @date 2019-11-06 10:54
 **/
public interface ProductService {

    Result<String> createProduct(CreateProductCommand command);

    RemoveProductResult removeProduct(RemoveProductCommand command);

    ActivateProductResult activateProduct(ActivateProductCommand command);

    DeactivateProductResult deactivateProduct(DeactivateProductCommand command);

}

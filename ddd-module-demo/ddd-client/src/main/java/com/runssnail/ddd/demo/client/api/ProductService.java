package com.runssnail.ddd.demo.client.api;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.client.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.client.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.command.product.GetProductCommand;
import com.runssnail.ddd.demo.client.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.command.product.UpdateProductCommand;

/**
 * @author zhengwei
 * @date 2019-11-06 10:54
 **/
public interface ProductService {

    Result<String> createProduct(CreateProductCommand command);

    Result<String> updateProduct(UpdateProductCommand command);

    Result<ProductDTO> getProduct(GetProductCommand command);

    Result<String> removeProduct(RemoveProductCommand command);

    Result<String> activateProduct(ActivateProductCommand command);

    Result<String> deactivateProduct(DeactivateProductCommand command);

}

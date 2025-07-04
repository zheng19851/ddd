package com.runssnail.ddd.demo.adapter.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.service.ProductAppService;
import com.runssnail.ddd.demo.client.api.ProductService;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.GetProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.UpdateProductCommand;

/**
 * @author zhengwei
 * @date 2019-11-07 10:03
 **/
@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductAppService productAppService;

    @Override
    public Result<String> createProduct(CreateProductCommand command) {
        return productAppService.createProduct(command);
    }

    @Override
    public Result<String> updateProduct(UpdateProductCommand command) {
        return productAppService.updateProduct(command);
    }

    @Override
    public Result<ProductDTO> getProduct(GetProductCommand command) {
        return productAppService.getProduct(command);
    }

    @Override
    public Result<String> removeProduct(RemoveProductCommand command) {
        return productAppService.removeProduct(command);
    }

    @Override
    public Result<String> activateProduct(ActivateProductCommand command) {
        return productAppService.activateProduct(command);
    }

    @Override
    public Result<String> deactivateProduct(DeactivateProductCommand command) {
        return productAppService.deactivateProduct(command);
    }

}

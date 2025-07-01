package com.runssnail.ddd.demo.intf.adapter.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.service.ProductApplicationService;
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
    private ProductApplicationService productApplicationService;

    @Override
    public Result<String> createProduct(CreateProductCommand command) {
        return productApplicationService.createProduct(command);
    }

    @Override
    public Result<String> updateProduct(UpdateProductCommand command) {
        return productApplicationService.updateProduct(command);
    }

    @Override
    public Result<ProductDTO> getProduct(GetProductCommand command) {
        return productApplicationService.getProduct(command);
    }

    @Override
    public Result<String> removeProduct(RemoveProductCommand command) {
        return productApplicationService.removeProduct(command);
    }

    @Override
    public Result<String> activateProduct(ActivateProductCommand command) {
        return productApplicationService.activateProduct(command);
    }

    @Override
    public Result<String> deactivateProduct(DeactivateProductCommand command) {
        return productApplicationService.deactivateProduct(command);
    }

}

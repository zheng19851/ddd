package com.runssnail.ddd.demo.intf.adapter.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.command.CommandBus;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.api.ProductService;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.dto.result.product.ActivateProductResult;
import com.runssnail.ddd.demo.client.dto.result.product.DeactivateProductResult;
import com.runssnail.ddd.demo.client.dto.result.product.RemoveProductResult;

/**
 * @author zhengwei
 * @date 2019-11-07 10:03
 **/
@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CommandBus commandBus;

    @Override
    public Result<String> createProduct(CreateProductCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public RemoveProductResult removeProduct(RemoveProductCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public ActivateProductResult activateProduct(ActivateProductCommand command) {
        return commandBus.dispatch(command);
    }

    @Override
    public DeactivateProductResult deactivateProduct(DeactivateProductCommand command) {
        return commandBus.dispatch(command);
    }

}

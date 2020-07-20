package com.runssnail.ddd.demo.bootstrap;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.runssnail.ddd.common.exception.BasicErrorCode;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.commandhandling.handler.product.CreateProductCommandHandler;
import com.runssnail.ddd.demo.application.commandhandling.interceptor.product.CreateProductInterceptor;
import com.runssnail.ddd.demo.application.commandhandling.interceptor.product.Order2CreateProductInterceptor;
import com.runssnail.ddd.demo.application.commandhandling.validator.CreateProductCommandValidator;
import com.runssnail.ddd.demo.client.api.ProductService;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;


/**
 * @author zhengwei
 * @date 2019-11-05 17:29
 * @see CreateProductCommandValidator
 * @see CreateProductInterceptor
 * @see Order2CreateProductInterceptor
 * @see CreateProductCommandHandler
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.runssnail.ddd.demo.bootstrap")
public class ProductApplicationServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testDeactivateProductOk() {
        DeactivateProductCommand command = new DeactivateProductCommand();
        command.setOperator("test");
        command.setProductId("3333333333");
        Result<String> result = productService.deactivateProduct(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void testActivateProductOk() {
        ActivateProductCommand command = new ActivateProductCommand();
        command.setOperator("test");
        command.setProductId("3333333333");
        Result<String> result = productService.activateProduct(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void testRemoveProductOk() {
        RemoveProductCommand command = new RemoveProductCommand();
        command.setOperator("test");
        command.setProductId("3333333333");
        Result<String> result = productService.removeProduct(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void testCreateProductOk() {
        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setDescription("demo");
        createProductCommand.setName("test");
        Result<String> result = productService.createProduct(createProductCommand);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void testCreateProductNameOverLength() {
        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setDescription("demo");
        createProductCommand.setName("testfffffffffff");
        Result<String> result = productService.createProduct(createProductCommand);

        Assert.assertNotNull(result);
        Assert.assertTrue(BasicErrorCode.PARAMS_ERROR.getErrorCode() == result.getCode());
    }
}

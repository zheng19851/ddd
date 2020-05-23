package com.runssnail.ddd.demo.bootstrap;


import com.runssnail.ddd.common.exception.BasicErrorCode;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.command.handler.product.CreateProductCommandHandler;
import com.runssnail.ddd.demo.application.command.interceptor.product.CreateProductInterceptor;
import com.runssnail.ddd.demo.application.command.interceptor.product.Order2CreateProductInterceptor;
import com.runssnail.ddd.demo.application.command.validator.CreateProductCommandValidator;
import com.runssnail.ddd.demo.client.api.ProductService;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.dto.result.product.ActivateProductResult;
import com.runssnail.ddd.demo.client.dto.result.product.DeactivateProductResult;
import com.runssnail.ddd.demo.client.dto.result.product.RemoveProductResult;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


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
        DeactivateProductResult result = productService.deactivateProduct(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getProductId());
    }

    @Test
    public void testActivatePolicySetOk() {
        ActivateProductCommand command = new ActivateProductCommand();
        command.setOperator("test");
        command.setProductId("3333333333");
        ActivateProductResult result = productService.activateProduct(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getProductId());
    }

    @Test
    public void testRemovePolicySetOk() {
        RemoveProductCommand command = new RemoveProductCommand();
        command.setOperator("test");
        command.setProductId("3333333333");
        RemoveProductResult result = productService.removeProduct(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getProductId());
    }

    @Test
    public void testCreatePolicySetOk() {
        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setDescription("demo");
        createProductCommand.setName("test");
        Result<String> result = productService.createProduct(createProductCommand);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void testCreatePolicySetNameOverLength() {
        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setDescription("demo");
        createProductCommand.setName("testfffffffffff");
        Result<String> result = productService.createProduct(createProductCommand);

        Assert.assertNotNull(result);
        Assert.assertTrue(BasicErrorCode.PARAMS_ERROR.getErrorCode() == result.getCode());
    }
}

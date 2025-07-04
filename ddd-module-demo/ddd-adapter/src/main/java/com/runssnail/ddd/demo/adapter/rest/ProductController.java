package com.runssnail.ddd.demo.adapter.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runssnail.ddd.common.result.PagingResult;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.service.ProductAppService;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.GetProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.QueryProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.UpdateProductCommand;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductAppService productAppService;

    /**
     * 分页查询商品信息
     * 复杂查询可以不经过领域层，直接从db查询数据
     *
     * @param command 参数
     * @return 商品信息
     */
    @GetMapping(value = "query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PagingResult<ProductDTO> queryProducts(@Validated QueryProductCommand command) {
        PagingResult<ProductDTO> result = productAppService.queryProducts(command);
        return result;
    }


    /**
     * 创建商品
     *
     * @param command 参数
     * @return 商品ID
     */
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> createProduct(@Validated @RequestBody CreateProductCommand command) {
        Result<String> result = productAppService.createProduct(command);
        return result;
    }

    /**
     * 修改商品
     *
     * @param command 参数
     * @return 商品ID
     */
    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> updateProduct(@Validated @RequestBody UpdateProductCommand command) {
        Result<String> result = productAppService.updateProduct(command);
        return result;
    }

    /**
     * 商品信息
     *
     * @param command 参数
     * @return 商品信息
     */
    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<ProductDTO> getProduct(@Validated GetProductCommand command) {
        Result<ProductDTO> result = productAppService.getProduct(command);
        return result;
    }

    /**
     * 禁用商品
     *
     * @param command 参数
     * @return 商品ID
     */
    @PostMapping(value = "deactivate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> deactivateProduct(@Validated DeactivateProductCommand command) {
        Result<String> result = productAppService.deactivateProduct(command);
        return result;
    }

    /**
     * 启用商品
     *
     * @param command 参数
     * @return 商品ID
     */
    @PostMapping(value = "activate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> activateProduct(@Validated ActivateProductCommand command) {
        Result<String> result = productAppService.activateProduct(command);
        return result;
    }


    /**
     * 删除商品
     *
     * @param command 参数
     * @return 商品Id
     */
    @PostMapping(value = "delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> deleteProduct(@Validated RemoveProductCommand command) {
        Result<String> result = productAppService.removeProduct(command);
        return result;
    }
}

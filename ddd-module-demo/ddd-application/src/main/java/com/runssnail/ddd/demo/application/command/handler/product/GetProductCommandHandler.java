package com.runssnail.ddd.demo.application.command.handler.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.assembler.product.ProductAssembler;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.client.dto.command.product.GetProductCommand;
import com.runssnail.ddd.demo.domain.exception.ProductErrorCode;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.ProductRepository;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class GetProductCommandHandler extends BaseCommandHandler<GetProductCommand, Result> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAssembler productAssembler;

    @Override
    public Class<GetProductCommand> supportCommand() {
        return GetProductCommand.class;
    }

    @Override
    public Result<ProductDTO> doHandle(GetProductCommand command) {

        // 转换领域对象
        Product product = this.productRepository.selectById(command.getProductId());
        if (product == null || product.isDeleted()) {
            // 不存在
            return Result.failure(ProductErrorCode.PRODUCT_NOT_EXISTS);
        }

        // 保存数据
        ProductDTO productDTO = productAssembler.assemble(product);

        return Result.success(productDTO);
    }
}

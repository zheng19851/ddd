package com.runssnail.ddd.demo.application.commandhandling.handler.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.PagingResult;
import com.runssnail.ddd.demo.application.assembler.product.ProductAssembler;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.client.command.product.QueryProductCommand;
import com.runssnail.ddd.demo.domain.entity.product.Product;
import com.runssnail.ddd.demo.domain.repository.product.ProductQuery;
import com.runssnail.ddd.demo.domain.repository.product.ProductRepository;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class QueryProductCommandHandler extends BaseCommandHandler<QueryProductCommand, PagingResult> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAssembler productAssembler;

    @Override
    public Class<QueryProductCommand> supportCommand() {
        return QueryProductCommand.class;
    }

    @Override
    public PagingResult<ProductDTO> doHandle(QueryProductCommand command) {

        ProductQuery query = new ProductQuery();
        BeanUtils.copyProperties(command, query);

        PagingResult<Product> result = productRepository.queryProducts(query);
        if (!result.isSuccess()) {
            return PagingResult.failure(result.getCode(), result.getMessage());
        }
        if (result.isEmpty()) {
            return PagingResult.create();
        }

        // 保存数据
        List<ProductDTO> productDTO = result.getData().stream().map(product -> this.productAssembler.assemble(product)).collect(Collectors.toList());

        return PagingResult.create(productDTO, result.getTotal(), result.getPages());
    }

}

package com.runssnail.ddd.demo.application.assembler.product;

import com.runssnail.ddd.assembler.Assembler;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.service.ProductDomainService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 18:10
 **/
@Component
public class CreateProductAssembler implements Assembler<Product, ProductDTO> {

    @Override
    public ProductDTO assemble(Product product) {

        ProductDTO target = new ProductDTO();
        BeanUtils.copyProperties(product, target);
        return target;
    }
}

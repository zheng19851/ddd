package com.runssnail.ddd.demo.application.assembler.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.assembler.Assembler;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.domain.entity.product.Product;

/**
 * @author zhengwei
 * @date 2019-11-05 18:10
 **/
@Component
public class ProductAssembler implements Assembler<Product, ProductDTO> {

    @Override
    public ProductDTO assemble(Product product) {

        ProductDTO target = new ProductDTO();
        BeanUtils.copyProperties(product, target);
        return target;
    }
}

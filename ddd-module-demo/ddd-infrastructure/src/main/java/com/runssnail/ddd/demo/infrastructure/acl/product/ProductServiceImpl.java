package com.runssnail.ddd.demo.infrastructure.acl.product;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.ProductDTO;
import com.runssnail.ddd.demo.domain.acl.product.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Result<ProductDTO> getProduct(String productId) {
        return null;
    }

    @Override
    public void lockStock(String orderId, String skuId, Integer skuQuantity) {

    }
}

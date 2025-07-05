package com.runssnail.ddd.demo.domain.acl.product;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.ProductDTO;

public interface ProductService {

    Result<ProductDTO> getProduct(String productId);

    void lockStock(String orderId, String skuId, Integer skuQuantity);

}

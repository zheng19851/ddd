package com.runssnail.ddd.demo.domain.acl.stock;

public interface StockService {

    void lockStock(String orderId, String skuId, Integer skuQuantity);

}

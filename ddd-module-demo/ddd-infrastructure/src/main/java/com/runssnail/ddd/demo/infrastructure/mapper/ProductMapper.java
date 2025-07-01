package com.runssnail.ddd.demo.infrastructure.mapper;

import java.util.concurrent.Callable;

public interface ProductMapper {

    int removeProduct(String productId, String operator, int concurrencyVersion);
}

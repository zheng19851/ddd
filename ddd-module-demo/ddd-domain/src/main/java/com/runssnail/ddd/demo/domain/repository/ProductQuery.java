package com.runssnail.ddd.demo.domain.repository;

import lombok.Data;

/**
 * @author zhengwei <zhengwei03@kuaishou.com>
 * Created on 2020-05-31
 */
@Data
public class ProductQuery {

    private String productId;

    /**
     * 商品名称
     */
    private String name;


}

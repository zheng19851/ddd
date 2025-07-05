package com.runssnail.ddd.demo.client.command.product;

import com.runssnail.ddd.common.command.BaseCommand;
import com.runssnail.ddd.common.result.PagingResult;

import lombok.Data;

/**
 * 获取商品信息
 *
 * @author zhengwei
 */
@Data
public class QueryProductCommand extends BaseCommand<PagingResult> {

    private String productId;

    /**
     * 商品名称
     */
    private String name;


    @Override
    public Class<PagingResult> resultType() {
        return PagingResult.class;
    }
}

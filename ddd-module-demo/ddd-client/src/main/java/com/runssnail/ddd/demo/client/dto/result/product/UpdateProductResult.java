package com.runssnail.ddd.demo.client.dto.result.product;

import com.runssnail.ddd.common.result.Result;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:36
 **/
@Data
public class UpdateProductResult extends Result {

    private String productId;

}

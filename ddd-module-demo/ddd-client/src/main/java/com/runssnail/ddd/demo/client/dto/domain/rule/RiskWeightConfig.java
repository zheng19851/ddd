package com.runssnail.ddd.demo.client.dto.domain.rule;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 *
 * 权重配置
 *
 * @author zhengwei
 * @date 2019-11-05 15:51
 **/
@Data
public class RiskWeightConfig extends ValueObject {
    private Double riskWeight;

    private Double weightRatio;

    private String indexId;
    private String indexType;

    /**
     * 权重分上下线
     */
    private LimitScore limitScore;


}

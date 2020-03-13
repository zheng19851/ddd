package com.runssnail.ddd.demo.client.dto.domain.rule;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 15:50
 **/
@Data
public class LimitScore extends ValueObject {
    private Integer lowerLimitScore;
    private Integer upperLimitScore;


}

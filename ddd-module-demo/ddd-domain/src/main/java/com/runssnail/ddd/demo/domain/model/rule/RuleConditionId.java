package com.runssnail.ddd.demo.domain.model.rule;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 15:52
 **/
@Data
public class RuleConditionId extends ValueObject {
    private String id;

}

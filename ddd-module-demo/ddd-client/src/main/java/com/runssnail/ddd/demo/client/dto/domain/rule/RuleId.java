package com.runssnail.ddd.demo.client.dto.domain.rule;


import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:30
 **/
@Data
public class RuleId extends ValueObject {
    private String id;

    public RuleId(String id) {
        Validate.notBlank(id);
        this.id = id;
    }

}

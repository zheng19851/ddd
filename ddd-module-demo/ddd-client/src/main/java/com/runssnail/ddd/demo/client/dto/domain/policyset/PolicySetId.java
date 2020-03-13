package com.runssnail.ddd.demo.client.dto.domain.policyset;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:30
 **/
@Data
public class PolicySetId extends ValueObject {
    private String id;

    protected PolicySetId(String id) {
        Validate.notBlank(id);
        this.id = id;
    }

    public static PolicySetId create(String id) {
        return new PolicySetId(id);
    }
}

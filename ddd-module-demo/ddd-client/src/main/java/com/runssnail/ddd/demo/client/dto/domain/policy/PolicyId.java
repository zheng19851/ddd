package com.runssnail.ddd.demo.client.dto.domain.policy;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:30
 **/
@Data
public class PolicyId extends ValueObject {
    private String id;

    protected PolicyId(String id) {
        Validate.notBlank(id);
        this.id = id;
    }

    public static PolicyId create(String id) {
        return new PolicyId(id);
    }
}

package com.runssnail.ddd.demo.domain.model.policyset;

import com.runssnail.ddd.common.domain.ValueObject;

import org.apache.commons.lang3.Validate;

import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:34
 **/
@Data
public class PolicySetVersion extends ValueObject {
    private String value;

    protected PolicySetVersion(String value) {
        Validate.notBlank(value);
        this.value = value;
    }

    public static PolicySetVersion create(String version) {
        return new PolicySetVersion(version);
    }
}

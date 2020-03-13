package com.runssnail.ddd.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author zhengwei
 * @date 2019/3/7 9:29 PM
 **/
public abstract class BaseDTO implements Serializable {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

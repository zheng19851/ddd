package com.runssnail.ddd.demo.client.dto.domain;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 16:16
 **/
@Data
public class Property extends ValueObject {

    private String name;
    private String type;
}

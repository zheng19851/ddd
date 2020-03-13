package com.runssnail.ddd.demo.client.dto.domain.rule;

import com.runssnail.ddd.common.dto.BaseDTO;
import com.runssnail.ddd.demo.client.dto.domain.Property;

import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 15:52
 **/
@Data
public class RuleAction extends BaseDTO {

    private Property leftValue;
    private Property rightValue;
}

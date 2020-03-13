package com.runssnail.ddd.demo.client.dto.domain.rule;

import java.util.List;

import com.runssnail.ddd.common.dto.BaseDTO;
import lombok.Data;

/**
 * 规则条件
 *
 * @author zhengwei
 * @date 2019-11-05 15:51
 **/
@Data
public class RuleConditionDTO extends BaseDTO {

    private String LogicOperator;

    private String leftProperty;
    private String leftPropertyType;

    private String rightProperty;
    private String rightPropertyType;

    private List<RuleConditionDTO> subConditions;
}

package com.runssnail.ddd.demo.domain.event.rule;

import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;
import com.runssnail.ddd.common.event.AbstractEvent;

import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 15:08
 **/
@Getter
public class RuleCreatedEvent extends AbstractEvent {

    private RuleId ruleId;

    public RuleCreatedEvent(RuleId ruleId) {
        this.ruleId = ruleId;
    }


}

package com.runssnail.ddd.demo.domain.event.policy;

import com.runssnail.ddd.common.event.AbstractEvent;
import com.runssnail.ddd.demo.client.dto.domain.policy.PolicyId;

import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 15:08
 **/
@Getter
public class PolicyCreatedEvent extends AbstractEvent {

    private PolicyId policyId;

    public PolicyCreatedEvent(PolicyId policyId) {
        this.policyId = policyId;
    }


}

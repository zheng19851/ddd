package com.runssnail.ddd.demo.domain.event.policyset;

import com.runssnail.ddd.common.event.AbstractEvent;
import com.runssnail.ddd.demo.client.dto.domain.policyset.PolicySetId;

import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 15:08
 **/
@Getter
public class PolicySetActivatedEvent extends AbstractEvent {

    private PolicySetId policySetId;

    public PolicySetActivatedEvent(PolicySetId policySetId) {
        this.policySetId = policySetId;
    }


}

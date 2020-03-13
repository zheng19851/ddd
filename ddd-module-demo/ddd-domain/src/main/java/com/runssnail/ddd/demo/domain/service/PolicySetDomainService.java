package com.runssnail.ddd.demo.domain.service;

import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySetVersion;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 策略集领域服务
 *
 * @author zhengwei
 * @date 2019-11-06 11:59
 **/
@Component
public class PolicySetDomainService {

    public String nextId() {
        return UUID.randomUUID().toString();
    }

    public PolicySet createPolicySet(String partnerCode, String name, String eventType, String eventId, PolicySetVersion version) {
        PolicySet policySet = new PolicySet(this.nextId(), partnerCode, name, eventType, eventId, version);

        return policySet;
    }

    public PolicySet createPolicySet(CreatePolicySetCommand command) {
        PolicySet policySet = this.createPolicySet(command.getPartnerCode(), command.getName(), command.getEventType(), command.getEventId(), PolicySetVersion.create(command.getVersion()));

        policySet.validate();

        return policySet;
    }
}

package com.runssnail.ddd.demo.application.eventhandler;

import com.runssnail.ddd.demo.domain.event.policyset.PolicySetCreatedEvent;
import com.runssnail.ddd.event.BaseEventHandler;

import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 15:28
 **/
@Component
public class PolicySetCreatedEventHandler extends BaseEventHandler<PolicySetCreatedEvent> {

    @Override
    protected void doHandle(PolicySetCreatedEvent event) {
        log.info("receive a PolicySetCreatedEvent, id={}", event.getPolicySetId());
    }

    @Override
    public Class<PolicySetCreatedEvent> supportEventType() {
        return PolicySetCreatedEvent.class;
    }
}

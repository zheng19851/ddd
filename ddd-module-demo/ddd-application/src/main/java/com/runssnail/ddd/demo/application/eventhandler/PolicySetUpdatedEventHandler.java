package com.runssnail.ddd.demo.application.eventhandler;

import org.springframework.stereotype.Component;

import com.runssnail.ddd.demo.domain.event.policyset.PolicySetUpdatedEvent;
import com.runssnail.ddd.event.BaseEventHandler;

/**
 * @author zhengwei
 * @date 2019-11-05 15:28
 **/
@Component
public class PolicySetUpdatedEventHandler extends BaseEventHandler<PolicySetUpdatedEvent> {

    @Override
    protected void doHandle(PolicySetUpdatedEvent event) {
        log.info("receive a PolicySetUpdatedEvent, id={}", event.getPolicySetId());
    }

    @Override
    public Class<PolicySetUpdatedEvent> supportEventType() {
        return PolicySetUpdatedEvent.class;
    }
}

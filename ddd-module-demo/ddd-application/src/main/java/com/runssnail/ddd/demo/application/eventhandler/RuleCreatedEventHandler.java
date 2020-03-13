package com.runssnail.ddd.demo.application.eventhandler;

import com.runssnail.ddd.demo.domain.event.rule.RuleCreatedEvent;
import com.runssnail.ddd.event.BaseEventHandler;

/**
 * @author zhengwei
 * @date 2019-11-05 15:28
 **/
//@Component
public class RuleCreatedEventHandler extends BaseEventHandler<RuleCreatedEvent> {

    @Override
    protected void doHandle(RuleCreatedEvent event) {
        log.info("receive a RuleCreatedEvent, id={}", event.getRuleId().getId());
    }

    @Override
    public Class<RuleCreatedEvent> supportEventType() {
        return RuleCreatedEvent.class;
    }
}

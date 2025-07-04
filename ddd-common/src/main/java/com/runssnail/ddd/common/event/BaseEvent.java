package com.runssnail.ddd.common.event;

import java.util.Date;

import com.runssnail.ddd.common.dto.BaseDTO;

/**
 * BaseEvent
 *
 * @author zhengwei
 * @date 2019/3/21 12:41 PM
 **/
public abstract class BaseEvent extends BaseDTO implements Event {
    private static final long serialVersionUID = -750375438171631444L;

    /**
     * 发生时间
     */
    private Date occurredTime = new Date();

    @Override
    public final Date occurredTime() {
        return this.occurredTime;
    }

    @Override
    public String getTag() {
        return null;
    }

}

package com.runssnail.ddd.common.event;

import java.util.Date;

/**
 * 事件接口
 *
 * @author zhengwei
 * @date 2019/1/26 9:46 PM
 **/
public interface Event {

    /**
     * 发生时间
     *
     * @return 发生时间
     */
    Date occurredTime();

    /**
     * 标签
     *
     * @return tag
     */
    String getTag();
}

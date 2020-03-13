package com.runssnail.ddd.event;

import com.runssnail.ddd.common.event.Event;

/**
 * event bus
 *
 * @author zhengwei
 * @date 2019/1/26 12:38 PM
 **/
public interface EventBus {

    /**
     * 同步发布事件
     *
     * @param event 事件
     */
    void publish(Event event);

    /**
     * 异步发布事件
     *
     * @param event 事件
     */
    void asyncPublish(Event event);

    /**
     * 注册EventListener
     * @param handler EventHandler
     * @param <T> Event
     */
    <T extends Event> void registerHandler(EventHandler<T> handler);
}

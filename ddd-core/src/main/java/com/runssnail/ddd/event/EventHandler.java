package com.runssnail.ddd.event;

import java.util.concurrent.Executor;

import com.runssnail.ddd.common.event.Event;

/**
 *
 * handle event
 *
 * @see EventBus
 * @see Event
 *
 * @author zhengwei
 * @date 2019/1/25 11:37 AM
 **/
public interface EventHandler<T extends Event> {

    /**
     * 异步执行器
     *
     * @return 异步执行器
     */
    Executor getExecutor();

    /**
     * 当前listener支持的事件类型
     *
     * 1、首先根据event type获取listener list
     * 2、根据support方法判断是否支持事件
     *
     * @return
     */
    Class<T> supportEventType();

    /**
     * 是否支持处理该事件
     *
     * @param event 事件
     * @return true/false
     */
    boolean support(Event event);

    /**
     * 处理事件
     *
     * @param event 事件
     */
    void handle(T event);

}

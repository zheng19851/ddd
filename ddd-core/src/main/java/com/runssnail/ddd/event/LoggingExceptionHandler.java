package com.runssnail.ddd.event;

import com.runssnail.ddd.common.event.Event;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认只是打印日志
 *
 * @author zhengwei
 * @date 2019/3/26 10:36 AM
 **/
@Slf4j
public class LoggingExceptionHandler implements EventExceptionHandler {

    @Override
    public void onException(EventHandler listener, Event event, Throwable t) {
        log.error("execute listener error, listener={}, event={}", listener, event, t);
    }
}

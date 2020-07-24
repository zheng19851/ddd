package com.runssnail.ddd.eventhandling;

import com.runssnail.ddd.common.event.Event;

/**
 * 异常处理器
 * @author zhengwei
 */
public interface EventExceptionHandler {

	/**
	 * 处理异常
	 *
	 * @param listener listener
	 * @param event event
	 * @param t 异常
	 */
	void onException(EventHandler listener, Event event, Throwable t);

}

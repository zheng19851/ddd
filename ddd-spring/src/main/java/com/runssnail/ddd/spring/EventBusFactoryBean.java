package com.runssnail.ddd.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.runssnail.ddd.eventhandling.DefaultEventBus;
import com.runssnail.ddd.eventhandling.EventBus;
import com.runssnail.ddd.eventhandling.EventExceptionHandler;
import com.runssnail.ddd.eventhandling.EventHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * EventBusFactoryBean
 *
 * @author zhengwei
 * @date 2019-11-07 17:14
 **/
@Component
@Slf4j
public class EventBusFactoryBean implements FactoryBean<EventBus>, ApplicationContextAware, InitializingBean, DisposableBean {

    public static final String DEFAULT_EVENT_EXECUTOR_BEAN_NAME = "eventExecutor";
    public static final String DEFAULT_EXCEPTION_HANDLER_BEAN_NAME = "exceptionHandler";

    private ApplicationContext applicationContext;

    private DefaultEventBus eventBus;

    private boolean detectAllEventHandlers = true;

    private EventExceptionHandler exceptionHandler;

    private ExecutorService eventExecutor;

    @Override
    public EventBus getObject() throws Exception {
        return this.eventBus;
    }

    @Override
    public Class<?> getObjectType() {
        return EventBus.class;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultEventBus eventBus = new DefaultEventBus();

        if (this.detectAllEventHandlers) {
            detectAllEventHandlers(eventBus);
        }

        initExecutor(eventBus);

        initExceptionHandler(eventBus);

        eventBus.init();

        this.eventBus = eventBus;
    }

    private void initExceptionHandler(DefaultEventBus eventBus) {
        if (this.exceptionHandler != null) {
            this.eventBus.setExceptionHandler(exceptionHandler);
            return;
        }

        if (this.applicationContext.containsBean(DEFAULT_EXCEPTION_HANDLER_BEAN_NAME)) {
            Object exceptionHandler = this.applicationContext.getBean(DEFAULT_EXCEPTION_HANDLER_BEAN_NAME);
            if (exceptionHandler instanceof EventExceptionHandler) {
                eventBus.setExceptionHandler((EventExceptionHandler) exceptionHandler);
                log.info("find EventExceptionHandler form ApplicationContext {}", DEFAULT_EXCEPTION_HANDLER_BEAN_NAME);
                return;
            }
        }

    }

    private void initExecutor(DefaultEventBus eventBus) {
        if (this.eventExecutor != null) {
            eventBus.setEventExecutor(eventExecutor);
            return;
        }

        if (this.applicationContext.containsBean(DEFAULT_EVENT_EXECUTOR_BEAN_NAME)) {
            Object eventExecutor = this.applicationContext.getBean(DEFAULT_EVENT_EXECUTOR_BEAN_NAME);
            if (eventExecutor instanceof ThreadPoolExecutor) {
                eventBus.setEventExecutor((ThreadPoolExecutor) eventExecutor);
                return;
            }
        }

        if (eventBus.getEventExecutor() == null) {
            ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
            threadFactoryBuilder.setNameFormat("EventBus-%d");
            int poolSize = Runtime.getRuntime().availableProcessors() * 2;
            ThreadPoolExecutor eventExecutor = new ThreadPoolExecutor(poolSize, poolSize, 1, TimeUnit.MINUTES, new SynchronousQueue<>(), threadFactoryBuilder.build(), (r, executor) -> {
                log.error("event task is rejected, r={}", r);
            });
            eventBus.setEventExecutor(eventExecutor);
        }
    }

    private void detectAllEventHandlers(DefaultEventBus eventBus) {
        Map<String, EventHandler> beansOfEventHandlers = applicationContext.getBeansOfType(EventHandler.class);

        if (MapUtils.isEmpty(beansOfEventHandlers)) {
            log.warn("cannot find any EventHandler from spring context");
            return;
        }

        List<EventHandler> eventHandlers = new ArrayList<>(beansOfEventHandlers.values());

        log.info("find {} EventHandler {}", eventHandlers.size(), eventHandlers);

        // 排序
        AnnotationAwareOrderComparator.sort(eventHandlers);

        for (EventHandler handler : eventHandlers) {
            eventBus.registerHandler(handler);
        }

    }

    @Override
    public void destroy() throws Exception {
        this.eventBus.destroy();
    }

    public boolean isDetectAllEventHandlers() {
        return detectAllEventHandlers;
    }

    public void setDetectAllEventHandlers(boolean detectAllEventHandlers) {
        this.detectAllEventHandlers = detectAllEventHandlers;
    }

    public EventExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(EventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public ExecutorService getEventExecutor() {
        return eventExecutor;
    }

    public void setEventExecutor(ExecutorService eventExecutor) {
        this.eventExecutor = eventExecutor;
    }
}

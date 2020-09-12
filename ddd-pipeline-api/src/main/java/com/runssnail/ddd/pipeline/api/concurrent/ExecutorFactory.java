package com.runssnail.ddd.pipeline.api.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程池工厂
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface ExecutorFactory {

    /**
     * 创建线程池
     *
     * @param corePoolSize     核心线程数
     * @param maxPoolSize      最大线程数
     * @param queue            队列类型
     * @param queueSize        队列数
     * @param threadNamePrefix 线程名称前缀
     * @return
     */
    ExecutorService create(int corePoolSize, int maxPoolSize, String queue, int queueSize, String threadNamePrefix);

    /**
     * 创建任务调度器
     *
     * @param corePoolSize     核心线程数
     * @param threadNamePrefix 线程名前缀
     * @return
     */
    ScheduledExecutorService createScheduled(int corePoolSize, String threadNamePrefix);
}

package com.runssnail.ddd.pipeline.api;

import java.util.concurrent.ExecutorService;

/**
 * 默认的线程池工厂
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public class DefaultExecutorFactory implements ExecutorFactory {

    @Override
    public ExecutorService create(int corePoolSize, int maxPoolSize, String queue, int queueSize, String threadNamePrefix) {
        // todo 创建线程池
        return null;
    }
}

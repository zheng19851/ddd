package com.runssnail.ddd.pipeline.api;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.concurrent.NamedThreadFactory;

/**
 * 默认的线程池工厂
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public class DefaultExecutorFactory implements ExecutorFactory {
    private static final Logger log = LoggerFactory.getLogger(DefaultExecutorFactory.class);

    @Override
    public ExecutorService create(int corePoolSize, int maxPoolSize, String queue, int queueSize, String threadNamePrefix) {
        BlockingQueue<Runnable> workQueue = createQueue(queue, queueSize);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60, TimeUnit.SECONDS, workQueue,
                new NamedThreadFactory(threadNamePrefix), new RejectedHandler());
        return executor;
    }

    private BlockingQueue<Runnable> createQueue(String queue, int queueSize) {
        if (queueSize <= 0) {
            return new SynchronousQueue<>();
        }
        return new ArrayBlockingQueue<>(queueSize);
    }

    @Override
    public ScheduledExecutorService createScheduled(int corePoolSize, String threadNamePrefix) {
        return new ScheduledThreadPoolExecutor(corePoolSize, new NamedThreadFactory(threadNamePrefix));
    }

    private class RejectedHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.warn("executor is full, task={}, poolSize={}, corePoolSize={}, maxPoolSize={}, queue={}", r,
                    executor.getPoolSize(), executor.getCorePoolSize(), executor.getMaximumPoolSize()
                    , executor.getQueue().size());
        }
    }
}

package com.runssnail.ddd.pipeline.api.metadata;

import java.util.List;

/**
 * 阶段定义
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface PhaseDefinition extends Definition {

    /**
     * 阶段唯一标识
     *
     * @return
     */
    String getPhaseId();

    /**
     * 是否并行
     *
     * @return
     */
    boolean isParallel();

    /**
     * 超时时间，单位毫秒
     *
     * @return
     */
    long getTimeout();

    /**
     * 核心线程数
     *
     * @return
     */
    int getCorePoolSize();

    /**
     * 最大线程数
     *
     * @return
     */
    int getMaxPoolSize();

    /**
     * 队列类型
     *
     * @return
     */
    String getQueue();

    /**
     * 队列容量
     *
     * @return
     */
    int getQueueSize();

    List<String> getSteps();

    List<StepDefinition> getStepDefinitions();
}

package com.runssnail.ddd.pipeline.api.metadata;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 阶段定义
 *
 * @author zhengwei
 */
public class DefaultPhaseDefinition extends BaseDefinition implements PhaseDefinition {

    /**
     * 唯一标识
     */
    private String phaseId;

    /**
     * 是否并行执行
     */
    private boolean parallel;

    /**
     * 超时时间，单位毫秒
     */
    private long timeout;

    /**
     * 核心线程池
     */
    private int corePoolSize;

    /**
     * 最大线程池
     */
    private int maxPoolSize;

    /**
     * 队列类型
     */
    private String queue;

    /**
     * 队列
     */
    private int queueSize;

    /**
     * 步骤定义
     */
    private List<StepDefinition> stepDefinitions;

    /**
     * Default constructor
     */
    public DefaultPhaseDefinition() {
    }

    @Override
    public List<String> getSteps() {
        if (CollectionUtils.isEmpty(stepDefinitions)) {
            return Collections.emptyList();
        }
        return stepDefinitions.stream().map(definition -> definition.getStepId()).collect(Collectors.toList());
    }

    @Override
    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    @Override
    public boolean isParallel() {
        return parallel;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    @Override
    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    @Override
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    @Override
    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    @Override
    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    @Override
    public List<StepDefinition> getStepDefinitions() {
        return stepDefinitions;
    }

    public void setStepDefinitions(List<StepDefinition> stepDefinitions) {
        this.stepDefinitions = stepDefinitions;
    }


}
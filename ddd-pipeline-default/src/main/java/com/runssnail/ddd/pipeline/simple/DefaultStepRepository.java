package com.runssnail.ddd.pipeline.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepRepository;

/**
 * 默认的步骤管理器
 *
 * @author zhengwei
 */
public class DefaultStepRepository implements StepRepository {

    private static final Logger log = LoggerFactory.getLogger(DefaultStepRepository.class);

    /**
     *
     */
    public ConcurrentMap<String, Step> steps = new ConcurrentHashMap<>();

    /**
     * Default constructor
     */
    public DefaultStepRepository() {
    }

    /**
     * @param stepIds
     * @return
     */
    @Override
    public List<Step> getSteps(List<String> stepIds) {
        List<Step> steps = new ArrayList<>(stepIds.size());
        for (String name : stepIds) {
            Step step = this.getStep(name);
            if (step != null) {
                steps.add(step);
            }
        }
        return steps;
    }

    /**
     * @param stepId
     * @return
     */
    @Override
    public Step getStep(String stepId) {
        return steps.get(stepId);
    }

    /**
     * @param step
     */
    @Override
    public void update(Step step) {
        this.steps.put(step.getStepId(), step);
    }

    /**
     * @param step
     */
    @Override
    public void add(Step step) {
        this.steps.put(step.getStepId(), step);
    }

    /**
     * @param steps
     */
    @Override
    public void addAll(List<Step> steps) {
        for (Step step : steps) {
            this.add(step);
        }
    }

    /**
     * 删除
     *
     * @param stepId 唯一标识
     * @return
     */
    @Override
    public Step remove(String stepId) {
        log.info("remove Step {}", stepId);
        return this.steps.remove(stepId);
    }

    /**
     * @param stepIds
     * @return
     */
    @Override
    public List<Step> removeAll(List<String> stepIds) {
        log.info("removeAll Step {}", stepIds);
        List<Step> removedList = new ArrayList<>(stepIds.size());
        for (String name : stepIds) {
            Step step = this.steps.remove(name);
            if (step != null) {
                removedList.add(step);
            }
        }
        return removedList;
    }

}
package com.runssnail.ddd.pipeline.memory;

import static com.runssnail.ddd.pipeline.api.constant.Constants.DEFAULT_SCHEDULED_CORE_POOL_SIZE;
import static com.runssnail.ddd.pipeline.api.constant.Constants.DEFAULT_SCHEDULED_PERIOD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepFactoryRepository;
import com.runssnail.ddd.pipeline.api.StepRepository;
import com.runssnail.ddd.pipeline.api.concurrent.ExecutorFactory;
import com.runssnail.ddd.pipeline.api.exception.StepDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinitionRepository;
import com.runssnail.ddd.pipeline.api.spi.StepFactory;

/**
 * 默认的步骤管理器
 *
 * @author zhengwei
 */
public class MemoryStepRepository implements StepRepository {
    private static final Logger log = LoggerFactory.getLogger(MemoryStepRepository.class);

    /**
     * 调度周期，单位毫秒
     */
    private long scheduledPeriod = DEFAULT_SCHEDULED_PERIOD;

    /**
     * 步骤本地缓存
     */
    public ConcurrentMap<String, Step> steps = new ConcurrentHashMap<>();

    /**
     * 步骤定义仓储
     */
    private StepDefinitionRepository stepDefinitionRepository;

    /**
     * StepFactory仓储
     */
    private StepFactoryRepository stepFactoryRepository;

    /**
     * 定时刷新缓存用
     */
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * 线程池创建工厂
     */
    private ExecutorFactory executorFactory;

    /**
     * 当前更新的最新时间
     */
    private volatile long lastUpdateTime;

    /**
     * Default constructor
     */
    public MemoryStepRepository() {
    }

    /**
     * @param stepIds
     * @return
     */
    @Override
    public List<Step> getSteps(List<String> stepIds) {
        List<Step> steps = new ArrayList<>(stepIds.size());
        for (String stepId : stepIds) {
            Step step = this.getStep(stepId);
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
    public void save(Step step) {
        log.info("save Step {}", step.getStepId());
        this.steps.put(step.getStepId(), step);
    }

    /**
     * @param steps
     */
    @Override
    public void saveAll(List<Step> steps) {
        for (Step step : steps) {
            this.save(step);
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
        for (String stepId : stepIds) {
            Step step = this.steps.remove(stepId);
            if (step != null) {
                removedList.add(step);
            }
        }
        return removedList;
    }

    @Override
    public void init() {
        log.info("init start");
        refreshSteps();
        initRefreshThread();
        log.info("init end");
    }

    private void refreshSteps() {
        // 保存当前更新时间
        long lastUpdateTime = this.lastUpdateTime;
        log.info("refreshSteps start, lastUpdateTime={}", lastUpdateTime);
        List<StepDefinition> stepDefinitions = stepDefinitionRepository.getStepDefinitions(lastUpdateTime);
        if (CollectionUtils.isEmpty(stepDefinitions)) {
            log.warn("refreshSteps skipped, cannot find any stepDefinitions, lastUpdateTime={}", lastUpdateTime);
            return;
        }

        for (StepDefinition sd : stepDefinitions) {
            if (sd.isRemoved()) {
                this.remove(sd.getStepId());
            } else {
                StepFactory stepFactory = this.stepFactoryRepository.getStepFactory(sd.getStepType());
                if (stepFactory == null) {
                    throw new StepDefinitionException("Cannot find StepFactory stepId=" + sd.getStepId() + ", type=" + sd.getStepType());
                }
                Step step = stepFactory.create(sd);
                if (step != null) {
                    this.save(step);
                }
            }
            if (sd.getUpdateTime() > this.lastUpdateTime) {
                this.lastUpdateTime = sd.getUpdateTime();
            }
        }

        log.info("refreshSteps end, find {} StepDefinition, [{}->{}], {}", stepDefinitions.size(), lastUpdateTime, this.lastUpdateTime, stepDefinitions);
    }

    private void initRefreshThread() {
        if (this.scheduledExecutorService == null) {
            // todo 参数配置化
            ScheduledExecutorService executor = this.executorFactory.createScheduled(DEFAULT_SCHEDULED_CORE_POOL_SIZE, "RefreshSteps");

            // 每隔一段时间去刷新
            // todo period参数配置化
            executor.scheduleAtFixedRate(new RefreshStepDefinitionTask(), scheduledPeriod, scheduledPeriod, TimeUnit.SECONDS);
            this.scheduledExecutorService = executor;
            log.info("initRefreshThread end");
        }

    }

    private class RefreshStepDefinitionTask implements Runnable {

        @Override
        public void run() {
            try {
                refreshSteps();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void close() {
        if (this.scheduledExecutorService != null) {
            this.scheduledExecutorService.shutdown();
        }
    }

    public StepDefinitionRepository getStepDefinitionRepository() {
        return stepDefinitionRepository;
    }

    public void setStepDefinitionRepository(StepDefinitionRepository stepDefinitionRepository) {
        this.stepDefinitionRepository = stepDefinitionRepository;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public ExecutorFactory getExecutorFactory() {
        return executorFactory;
    }

    public void setExecutorFactory(ExecutorFactory executorFactory) {
        this.executorFactory = executorFactory;
    }

    public long getScheduledPeriod() {
        return scheduledPeriod;
    }

    public void setScheduledPeriod(long scheduledPeriod) {
        this.scheduledPeriod = scheduledPeriod;
    }

    public StepFactoryRepository getStepFactoryRepository() {
        return stepFactoryRepository;
    }

    public void setStepFactoryRepository(StepFactoryRepository stepFactoryRepository) {
        this.stepFactoryRepository = stepFactoryRepository;
    }
}
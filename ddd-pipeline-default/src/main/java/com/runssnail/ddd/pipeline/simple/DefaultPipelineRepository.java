package com.runssnail.ddd.pipeline.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.Phase;
import com.runssnail.ddd.pipeline.api.PhaseFactory;
import com.runssnail.ddd.pipeline.api.PhaseRepository;
import com.runssnail.ddd.pipeline.api.Pipeline;
import com.runssnail.ddd.pipeline.api.PipelineFactory;
import com.runssnail.ddd.pipeline.api.PipelineRepository;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepFactory;
import com.runssnail.ddd.pipeline.api.StepRepository;
import com.runssnail.ddd.pipeline.api.exception.PipelineDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinitionRepository;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;

/**
 * DefaultPipelineRepository
 * <p>
 * 用本地内存实现Pipeline缓存
 *
 * @author zhengwei
 */
public class DefaultPipelineRepository implements PipelineRepository {

    private static final Logger log = LoggerFactory.getLogger(DefaultPipelineRepository.class);

    private static final long DEFAULT_SCHEDULED_PERIOD = 10000L;
    private static final int DEFAULT_CORE_POOL_SIZE = 1;

    /**
     * key=pipelineId
     */
    private ConcurrentMap<String, Pipeline> pipelines = new ConcurrentHashMap<>();

    /**
     * 流程定义仓储
     */
    private PipelineDefinitionRepository pipelineDefinitionRepository;

    /**
     *
     */
    private PipelineFactory pipelineFactory;

    private PhaseFactory phaseFactory;

    private PhaseRepository phaseRepository;

    private StepFactory stepFactory;

    private StepRepository stepRepository;

    /**
     * 定时刷新缓存用
     */
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * 当前更新的流程定义最新时间
     */
    private volatile long lastPipelineUpdateTime;

    /**
     * Default constructor
     */
    public DefaultPipelineRepository() {
    }

    /**
     * @param pipelineId
     * @return
     */
    @Override
    public Pipeline getPipeline(String pipelineId) {
        return pipelines.get(pipelineId);
    }

    /**
     * @param pipelines
     */
    @Override
    public void addAll(List<Pipeline> pipelines) {
        for (Pipeline pipeline : pipelines) {
            this.add(pipeline);
        }
    }

    /**
     * @param pipeline
     */
    @Override
    public void add(Pipeline pipeline) {
        log.info("add Pipeline {}", pipeline.getPipelineId());
        pipelines.put(pipeline.getPipelineId(), pipeline);
    }

    /**
     * @param pipeline
     */
    @Override
    public void update(Pipeline pipeline) {
        log.info("update Pipeline {}", pipeline.getPipelineId());
        this.pipelines.put(pipeline.getPipelineId(), pipeline);
    }

    /**
     * @param pipelineId
     * @return
     */
    @Override
    public Pipeline remove(String pipelineId) {
        log.info("remove Pipeline {}", pipelineId);
        return pipelines.remove(pipelineId);
    }

    /**
     * @param pipelineIds
     * @return
     */
    @Override
    public List<Pipeline> removeAll(List<String> pipelineIds) {
        log.info("removeAll Pipeline {}", pipelineIds);
        List<Pipeline> removedList = new ArrayList<>(pipelineIds.size());
        for (String name : pipelineIds) {
            Pipeline pipeline = this.pipelines.remove(name);
            if (pipeline != null) {
                removedList.add(pipeline);
            }
        }
        return removedList;
    }

    @Override
    public boolean contains(String pipelineId) {
        return this.pipelines.containsKey(pipelineId);
    }

    @Override
    public List<Pipeline> getAllPipelines() {
        return new ArrayList<>(this.pipelines.values());
    }

    @Override
    public void init() {
        Validate.notNull(this.pipelineDefinitionRepository, "PipelineDefinitionRepository is required");
        Validate.notNull(this.pipelineFactory, "pipelineFactory is required");
        Validate.notNull(this.phaseFactory, "phaseFactory is required");
        Validate.notNull(this.phaseRepository, "phaseRepository is required");
        Validate.notNull(this.stepFactory, "stepFactory is required");
        Validate.notNull(this.stepRepository, "stepRepository is required");

        refreshPipelines(true);

        initRefreshThread();
    }

    private void initRefreshThread() {
        initRefreshPipelineDefinitionThread();
    }

    private void initRefreshPipelineDefinitionThread() {
        // todo 参数配置化，指定线程工厂
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(DEFAULT_CORE_POOL_SIZE);

        // 每隔一段时间去刷新
        // todo period参数配置化
        executor.scheduleAtFixedRate(new RefreshPipelineDefinitionTask(), DEFAULT_SCHEDULED_PERIOD, DEFAULT_SCHEDULED_PERIOD, TimeUnit.SECONDS);
        this.scheduledExecutorService = executor;
    }

    private class RefreshPipelineDefinitionTask implements Runnable {

        @Override
        public void run() {
            try {
                refreshPipelineDefinition();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void refreshPipelineDefinition() {
        this.refreshPipelines(false);
    }

    private void refreshPipelines(boolean onlyEnabled) {
        List<PipelineDefinition> pipelineDefinitions = pipelineDefinitionRepository.getPipelineDefinitions(onlyEnabled, this.lastPipelineUpdateTime);
        if (CollectionUtils.isEmpty(pipelineDefinitions)) {
            log.warn("Cannot find any pipelineDefinitions, lastPipelineUpdateTime={}", lastPipelineUpdateTime);
            return;
        }

        if (onlyEnabled) {
            // 初始化时，需要校验下唯一标识是否重复
            this.validate(pipelineDefinitions);
        }

        for (PipelineDefinition pd : pipelineDefinitions) {
            if (pd.isRemoved()) {

                // 如果Phase是不能重用的，那么存在内存泄漏问题
                this.remove(pd.getPipelineId());
            } else {
                Pipeline pipeline = pipelineFactory.create(pd);
                this.add(pipeline);
                refreshPhases(pd.getPhaseDefinitions());
            }

        }

        this.validate();
        resetLastPipelineUpdateTime(pipelineDefinitions);

        log.info("refreshPipelines end, find {} PipelineDefinition, {}", pipelineDefinitions.size(), pipelineDefinitions);
    }

    /**
     * 验证流程定义是否有效，流程定义里的phase和step是否都存在
     */
    protected void validate() {
    }

    private void resetLastPipelineUpdateTime(List<PipelineDefinition> pipelineDefinitions) {
        long max = this.lastPipelineUpdateTime;
        for (PipelineDefinition pipelineDefinition : pipelineDefinitions) {
            if (pipelineDefinition.getUpdateTime() > max) {
                max = pipelineDefinition.getUpdateTime();
            }
        }

        this.lastPipelineUpdateTime = max;
    }

    private void refreshPhases(List<PhaseDefinition> phaseDefinitions) {
        if (CollectionUtils.isEmpty(phaseDefinitions)) {
            log.info("phaseDefinitions is empty");
            return;
        }

        for (PhaseDefinition phaseDefinition : phaseDefinitions) {
            if (phaseDefinition.isRemoved()) {
                phaseRepository.remove(phaseDefinition.getPhaseId());
            } else {
                Phase phase = this.phaseFactory.create(phaseDefinition);
                this.phaseRepository.add(phase);
                refreshSteps(phaseDefinition.getStepDefinitions());
            }
        }

        log.info("refreshPhases end, find {} PhaseDefinition", phaseDefinitions.size());
    }

    private void refreshSteps(List<StepDefinition> stepDefinitions) {
        if (CollectionUtils.isEmpty(stepDefinitions)) {
            log.info("stepDefinitions is empty");
            return;
        }

        for (StepDefinition stepDefinition : stepDefinitions) {
            if (stepDefinition.isRemoved()) {
                stepRepository.remove(stepDefinition.getStepId());
            } else {
                Step step = this.stepFactory.create(stepDefinition);
                this.stepRepository.add(step);
            }

        }
    }

    /**
     * 验证是否重复
     *
     * @param pipelineDefinitions
     */
    private void validate(List<PipelineDefinition> pipelineDefinitions) {
        for (PipelineDefinition pipelineDefinition : pipelineDefinitions) {
            String pipelineId = pipelineDefinition.getPipelineId();
            if (this.contains(pipelineId)) {
                throw new PipelineDefinitionException(pipelineId, "pipeline id duplicated '" + pipelineId + "'");
            }
        }

    }

    @Override
    public void close() {
        if (this.scheduledExecutorService != null) {
            this.scheduledExecutorService.shutdown();
        }
    }

    public PipelineDefinitionRepository getPipelineDefinitionRepository() {
        return pipelineDefinitionRepository;
    }

    public void setPipelineDefinitionRepository(PipelineDefinitionRepository pipelineDefinitionRepository) {
        this.pipelineDefinitionRepository = pipelineDefinitionRepository;
    }

    public PipelineFactory getPipelineFactory() {
        return pipelineFactory;
    }

    public void setPipelineFactory(PipelineFactory pipelineFactory) {
        this.pipelineFactory = pipelineFactory;
    }

    public PhaseFactory getPhaseFactory() {
        return phaseFactory;
    }

    public void setPhaseFactory(PhaseFactory phaseFactory) {
        this.phaseFactory = phaseFactory;
    }

    public PhaseRepository getPhaseRepository() {
        return phaseRepository;
    }

    public void setPhaseRepository(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    public StepFactory getStepFactory() {
        return stepFactory;
    }

    public void setStepFactory(StepFactory stepFactory) {
        this.stepFactory = stepFactory;
    }

    public StepRepository getStepRepository() {
        return stepRepository;
    }

    public void setStepRepository(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }
}
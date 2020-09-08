package com.runssnail.ddd.pipeline.simple;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.pipeline.api.DefaultPipelineErrorHandler;
import com.runssnail.ddd.pipeline.api.Exchange;
import com.runssnail.ddd.pipeline.api.Phase;
import com.runssnail.ddd.pipeline.api.PhaseFactory;
import com.runssnail.ddd.pipeline.api.PhaseRepository;
import com.runssnail.ddd.pipeline.api.Pipeline;
import com.runssnail.ddd.pipeline.api.PipelineEngine;
import com.runssnail.ddd.pipeline.api.PipelineErrorHandler;
import com.runssnail.ddd.pipeline.api.PipelineFactory;
import com.runssnail.ddd.pipeline.api.PipelineRepository;
import com.runssnail.ddd.pipeline.api.Step;
import com.runssnail.ddd.pipeline.api.StepFactory;
import com.runssnail.ddd.pipeline.api.StepRepository;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinitionRepository;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;


/**
 * 默认的流程引擎
 *
 * @author zhengwei
 */
public class DefaultPipelineEngine implements PipelineEngine {

    private PipelineErrorHandler pipelineErrorHandler;

    /**
     * 流程定义仓储
     */
    private PipelineDefinitionRepository pipelineDefinitionRepository;

//    /**
//     *
//     */
//    private PhaseDefinitionRepository phaseDefinitionRepository;
//
//    /**
//     *
//     */
//    private StepDefinitionRepository stepDefinitionRepository;

    /**
     *
     */
    private PipelineRepository pipelineRepository;

    /**
     *
     */
    private PipelineFactory pipelineFactory;

    private PhaseFactory phaseFactory;

    private PhaseRepository phaseRepository;

    private StepFactory stepFactory;

    private StepRepository stepRepository;

    /**
     * Default constructor
     */
    public DefaultPipelineEngine() {
    }

    /**
     * 加载流程初始化
     */
    public void init() {
        initPipelines();

        initPipelineErrorHandler();

        initRefreshThread();
    }

    private void initRefreshThread() {
        // todo 实现定时刷新缓存策略
        // 每隔一段时间去刷新
    }

    private void initPipelines() {
        Validate.notNull(this.pipelineDefinitionRepository, "PipelineDefinitionRepository is required");
        Map<String, PipelineDefinition> pipelineDefinitions = pipelineDefinitionRepository.getPipelineDefinitions();
        this.validate(pipelineDefinitions);

        for (Map.Entry<String, PipelineDefinition> entry : pipelineDefinitions.entrySet()) {
            PipelineDefinition pd = entry.getValue();
            Pipeline pipeline = pipelineFactory.create(pd);
            pipelineRepository.add(pipeline);
            initPhases(pd.getPhaseDefinitions());
        }

        this.validate();
    }

    private void initPhases(List<PhaseDefinition> phaseDefinitions) {
        for (PhaseDefinition phaseDefinition : phaseDefinitions) {
            Phase phase = this.phaseFactory.create(phaseDefinition);
            this.phaseRepository.add(phase);
            initSteps(phaseDefinition.getStepDefinitions());
        }
    }

    private void initSteps(List<StepDefinition> stepDefinitions) {
        for (StepDefinition stepDefinition : stepDefinitions) {
            Step step = this.stepFactory.create(stepDefinition);
            this.stepRepository.add(step);
        }
    }

    private void validate(Map<String, PipelineDefinition> pipelineDefinitions) {
        Set<Map.Entry<String, PipelineDefinition>> entries = pipelineDefinitions.entrySet();
        for (Map.Entry<String, PipelineDefinition> entry : entries) {
            String pipelineId = entry.getKey();
            if (pipelineRepository.contains(pipelineId)) {
                throw new RuntimeException("pipeline id duplicated '" + pipelineId + "'");
            }
        }

    }

    private void initPipelineErrorHandler() {
        if (this.pipelineErrorHandler == null) {
            this.pipelineErrorHandler = new DefaultPipelineErrorHandler();
        }
    }

    /**
     * @param pipelineId
     * @return
     */
    @Override
    public Pipeline getPipeline(String pipelineId) {
        return pipelineRepository.getPipeline(pipelineId);
    }

    /**
     * @return
     */
    @Override
    public List<Pipeline> getAllPipelines() {
        return this.pipelineRepository.getAllPipelines();
    }

    @Override
    public void setPipelineErrorHandler(PipelineErrorHandler handler) {
        this.pipelineErrorHandler = handler;
    }

    @Override
    public void execute(Exchange exchange) {
        Pipeline pipeline = this.getPipeline(exchange.getPipelineId());
        try {
            pipeline.execute(exchange);
        } catch (Exception e) {
            this.pipelineErrorHandler.onException(exchange, e);
        }

    }

    /**
     * 验证流程定义是否有效，流程定义里的phase和step是否都存在
     */
    protected void validate() {
    }

}
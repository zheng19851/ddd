package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * @author zhengwei
 */
public abstract class BasePipeline implements Pipeline {


    /**
     * 流程唯一标识
     */
    protected String pipelineId;

    /**
     * 阶段唯一标识
     */
    protected List<String> phases;

    /**
     *
     */
    protected PhaseRepository phaseRepository;

    /**
     * Default constructor
     */
    public BasePipeline() {
    }

    /**
     * 创建流程执行对象
     *
     * @param pipelineId      流程唯一标识
     * @param phases          阶段标识
     * @param phaseRepository 阶段仓储
     */
    public BasePipeline(String pipelineId, List<String> phases, PhaseRepository phaseRepository) {
        Validate.notBlank(pipelineId);
        Validate.notEmpty(phases);
        Validate.notNull(phaseRepository);
        this.pipelineId = pipelineId;
        this.phases = phases;
        this.phaseRepository = phaseRepository;
    }

    /**
     * @param exchange
     */
    @Override
    public void execute(Exchange exchange) throws ExecuteException {
        this.doExecute(exchange);
    }

    /**
     * @return
     */
    @Override
    public String getPipelineId() {
        return this.pipelineId;
    }

    /**
     * @param exchange
     */
    protected void doExecute(Exchange exchange) {
        List<Phase> phases = phaseRepository.getPhases(this.phases);
        for (Phase phase : phases) {
            phase.execute(exchange);
        }

    }

}
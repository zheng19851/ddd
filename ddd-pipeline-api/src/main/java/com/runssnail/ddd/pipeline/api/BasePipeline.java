package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * @author zhengwei
 */
public abstract class BasePipeline implements Pipeline {

    /**
     * Default constructor
     */
    public BasePipeline() {
    }

    /**
     * 流程唯一标识
     */
    private String pipelineId;

    /**
     * 阶段唯一标识
     */
    private List<String> phases;

    /**
     *
     */
    private PhaseRepository phaseRepository;

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
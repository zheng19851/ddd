package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * @author zhengwei
 */
public abstract class BasePipeline implements Pipeline {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 流程唯一标识
     */
    protected String pipelineId;

    /**
     * 阶段唯一标识
     */
    protected List<String> phases;

    /**
     * 阶段仓储
     */
    protected PhaseRepository phaseRepository;

    /**
     * 中断策略
     */
    protected TerminateStrategy terminateStrategy;

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

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }

    /**
     * @param exchange
     */
    @Override
    public void execute(Exchange exchange) throws ExecuteException {
        log.info("execute pipeline start {}", this.pipelineId);
        long start = System.currentTimeMillis();
        try {
            this.doExecute(exchange);
        } finally {
            log.info("execute pipeline end {}, cost {} ms", this.pipelineId, (System.currentTimeMillis() - start));
        }
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

    public PhaseRepository getPhaseRepository() {
        return phaseRepository;
    }

    public void setPhaseRepository(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    @Override
    public TerminateStrategy getTerminateStrategy() {
        return terminateStrategy;
    }

    public void setTerminateStrategy(TerminateStrategy terminateStrategy) {
        this.terminateStrategy = terminateStrategy;
    }
}
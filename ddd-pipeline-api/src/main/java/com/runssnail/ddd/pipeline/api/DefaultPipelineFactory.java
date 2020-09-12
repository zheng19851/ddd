package com.runssnail.ddd.pipeline.api;

import org.apache.commons.lang3.StringUtils;

import com.runssnail.ddd.pipeline.api.exception.PipelineDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;
import com.runssnail.ddd.pipeline.api.terminate.DefaultTerminateStrategyFactory;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategyFactory;

/**
 * 默认的流程执行对象工厂
 *
 * @author zhengwei
 */
public class DefaultPipelineFactory implements PipelineFactory {


    /**
     * 阶段仓储
     */
    private PhaseRepository phaseRepository;

    /**
     * 中断策略工厂
     */
    private TerminateStrategyFactory terminateStrategyFactory;

    /**
     * Default constructor
     */
    public DefaultPipelineFactory() {
    }

    /**
     * @param pd 流程定义
     * @return
     */
    @Override
    public Pipeline create(PipelineDefinition pd) throws PipelineDefinitionException {
        DefaultPipeline defaultPipeline = new DefaultPipeline(pd.getPipelineId(), pd.getPhases(), this.phaseRepository);

        // 中断策略
        String strategy = pd.getAttribute("terminate.strategy");
        if (StringUtils.isNotBlank(strategy)) {
            TerminateStrategy terminateStrategy = terminateStrategyFactory.create(strategy);
            defaultPipeline.setTerminateStrategy(terminateStrategy);
        }

        return defaultPipeline;
    }

    @Override
    public void init() {
        initTerminateStrategyFactory();
    }

    private void initTerminateStrategyFactory() {
        if (this.terminateStrategyFactory == null) {
            this.terminateStrategyFactory = new DefaultTerminateStrategyFactory();
        }
    }

    @Override
    public void close() {

    }

    public PhaseRepository getPhaseRepository() {
        return phaseRepository;
    }

    public void setPhaseRepository(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    public TerminateStrategyFactory getTerminateStrategyFactory() {
        return terminateStrategyFactory;
    }

    public void setTerminateStrategyFactory(TerminateStrategyFactory terminateStrategyFactory) {
        this.terminateStrategyFactory = terminateStrategyFactory;
    }

}
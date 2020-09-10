package com.runssnail.ddd.pipeline.memory;

import com.runssnail.ddd.pipeline.api.DefaultPipelineEngine;
import com.runssnail.ddd.pipeline.api.PipelineEngine;
import com.runssnail.ddd.pipeline.api.PipelineExceptionHandler;
import com.runssnail.ddd.pipeline.api.PipelineRepository;

/**
 * 实现Spring FactoryBean
 *
 * @author zhengwei
 */
public class PipelineEngineFactoryBean {

    /**
     * 流程异常处理器
     */
    private PipelineExceptionHandler pipelineExceptionHandler;

    /**
     *
     */
    private PipelineRepository pipelineRepository;

    /**
     * Default constructor
     */
    public PipelineEngineFactoryBean() {
    }

    /**
     *
     */
    private PipelineEngine pipelineEngine;

    public void init() {
        // todo 待实现
        DefaultPipelineEngine pipelineEngine = new DefaultPipelineEngine();
        pipelineEngine.setPipelineExceptionHandler(pipelineExceptionHandler);
        pipelineEngine.setPipelineRepository(pipelineRepository);
        pipelineEngine.init();
        this.pipelineEngine = pipelineEngine;
    }
}
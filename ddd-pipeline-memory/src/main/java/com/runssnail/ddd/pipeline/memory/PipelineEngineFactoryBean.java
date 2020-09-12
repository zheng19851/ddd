package com.runssnail.ddd.pipeline.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.pipeline.api.DefaultPipelineEngine;
import com.runssnail.ddd.pipeline.api.PipelineEngine;
import com.runssnail.ddd.pipeline.api.PipelineErrorHandler;
import com.runssnail.ddd.pipeline.api.PipelineRepository;

/**
 * 实现Spring FactoryBean
 *
 * @author zhengwei
 */
@Lazy
@Component
public class PipelineEngineFactoryBean implements FactoryBean<PipelineEngine>, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(PipelineEngineFactoryBean.class);

    /**
     * 流程异常处理器
     */
    private PipelineErrorHandler pipelineErrorHandler;

    /**
     *
     */
    @Autowired
    private PipelineRepository pipelineRepository;

    /**
     *
     */
    private PipelineEngine pipelineEngine;

    public void init() {
        // todo 待实现
        DefaultPipelineEngine pipelineEngine = new DefaultPipelineEngine();
        pipelineEngine.setPipelineErrorHandler(pipelineErrorHandler);
        pipelineEngine.setPipelineRepository(pipelineRepository);
        pipelineEngine.init();
        this.pipelineEngine = pipelineEngine;
    }

    @Override
    public PipelineEngine getObject() throws Exception {
        return this.pipelineEngine;
    }

    @Override
    public Class<?> getObjectType() {
        return PipelineEngine.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public PipelineErrorHandler getPipelineErrorHandler() {
        return pipelineErrorHandler;
    }

    public void setPipelineErrorHandler(PipelineErrorHandler pipelineErrorHandler) {
        this.pipelineErrorHandler = pipelineErrorHandler;
    }

    public PipelineRepository getPipelineRepository() {
        return pipelineRepository;
    }

    public void setPipelineRepository(PipelineRepository pipelineRepository) {
        this.pipelineRepository = pipelineRepository;
    }
}
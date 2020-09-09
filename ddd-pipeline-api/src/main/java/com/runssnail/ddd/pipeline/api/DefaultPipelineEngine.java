package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 默认的流程引擎
 *
 * @author zhengwei
 */
public class DefaultPipelineEngine implements PipelineEngine {
    private static final Logger log = LoggerFactory.getLogger(DefaultPipelineEngine.class);

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
    public DefaultPipelineEngine() {
    }

    /**
     * 加载流程初始化
     */
    @Override
    public void init() {
        Validate.notNull(this.pipelineRepository, "PipelineRepository is required");

        initPipelineExceptionHandler();

        log.info("pipeline engine init end");
    }

    @Override
    public void close() {

    }

    private void initPipelineExceptionHandler() {
        if (this.pipelineExceptionHandler == null) {
            this.pipelineExceptionHandler = new DefaultPipelineExceptionHandler();
        }
    }

    @Override
    public void execute(Exchange exchange) {
        Pipeline pipeline = this.getPipeline(exchange.getPipelineId());
        if (pipeline == null) {
            // todo 不存在，设置错误码
            log.warn("cannot find the Pipeline {}", exchange.getPipelineId());
            return;
        }
        try {
            pipeline.execute(exchange);
        } catch (Exception e) {
            this.pipelineExceptionHandler.onException(exchange, e);
        }

    }

    /**
     * 获取流程执行对象
     *
     * @param pipelineId 流程唯一标识
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
    public void setPipelineExceptionHandler(PipelineExceptionHandler handler) {
        this.pipelineExceptionHandler = handler;
    }

    public PipelineExceptionHandler getPipelineExceptionHandler() {
        return pipelineExceptionHandler;
    }

    public PipelineRepository getPipelineRepository() {
        return pipelineRepository;
    }

    public void setPipelineRepository(PipelineRepository pipelineRepository) {
        this.pipelineRepository = pipelineRepository;
    }
}
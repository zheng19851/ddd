package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.exception.PipelineExecuteErrorCode;


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
    private PipelineErrorHandler pipelineErrorHandler;

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

        initPipelineErrorHandler();

        log.info("pipeline engine init end");
    }

    @Override
    public void close() {

    }

    private void initPipelineErrorHandler() {
        if (this.pipelineErrorHandler == null) {
            this.pipelineErrorHandler = new DefaultPipelineErrorHandler();
        }
    }

    @Override
    public void execute(Exchange exchange) {
        Validate.notNull(exchange, "exchange is required");
        Validate.notBlank(exchange.getPipelineId(), "exchange.pipelineId is required");
        Validate.notNull(exchange.getBody(), "exchange.body is required");

        Pipeline pipeline = this.getPipeline(exchange.getPipelineId());
        if (pipeline == null) {
            log.warn("cannot find the Pipeline {}", exchange.getPipelineId());
            exchange.setErrorCode(PipelineExecuteErrorCode.PIPELINE_NOT_EXISTS.getErrorCode());
            exchange.setErrorMsg(PipelineExecuteErrorCode.PIPELINE_NOT_EXISTS.getErrorMsg());
            return;
        }

        try {
            pipeline.execute(exchange);
        } catch (Exception e) {
            this.pipelineErrorHandler.onException(exchange, e);
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
    public void setPipelineErrorHandler(PipelineErrorHandler handler) {
        this.pipelineErrorHandler = handler;
    }

    @Override
    public PipelineErrorHandler getPipelineErrorHandler() {
        return pipelineErrorHandler;
    }

    public PipelineRepository getPipelineRepository() {
        return pipelineRepository;
    }

    public void setPipelineRepository(PipelineRepository pipelineRepository) {
        this.pipelineRepository = pipelineRepository;
    }
}
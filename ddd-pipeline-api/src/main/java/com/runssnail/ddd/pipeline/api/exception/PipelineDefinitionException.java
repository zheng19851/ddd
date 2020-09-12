package com.runssnail.ddd.pipeline.api.exception;

/**
 * 流程元数据定义错误
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class PipelineDefinitionException extends DefinitionException {

    private String pipelineId;

    public PipelineDefinitionException(String msg) {
        super(msg);
    }

    public PipelineDefinitionException(Throwable e) {
        super(e);
    }

    public PipelineDefinitionException(String pipelineId, String msg) {
        super(msg);
        this.pipelineId = pipelineId;
    }

    public PipelineDefinitionException(String msg, Throwable e) {
        super(msg, e);
    }

    public String getPipelineId() {
        return pipelineId;
    }
}

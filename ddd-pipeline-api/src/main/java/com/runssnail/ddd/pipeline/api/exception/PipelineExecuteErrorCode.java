package com.runssnail.ddd.pipeline.api.exception;

import com.runssnail.ddd.common.exception.ErrorCode;

/**
 * 流程执行错误码
 *
 * @author zhengwei
 * Created on 2020-09-10
 */
public enum PipelineExecuteErrorCode implements ErrorCode {

    /**
     * 不存在
     */
    PIPELINE_NOT_EXISTS(40000, "流程不存在");

    private int errorCode;

    private String errorMsg;

    PipelineExecuteErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}

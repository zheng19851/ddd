package com.runssnail.ddd.pipeline.api;

import java.util.HashMap;
import java.util.Map;

import com.runssnail.ddd.common.exception.BasicErrorCode;

/**
 * 执行上下文参数
 * 注意：此对象为大对象
 *
 * @author zhengwei
 */
public class DefaultExchange implements Exchange {
    private static final long serialVersionUID = 2409035761108469697L;

    /**
     * 流程名称（唯一标识）
     */
    private String pipelineId;

    /**
     * 扩展属性，可以保存执行过程中间数据
     */
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * 原始请求数据
     */
    private Object requestBody;

    /**
     * 中间数据
     */
    private Object body;

    /**
     * 异常
     */
    private Throwable throwable;

    private int errorCode = BasicErrorCode.SUCCESS.getErrorCode();

    private String errorMsg = BasicErrorCode.SUCCESS.getErrorMsg();

    @Override
    public boolean isSuccess() {
        return throwable == null && this.errorCode == BasicErrorCode.SUCCESS.getErrorCode();
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    /**
     * @param name
     * @param value
     */
    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    @Override
    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Object getRequestBody() {
        return requestBody;
    }

    @Override
    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public Object getBody() {
        return body;
    }

    @Override
    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

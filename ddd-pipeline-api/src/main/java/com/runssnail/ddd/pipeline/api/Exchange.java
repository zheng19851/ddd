package com.runssnail.ddd.pipeline.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行上下文参数
 * 注意：此对象为大对象
 *
 * @author zhengwei
 */
public class Exchange {

    /**
     * 流程名称（唯一标识）
     */
    private String pipelineId;

    /**
     * 扩展属性，可以保存执行过程中间数据
     */
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * 数据
     */
    private Object body;

    /**
     * 异常
     */
    private Throwable throwable;

    public boolean isSuccess() {
        return throwable == null;
    }

    public int getErrorCode() {
        // todo 待实现
        return 1;
    }

    public String getErrorMsg() {
        // todo 待实现
        return null;
    }

    /**
     * @param name
     * @param value
     */
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    /**
     * @param name
     * @return
     */
    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
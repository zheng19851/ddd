package com.runssnail.ddd.pipeline.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.common.exception.BasicErrorCode;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * @author zhengwei
 * Created on 2020-09-12
 */
public abstract class BaseExchange implements Exchange<ConcurrentMap<String, Object>> {

    private String exchangeId;

    /**
     * 流程唯一标识
     */
    private String pipelineId;

    /**
     * 扩展属性，可以保存执行过程中间数据，Phase存在并行，所以这里用了ConcurrentMap
     */
    private Map<String, Object> attributes = new ConcurrentHashMap<>();

    /**
     * 请求原始数据，中间不要去改变
     */
    private Map<String, Object> request = new HashMap<>();

    /**
     * 中间数据
     */
    private ConcurrentMap<String, Object> body;

    /**
     * 异常
     */
    private volatile Throwable throwable;

    /**
     * 中断策略
     */
    private TerminateStrategy terminateStrategy;

    private volatile int errorCode = BasicErrorCode.SUCCESS.getErrorCode();

    private volatile String errorMsg = BasicErrorCode.SUCCESS.getErrorMsg();

    @Override
    public void init() {
        initExchangeId();
    }

    protected void initExchangeId() {
        if (StringUtils.isBlank(this.exchangeId)) {
            this.exchangeId = UUID.randomUUID().toString();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isSuccess() {
        return throwable == null && this.errorCode == BasicErrorCode.SUCCESS.getErrorCode();
    }

    @Override
    public Object getValue(String name) {
        Validate.notBlank(name);

        Object v = this.getValueFromBody(name);
        if (v != null) {
            return v;
        }
        v = this.getRequest().get(name);
        if (v != null) {
            return v;
        }

        return this.getAttribute(name);
    }

    private Object getValueFromBody(String name) {
        return this.body != null ? this.body.get(name) : null;
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
        Validate.notBlank(pipelineId);
        this.pipelineId = pipelineId;
    }

    @Override
    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        Validate.notBlank(exchangeId);
        this.exchangeId = exchangeId;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public ConcurrentMap<String, Object> getBody() {
        return body;
    }

    @Override
    public void setBody(ConcurrentMap<String, Object> body) {
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
    public TerminateStrategy getTerminateStrategy() {
        return terminateStrategy;
    }

    @Override
    public void setTerminateStrategy(TerminateStrategy terminateStrategy) {
        Validate.notNull(terminateStrategy);
        this.terminateStrategy = terminateStrategy;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}

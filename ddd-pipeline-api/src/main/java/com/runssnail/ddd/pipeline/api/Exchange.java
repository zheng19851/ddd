package com.runssnail.ddd.pipeline.api;

import java.io.Serializable;
import java.util.Map;

/**
 * 执行上下文参数
 * 注意：此对象为大对象
 *
 * @author zhengwei
 */
public interface Exchange extends Serializable {

    /**
     * 是否成功
     *
     * @return
     */
    boolean isSuccess();

    int getErrorCode();

    void setErrorCode(int errorCode);

    String getErrorMsg();

    void setErrorMsg(String errorMsg);

    /**
     * 属性
     *
     * @param name  属性名
     * @param value 属性值
     */
    void setAttribute(String name, Object value);

    /**
     * 属性值
     *
     * @param name 属性名
     * @return
     */
    Object getAttribute(String name);

    /**
     * 流程唯一标识
     *
     * @return
     */
    String getPipelineId();

    /**
     * 属性
     *
     * @return
     */
    Map<String, Object> getAttributes();

    Object getRequestBody();

    void setRequestBody(Object requestBody);

    Object getBody();

    void setBody(Object body);

    Throwable getThrowable();

    void setThrowable(Throwable throwable);

}
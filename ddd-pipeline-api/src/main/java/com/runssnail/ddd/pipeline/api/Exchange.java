package com.runssnail.ddd.pipeline.api;

import java.io.Serializable;
import java.util.Map;

/**
 * 执行上下文参数
 * 注意：此对象为大对象
 *
 * @author zhengwei
 */
public interface Exchange<T> extends Serializable {

    /**
     * 是否成功
     *
     * @return
     */
    boolean isSuccess();

    /**
     * 错误码
     *
     * @return
     */
    int getErrorCode();

    /**
     * 错误码
     *
     * @param errorCode 错误码
     */
    void setErrorCode(int errorCode);

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String getErrorMsg();

    /**
     * 错误信息
     *
     * @param errorMsg 错误信息
     */
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

    /**
     * 原始请求数据
     *
     * @return 原始请求数据
     */
    Map<String, Object> getRequest();

    /**
     * 从body or request or attribute 中获取name对应的值
     * 优先级 body > request > attribute
     *
     * @param name 属性名
     * @return 属性值
     */
    Object getValue(String name);

    /**
     * 数据
     *
     * @return 数据
     */
    T getBody();

    /**
     * 数据
     *
     * @param body 数据
     */
    void setBody(T body);

    /**
     * 异常
     *
     * @return 异常
     */
    Throwable getThrowable();

    /**
     * 异常
     *
     * @param throwable 异常
     */
    void setThrowable(Throwable throwable);

}
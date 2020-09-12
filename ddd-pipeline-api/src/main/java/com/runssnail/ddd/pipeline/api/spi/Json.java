package com.runssnail.ddd.pipeline.api.spi;

import java.util.Map;

/**
 * json
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface Json {

    /**
     * 对象转json字符串
     *
     * @param obj 对象
     * @return
     */
    String toJson(Object obj);

    /**
     * json字符串转对象
     *
     * @param json      json字符串
     * @param valueType 类型
     * @param <T>
     * @return
     */
    <T> T fromJson(String json, Class<T> valueType);

    /**
     * json字符串转map对象
     *
     * @param json json字符串
     * @return
     */
    Map<String, Object> fromJson(String json);
}

package com.runssnail.ddd.pipeline.api.json;

import java.util.Map;

/**
 * json
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface Json {

    String toJson(Object obj);

    <T> T fromJson(String json, Class<T> valueType);

    Map<String, Object> fromJson(String json);
}

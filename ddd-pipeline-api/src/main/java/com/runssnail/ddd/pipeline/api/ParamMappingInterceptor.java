package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 参数映射拦截器
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public class ParamMappingInterceptor implements Interceptor {

    @Override
    public void beforeExecute(Exchange context) throws ExecuteException {
        // todo 入参映射
    }

    @Override
    public void afterExecute(Exchange context) throws ExecuteException {
        // todo 出参映射
    }
}

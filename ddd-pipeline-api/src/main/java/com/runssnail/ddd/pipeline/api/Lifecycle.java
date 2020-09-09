package com.runssnail.ddd.pipeline.api;

/**
 * 生命周期
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface Lifecycle {

    void init();

    void close();

}

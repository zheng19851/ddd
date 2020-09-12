package com.runssnail.ddd.pipeline.api;

import java.io.Serializable;

/**
 * 生命周期
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface Lifecycle extends Serializable {

    void init();

    void close();

}

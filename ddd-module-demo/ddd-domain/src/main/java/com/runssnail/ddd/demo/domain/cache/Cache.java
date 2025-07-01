package com.runssnail.ddd.demo.domain.cache;


/**
 * cache demo
 */
public interface Cache {

    String getString(String key);

    int getInt(String key);
}

package com.runssnail.ddd.common.domain;

/**
 * 支持并发控制的实体
 *
 * @author zhengwei
 * @date 2019-12-20 17:21
 **/
public abstract class ConcurrencySafeEntity extends UUIDEntity {

    /**
     * 并发版本
     */
    private int concurrencyVersion;

    public int getConcurrencyVersion() {
        return concurrencyVersion;
    }

    public void setConcurrencyVersion(int concurrencyVersion) {
        this.concurrencyVersion = concurrencyVersion;
    }
}

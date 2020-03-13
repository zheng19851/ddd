package com.runssnail.ddd.common.domain;

/**
 *
 * uuid entity
 *
 * @author zhengwei
 * @date 2019-12-23 17:35
 **/
public abstract class UUIDEntity extends IdentityEntity {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}


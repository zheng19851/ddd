package com.runssnail.ddd.common.domain;

/**
 *
 * id entity
 *
 * @author zhengwei
 * @date 2019-12-23 17:35
 **/
public abstract class IdentityEntity extends Entity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


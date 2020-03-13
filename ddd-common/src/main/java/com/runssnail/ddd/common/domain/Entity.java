package com.runssnail.ddd.common.domain;

import com.runssnail.ddd.common.exception.BizException;

import java.io.Serializable;

/**
 * 实体
 *
 * @author zhengwei
 * @date 2019-11-04 16:16
 **/
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = -6220077183080333321L;

    /**
     * 验证实体对象
     *
     * @throws BizException
     */
    public void validate() throws BizException {

    }
}

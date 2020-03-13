package com.runssnail.ddd.demo.client.dataobject;

import com.runssnail.ddd.common.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 15:16
 **/
@Data
public class PolicySetDO extends BaseDTO {
    private String id;

    private String partnerCode;

    private String name;

    private String eventType;

    private String eventId;

    private String version;
}

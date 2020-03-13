package com.runssnail.ddd.converter;

/**
 * 实现实体对象和数据对象之间的转换
 *
 * @author zhengwei
 * @date 2019-11-05 16:36
 **/
public interface Converter<DomainEntity, DataObject> {

    /**
     * 领域对象转换成数据对象
     *
     * @param domainObject 领域对象
     * @return 数据对象
     */
    DataObject serialize(DomainEntity domainObject);

    /**
     * 数据对象转换成领域对象
     *
     * @param dataObject 数据对象
     * @return 领域对象
     */
    DomainEntity deserialize(DataObject dataObject);

}

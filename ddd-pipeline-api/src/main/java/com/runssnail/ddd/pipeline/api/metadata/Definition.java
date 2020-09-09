package com.runssnail.ddd.pipeline.api.metadata;

import java.util.Map;

/**
 * 元数据定义
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public interface Definition {

    /**
     * 最近更新时间
     *
     * @return 最近更新时间
     */
    long getUpdateTime();

    /**
     * 属性
     *
     * @return 属性
     */
    Map<String, String> getAttributes();

    /**
     * 是否已删除
     *
     * @return 是否已删除
     */
    boolean isRemoved();

    /**
     * 设置扩展属性
     *
     * @param name  属性名称
     * @param value 属性值
     */
    void putAttribute(String name, String value);

    /**
     * 获取扩展属性
     *
     * @param name 属性名称
     * @return 属性值
     */
    String getAttribute(String name);

    /**
     * 获取扩展属性
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return 属性值
     */
    String getAttribute(String name, String defaultValue);

    /**
     * 获取boolean属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    boolean getAttrBooleanValue(String name);

    /**
     * 获取长整型属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    Long getAttrLongValue(String name);

    /**
     * 获取长整型属性值
     *
     * @param name         属性名
     * @param defaultValue 默认值
     * @return
     */
    Long getAttrLongValue(String name, long defaultValue);

    /**
     * 获取int类型属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    int getAttrIntValue(String name);

}

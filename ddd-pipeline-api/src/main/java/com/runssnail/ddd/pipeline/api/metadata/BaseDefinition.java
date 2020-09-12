package com.runssnail.ddd.pipeline.api.metadata;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * 元数据定义
 *
 * @author zhengwei
 * Created on 2020-09-09
 */
public abstract class BaseDefinition implements Definition {

    /**
     * 更新时间
     */
    protected long updateTime;

    /**
     * 扩展属性
     */
    protected Map<String, String> attributes = new HashMap<>();

    /**
     * 是否被删除
     */
    protected boolean removed;

    @Override
    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    /**
     * 设置扩展属性
     *
     * @param name  属性名称
     * @param value 属性值
     */
    @Override
    public void putAttribute(String name, String value) {
        Validate.notBlank(name);
        Validate.notBlank(value);
        attributes.put(name, value);
    }

    /**
     * 获取扩展属性
     *
     * @param name 属性名称
     * @return 属性值
     */
    @Override
    public String getAttribute(String name) {
        return this.getAttribute(name, null);
    }

    /**
     * 获取扩展属性
     *
     * @param name 属性名称
     * @return 属性值
     */
    @Override
    public String getAttribute(String name, String defaultValue) {
        if (this.attributes != null && this.attributes.containsKey(name)) {
            return this.attributes.get(name);
        }
        return defaultValue;
    }

    /**
     * 获取boolean属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    @Override
    public boolean getAttrBooleanValue(String name) {
        String value = this.getAttribute(name);
        // value=1 or value=true
        return StringUtils.isBlank(value) ? false : ("1".equals(value) || BooleanUtils.toBoolean(value));
    }

    /**
     * 获取长整型属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    @Override
    public Long getAttrLongValue(String name) {
        String attrValue = this.getAttribute(name);
        return StringUtils.isBlank(attrValue) ? null : Long.parseLong(attrValue);
    }

    @Override
    public int getAttrIntValue(String name) {
        String attrValue = this.getAttribute(name);
        return StringUtils.isBlank(attrValue) ? -1 : Integer.parseInt(attrValue);
    }

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public Long getAttrLongValue(String name, long defaultValue) {
        if (this.attributes.containsKey(name)) {
            return Long.parseLong(this.attributes.get(name));
        }
        return defaultValue;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}

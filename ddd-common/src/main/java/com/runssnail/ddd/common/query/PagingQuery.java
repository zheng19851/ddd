package com.runssnail.ddd.common.query;


import java.util.List;

import com.runssnail.ddd.common.result.Result;
import org.apache.commons.lang3.Validate;

/**
 * 分页查询
 *
 * @author zhengwei
 * @date 2019/3/12 3:48 PM
 **/
public abstract class PagingQuery<T extends Result> extends Query<T> {
    private static final long serialVersionUID = -7364444103182324366L;

    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_MAX_PAGE_NUM = 10000;

    private Integer pageNum = DEFAULT_PAGE_NUM;
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 排序
     */
    private List<OrderDefinition> orderDefinitions;

    public PagingQuery() {
    }

    /**
     * pageNum 或者 pageSize 为 null的情况下才会调整成默认的
     */
    public void adjustIfNecessary() {
        this.adjustIfNecessary(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE, DEFAULT_MAX_PAGE_NUM);
    }

    /**
     * pageNum 或者 pageSize 为 null的情况下才会调整成默认的
     */
    public void adjustIfNecessary(int pageSize) {
        this.adjustIfNecessary(DEFAULT_PAGE_NUM, pageSize, DEFAULT_MAX_PAGE_NUM);
    }

    /**
     * pageNum 或者 pageSize 为 null的情况下才会调整成默认的
     */
    public void adjustIfNecessary(int pageNum, int pageSize) {
        this.adjustIfNecessary(pageNum, pageSize, DEFAULT_MAX_PAGE_NUM);
    }

    /**
     * pageNum 或者 pageSize 为 null的情况下才会调整
     *
     * @param pageNum  页号
     * @param pageSize 每页显示的记录数
     */
    public void adjustIfNecessary(int pageNum, int pageSize, int maxPageNum) {
        Validate.isTrue(pageNum > 0 && pageNum <= maxPageNum);
        Validate.isTrue(pageSize > 0);
        if (this.pageNum == null) {
            this.pageNum = pageNum;
        }
        if (this.pageSize == null) {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderDefinition> getOrderDefinitions() {
        return orderDefinitions;
    }

    public void setOrderDefinitions(List<OrderDefinition> orderDefinitions) {
        this.orderDefinitions = orderDefinitions;
    }
}

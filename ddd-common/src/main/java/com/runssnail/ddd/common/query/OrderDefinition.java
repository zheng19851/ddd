package com.runssnail.ddd.common.query;

import com.runssnail.ddd.common.dto.BaseDTO;

/**
 * 排序定义
 *
 * @author zhengwei
 * @date 2019-12-17 11:46
 **/
public class OrderDefinition extends BaseDTO {

    /**
     * 列
     */
    private String column;

    /**
     * 顺序
     */
    private boolean asc = true;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }
}

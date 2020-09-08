package com.runssnail.ddd.demo.domain.exception;

import com.runssnail.ddd.common.exception.ErrorCode;

/**
 * @author zhengwei
 * Created on 2020-05-31
 */
public enum ProductErrorCode implements ErrorCode {

    PRODUCT_NOT_EXISTS(400001, "商品不存在");;

    private int code;
    private String msg;

    ProductErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}

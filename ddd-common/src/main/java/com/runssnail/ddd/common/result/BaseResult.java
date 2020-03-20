package com.runssnail.ddd.common.result;


import com.runssnail.ddd.common.dto.BaseDTO;
import com.runssnail.ddd.common.exception.BasicErrorCode;

/**
 * 结果
 *
 * @author zhengwei
 */
public class BaseResult extends BaseDTO {

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "success.message";

    public static final int SERVER_ERROR_CODE = BasicErrorCode.SYS_ERROR.getErrorCode();

    private int code = SERVER_ERROR_CODE;
    private String subCode;
    private String message;

    public BaseResult() {
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(int code, String subCode, String message) {
        this.code = code;
        this.subCode = subCode;
        this.message = message;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE == this.code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

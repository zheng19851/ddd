package com.runssnail.ddd.common.result;


import com.runssnail.ddd.common.dto.BaseDTO;
import com.runssnail.ddd.common.exception.BasicErrorCode;

/**
 * 结果
 *
 * @author zhengwei
 */
public class Result extends BaseDTO {

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "success.message";

    public static final int PARAM_ERROR_CODE = BasicErrorCode.PARAMS_ERROR.getErrorCode();
    public static final Result PARAM_ERROR = new Result(PARAM_ERROR_CODE, BasicErrorCode.PARAMS_ERROR.getErrorMsg());


    public static final int SERVER_ERROR_CODE = BasicErrorCode.SYS_ERROR.getErrorCode();
    public static final Result SERVER_ERROR = new Result(SERVER_ERROR_CODE, BasicErrorCode.SYS_ERROR.getErrorMsg());

    public static final Result SUCCESS = new Result(SUCCESS_CODE, SUCCESS_MSG);

    private int code = SERVER_ERROR_CODE;
    private String subCode;
    private String message;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String subCode, String message) {
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

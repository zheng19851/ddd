package com.runssnail.ddd.common.result;


import com.runssnail.ddd.common.exception.ErrorCode;

/**
 * 结果
 *
 * @author zhengwei
 */
public class Result<T> extends BaseResult {
    private static final long serialVersionUID = 7796096612205501634L;

    private T data;

    public Result() {
    }

    public Result(int code, String message) {
        super(code, message);
    }

    public Result(int code, String subCode, String message) {
        super(code, subCode, message);
    }

    public Result(int code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public Result(int code, String subCode, String msg, T data) {
        this(code, subCode, msg);
        this.data = data;
    }

    public static <T> Result<T> failure(ErrorCode errorCode) {
        return new Result<>(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    public static <T> Result<T> failure(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> failure(int code, String subCode, String msg) {
        return new Result<>(SERVER_ERROR_CODE, subCode, msg);
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, SUCCESS_MSG, SUCCESS_MSG);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MSG, SUCCESS_MSG, data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

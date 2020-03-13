package com.runssnail.ddd.common.result;


import com.runssnail.ddd.common.exception.ErrorCode;

/**
 * 单个结果
 *
 * @author zhengwei
 */
public class SingleResult<T> extends Result {
    private static final long serialVersionUID = 3583339207719763537L;

    private T data;

    public SingleResult() {
    }

    public SingleResult(T data) {
        this(SUCCESS_CODE, SUCCESS_MSG, SUCCESS_MSG, data);
    }

    public SingleResult(int code, String msg) {
        super(code, msg);
    }

    public SingleResult(int code, String subCode, String msg) {
        super(code, subCode, msg);
    }

    public SingleResult(int code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public SingleResult(int code, String subCode, String msg, T data) {
        this(code, subCode, msg);
        this.data = data;
    }

    public static <T> SingleResult<T> failure(ErrorCode errorCode) {
        return new SingleResult<>(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    public static <T> SingleResult<T> failure(int code, String msg) {
        return new SingleResult<>(code, msg);
    }

    public static <T> SingleResult<T> failure(int code, String subCode, String msg) {
        return new SingleResult<>(SERVER_ERROR_CODE, subCode, msg);
    }

    public static <T> SingleResult<T> success() {
        return new SingleResult<>(SUCCESS_CODE, SUCCESS_MSG, SUCCESS_MSG);
    }

    public static <T> SingleResult<T> success(T data) {
        return new SingleResult<>(SUCCESS_CODE, SUCCESS_MSG, SUCCESS_MSG, data);
    }

    public static <T> SingleResult<T> create() {
        return new SingleResult<>(SUCCESS_CODE, SUCCESS_MSG, SUCCESS_MSG);
    }

    public static <T> SingleResult<T> create(T data) {
        return new SingleResult<>(SUCCESS_CODE, SUCCESS_MSG, SUCCESS_MSG, data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

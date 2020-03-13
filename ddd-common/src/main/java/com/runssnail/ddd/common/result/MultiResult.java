package com.runssnail.ddd.common.result;


import com.runssnail.ddd.common.exception.ErrorCode;

import java.util.List;

/**
 * 查询返回多个结果对象
 *
 * @author zhengwei
 */
public class MultiResult<T> extends Result {

    private static final long serialVersionUID = 7761262662429121287L;

    private List<T> data;

    public MultiResult() {
    }

    public MultiResult(int code, String msg) {
        super(code, msg);
    }

    public MultiResult(int code, String msg, List<T> data) {
        super(code, msg);
        this.data = data;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  信息
     * @param <T>
     * @return
     */
    public static <T> MultiResult<T> failure(int code, String msg) {
        return new MultiResult<>(code, msg);
    }

    public static <T> MultiResult<T> failure(ErrorCode errorCode) {
        return new MultiResult<>(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    public static <T> MultiResult<T> success() {
        return new MultiResult<>(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static <T> MultiResult<T> success(List<T> data) {
        return new MultiResult<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> MultiResult<T> create() {
        return new MultiResult<>(SUCCESS_CODE, SUCCESS_MSG);
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> MultiResult<T> create(List<T> data) {
        return new MultiResult<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

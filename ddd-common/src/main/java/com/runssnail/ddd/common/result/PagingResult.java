package com.runssnail.ddd.common.result;


import java.util.List;

import com.runssnail.ddd.common.exception.ErrorCode;

/**
 * 分页查询返回结果对象
 *
 * @author zhengwei
 */
public class PagingResult<T> extends MultiResult<T> {

    private static final long serialVersionUID = 7761262662429121287L;

    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 总的记录数
     */
    private Integer total = 0;

    /**
     * 多少页
     */
    private Integer pages = 0;

    public PagingResult() {
    }

    public PagingResult(int code, String msg) {
        super(code, msg);
    }

    public PagingResult(int code, String msg, List<T> data) {
        super(code, msg, data);
    }

    public PagingResult(List<T> data, Integer total, Integer pages) {
        this(data, total, pages, null);
    }

    public PagingResult(List<T> data, Integer total, Integer pages, Integer pageNum) {
        super(SUCCESS_CODE, SUCCESS_MSG, data);
        this.total = total;
        this.pages = pages;
        this.pageNum = pageNum;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  信息
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> failure(int code, String msg) {
        return new PagingResult<>(code, msg);
    }

    public static <T> PagingResult<T> failure(ErrorCode errorCode) {
        return new PagingResult<>(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    public static <T> PagingResult<T> success() {
        return new PagingResult<>(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static <T> PagingResult<T> success(List<T> data) {
        return new PagingResult<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    /**
     * 成功
     *
     * @param data  数据
     * @param total 总数
     * @param pages 总的页数
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> success(List<T> data, Integer total, Integer pages) {
        return new PagingResult<>(data, total, pages);
    }

    /**
     * 成功
     *
     * @param data  数据
     * @param total 总数
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> success(List<T> data, Integer total) {
        return new PagingResult<>(data, total, 0);
    }

    /**
     * 成功
     *
     * @param data    数据
     * @param total   总的记录数
     * @param pages   总的页数
     * @param pageNum 第几页
     * @param <T>
     * @return 结果
     */
    public static <T> PagingResult<T> success(List<T> data, Integer total, Integer pages, Integer pageNum) {
        return new PagingResult<>(data, total, pages, pageNum);
    }

    public static <T> PagingResult<T> create() {
        return new PagingResult<>(SUCCESS_CODE, SUCCESS_MSG);
    }


    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> create(List<T> data) {
        return new PagingResult<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    /**
     * 成功
     *
     * @param data
     * @param total
     * @param pages
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> create(List<T> data, Integer total, Integer pages) {
        return new PagingResult<>(data, total, pages);
    }

    public static <T> PagingResult<T> create(List<T> data, Integer total, Integer pages, Integer pageNum) {
        return new PagingResult<>(data, total, pages, pageNum);
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 不要删除，这个是给前端用的
     *
     * @return
     */
    public Integer getCurrPage() {
        return this.pageNum;
    }
}

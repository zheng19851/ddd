package com.runssnail.ddd.common.util;

/**
 * 分页工具类
 *
 * @author zhengwei
 * @date 2019/7/22 3:17 PM
 **/
public abstract class PagingUtils {

    /**
     * 计算偏移量
     *
     * @param pageNum  页号
     * @param pageSize 每页记录数
     * @return 偏移量
     */
    public static int getOffset(int pageNum, int pageSize) {
        return pageNum > 0 ? (pageSize * (pageNum - 1)) : 0;
    }

    /**
     * 计算页数
     *
     * @param total    总的记录数
     * @param pageSize 每页记录数
     * @return 总的页数
     */
    public static int getPages(int total, int pageSize) {
        int pages = total % pageSize == 0 ? (total / pageSize) : ((total / pageSize) + 1);
        return pages;
    }

    /**
     * 计算页数
     *
     * @param total    总的记录数
     * @param pageSize 每页记录数
     * @return 总的页数
     */
    public static int getPages(long total, int pageSize) {
        int pages = total % pageSize == 0 ? (int) (total / pageSize) : (int) ((total / pageSize) + 1);
        return pages;
    }

}

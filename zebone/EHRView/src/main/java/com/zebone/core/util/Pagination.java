package com.zebone.core.util;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页对象
 * @author 杨英
 * @version 2013-8-16 上午08:45
 */
public class Pagination<T> implements Serializable {

    private int curPage = 1;// 当前页

    private int totalCount;// 总记录数

    private int countEveryPage = 20;// 每页记录数

    private List<T> data = Collections.emptyList();//结果列表

    private int offset;//查询时所跳过的记录数

    private int pageCount;//总页数

    public Pagination() {
        setCurPage(curPage);
        setLimit(countEveryPage);

    }

    public Pagination(int curPage) {
        this.curPage = curPage;
        setCurPage(curPage);
        setLimit(countEveryPage);
    }

    public Pagination(int curPage, int countEveryPage) {
        setCurPage(curPage);
        setLimit(countEveryPage);
    }

    public Pagination(RowBounds rowBounds) {
        this.countEveryPage = rowBounds.getLimit();
        this.offset = rowBounds.getOffset();
        this.curPage = offset / countEveryPage + 1;
    }

    /**
     * 设置当前页
     *
     * @param curPage
     */
    public void setCurPage(int curPage) {
        if (curPage <= 0) {
            curPage = 1;
        }
        this.curPage = curPage;
        onInit();
    }

    public void setLimit(int countEveryPage) {
        if (countEveryPage < 1) {
            countEveryPage = 20;
        }
        this.countEveryPage = countEveryPage;
        onInit();
    }

    protected void onInit() {
        offset = (curPage - 1) * countEveryPage;
    }

    protected void onSetRowsize() {
        pageCount = (int) (this.totalCount / this.countEveryPage);
        if (totalCount % countEveryPage > 0) {
            pageCount++;
        }
        if (curPage > pageCount) {
            curPage = pageCount;
            onInit();
        }
    }

    protected void onSetList() {
        if (data == null || data.isEmpty()) {
            totalCount = 0;
            curPage = 1;
            offset = 0;
        }
    }

    /**
     * 返回总的记录数
     *
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总的记录数
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        onSetRowsize();
    }

    /**
     * 返回总页数
     *
     * @return
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 返回数据列表
     *
     * @return
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 设置分页数据
     *
     * @param data
     */
    public void setData(List<T> data) {
        this.data = data;
        onSetList();
    }

    public int getOffset() {
        return offset;
    }

    /**
     * 得到没有多少条记录
     *
     * @return
     */
    public int getCountEveryPage() {
        return countEveryPage;
    }

    /**
     * 设置每页记录数
     *
     * @param countEveryPage
     */
    public void setCountEveryPage(int countEveryPage) {
        setLimit(countEveryPage);
    }

    /**
     * 得到当前页
     *
     * @return
     */
    public int getCurPage() {
        return curPage;
    }

    /**
     * 返回分页范围对象。
     *
     * @return
     */
    public RowBounds getRowBounds() {
        return new RowBounds(getOffset(), this.getCountEveryPage());
    }

}

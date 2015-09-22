/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-6-21      分页对象
 */
package com.zebone.btp.core.mybatis.page;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

/**
 * 分页对象
 * 
 * @author lilin
 * @version [版本号, 2013-6-21]
 */
public class PageInfo<T>
{
    /**
     * 当前页码
     */
    private int pageNumber = 1;
    
    /**
     * 每页显示行数
     */
    private int pageSize = 10;
    
    /**
     * 总记录数
     */
    private int total = 0;
    
    /**
     * 记录集合
     */
    private List<T> rows;
    
    /**
     * 默认构造函数
     */
    public PageInfo()
    {
        
    }
    
    /**
     * 构造函数
     * 
     * @param pageNumber 当前页码
     * @param pageSize 每页显示行数
     */
    public PageInfo(Integer pageNumber, Integer pageSize)
    {
        if (pageNumber != null)
        {
            this.pageNumber = pageNumber.intValue();
        }
        if (pageSize != null)
        {
            this.pageSize = pageSize.intValue();
        }
    }
    
    /**
     * 返回分页范围对象。
     * 
     * @return RowBounds
     */
    public RowBounds getRowBounds()
    {
        return new RowBounds(getFirstResult(), getPageSize());
    }
    
    /**
     * 获取分页首记录偏移
     * 
     * @return 分页首记录偏移
     */
    public int getFirstResult()
    {
        return (getPageNumber() - 1) * getPageSize();
    }
    
    /**
     * 获取分页尾记录偏移
     * 
     * @return 分页尾记录偏移
     */
    public int getLastResult()
    {
        return getFirstResult() + getPageSize();
    }
    
    public int getPageNumber()
    {
        return pageNumber;
    }
    
    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public int getTotal()
    {
        return total;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
    
    public List<T> getRows()
    {
        return rows;
    }
    
    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }
}

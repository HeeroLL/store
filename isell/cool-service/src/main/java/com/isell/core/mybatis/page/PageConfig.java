/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2014-1-13      页面配置信息类
 */
package com.isell.core.mybatis.page;

import org.apache.ibatis.session.RowBounds;

/**
 * 页面配置信息类
 * 
 * @author lilin
 * @version [版本号, 2014-1-13]
 */
public class PageConfig
{
    /**
     * 当前页
     */
    private Integer page = 1;
    
    /**
     * 每页显示行数
     */
    private Integer rows = 10;
    
    /**
     * 排序字段名
     */
    private String sort;
    
    /**
     * 排序方式
     */
    private String order;
    
    /**
     * 返回分页范围对象。
     * 
     * @return RowBounds
     */
    public RowBounds getRowBounds()
    {
        return new RowBounds(getFirstResult(), getRows());
    }
    
    /**
     * 获取分页首记录偏移
     * 
     * @return 分页首记录偏移
     */
    public int getFirstResult()
    {
        return (getPage() - 1) * getRows();
    }
    
    
    public Integer getPage()
    {
        return page;
    }
    
    public void setPage(Integer page)
    {
        this.page = page;
    }
    
    public Integer getRows()
    {
        return rows;
    }
    
    public void setRows(Integer rows)
    {
        this.rows = rows;
    }
    
    public String getSort()
    {
        return sort;
    }
    
    public void setSort(String sort)
    {
        this.sort = sort;
    }
    
    public String getOrder()
    {
        return order;
    }
    
    public void setOrder(String order)
    {
        this.order = order;
    }
}

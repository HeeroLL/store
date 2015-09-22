/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2014-1-13      页面配置信息类
 */
package com.zebone.btp.core.mybatis.page;

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
    private Integer page;
    
    /**
     * 每页显示行数
     */
    private Integer rows;
    
    /**
     * 排序字段名
     */
    private String sort;
    
    /**
     * 排序方式
     */
    private String order;
    
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

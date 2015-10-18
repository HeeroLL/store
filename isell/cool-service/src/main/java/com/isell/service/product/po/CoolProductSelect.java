package com.isell.service.product.po;

import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 商品查询条件
 * 
 * @author lilin
 * @version [版本号, 2015年10月17日]
 */
public class CoolProductSelect extends PageConfig{
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品名称En
     */
    private String nameEn;
    /**
     * 是否下架
     */
    private Boolean shelves;
    
    /**
     * 是否查询规格
     */
    private boolean searchDetail;
    
    /**
     * 起始的修改时间
     */
    private Date startUpdatetime;
    
    /**
     * 结束的修改时间
     */
    private Date endUpdatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Boolean getShelves() {
        return shelves;
    }

    public void setShelves(Boolean shelves) {
        this.shelves = shelves;
    }

    public boolean isSearchDetail() {
        return searchDetail;
    }

    public void setSearchDetail(boolean searchDetail) {
        this.searchDetail = searchDetail;
    }

    public Date getStartUpdatetime() {
        return startUpdatetime;
    }

    public void setStartUpdatetime(Date startUpdatetime) {
        this.startUpdatetime = startUpdatetime;
    }

    public Date getEndUpdatetime() {
        return endUpdatetime;
    }

    public void setEndUpdatetime(Date endUpdatetime) {
        this.endUpdatetime = endUpdatetime;
    }
    
     
}

package com.isell.service.product.vo;

import java.math.BigDecimal;

/**
 * 商品组
 * 
 * @author lilin
 * @version [版本号, 2015年12月25日]
 */
public class CoolProductGroup {
    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 商品id
     */
    private Integer pId;
    
    /**
     * 规格id
     */
    private Integer gId;
    
    /**
     * 组id
     */
    private Integer groupId;
    
    /**
     * 数量
     */
    private Integer count;
    
    /**
     * 单价
     */
    private BigDecimal price;
    
    /**
     * 主键id
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 商品id
     */
    public Integer getpId() {
        return this.pId;
    }
    
    /**
     * 商品id
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }
    
    /**
     * 规格id
     */
    public Integer getgId() {
        return this.gId;
    }
    
    /**
     * 规格id
     */
    public void setgId(Integer gId) {
        this.gId = gId;
    }
    
    /**
     * 组id
     */
    public Integer getGroupId() {
        return this.groupId;
    }
    
    /**
     * 组id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    /**
     * 数量
     */
    public Integer getCount() {
        return this.count;
    }
    
    /**
     * 数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }
    
    /**
     * 单价
     */
    public BigDecimal getPrice() {
        return this.price;
    }
    
    /**
     * 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
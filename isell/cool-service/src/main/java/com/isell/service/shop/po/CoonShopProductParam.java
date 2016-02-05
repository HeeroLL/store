package com.isell.service.shop.po;

import java.math.BigDecimal;
import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;

public class CoonShopProductParam extends PageConfig{
	/**
     * 
     */
    private String id;
    /**
     * 商品id
     */
    private String pId;
    /**
     * 规格id
     */
    private String gId;
    /**
     * 0：仓库中（未上架） 1：出售中（已上架）
     */
    private Boolean added;
    /**
     * 酷店id
     */
    private String sId;
    /**
     * 
     */
    private String qrCode;
    /**
     * 
     */
    private BigDecimal brokerage;    
    
    /**
     * 是否新品
     */
    private String isNew;
    
    /**
     * 商品分类
     */
    private Integer type;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品是否下架 0:未下架 1：已下架
     */
    private Boolean shelves;
    
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 显示条数
     */
    private Integer showCount;
    
    /**
     * 该字符串由两位组成 第一位：1:佣金  2：售价 3:销量 默认按佣金；第二位：1：升序  2：降序 默认降序
     */
    private String orderBy;
    
    /**
     * 是否一件代发商品列表 0：否  1：是
     */
    private String dist;
    
    /**
     * 是否显示有货 1：显示有货
     */
    private String stock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) { 
		this.pId = pId;
	}

	public String getgId() {
		return gId;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}

	public Boolean getAdded() {
		return added;
	}

	public void setAdded(Boolean added) {
		this.added = added;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public BigDecimal getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(BigDecimal brokerage) {
		this.brokerage = brokerage;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getShowCount() {
		return showCount;
	}

	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getShelves() {
		return shelves;
	}

	public void setShelves(Boolean shelves) {
		this.shelves = shelves;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

}

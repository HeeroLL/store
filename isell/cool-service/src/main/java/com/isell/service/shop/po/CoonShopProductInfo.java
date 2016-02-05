package com.isell.service.shop.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.isell.service.product.vo.CoolProductImg;

/**
 * 酷店商品返回类
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-26]
 */
public class CoonShopProductInfo {
	
	/**
     * 规格id
     */
    private Integer id;
    
    /**
     * 商品id
     */
    private Integer goodsId;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品英文名
     */
    private String nameEn;
    
    /**
     * 商品类型
     */
    private Integer type;
    
    /**
     * 商品图片路径
     */
    private String logo;
    
    /**
     * 商品详情内容
     */
    private String content;
    
    /**
     * 规格
     */
    private String gg;
    
    /**
     * 价格
     */
    private BigDecimal jg;
    
    /**
     * 促销价格
     */
    private BigDecimal cxjg;
    
    /**
     * 销售价格
     */
    private BigDecimal xsjg;
    
    /**
     * 商品价格（最终定的）
     */
    private BigDecimal price;
    
    /**
     * 供货价
     */
    private BigDecimal drpPrice;
    
    /**
     * 佣金比例
     */
    private BigDecimal divide;
    
    /**
     * 酷店佣金比例
     */
    private BigDecimal cRate;
    
    /**
     * 销售数量
     */
    private Integer sales;
    
    /**
     * 月销售数量
     */
    private Integer salesMonth;
    
    /**
     * 评价数
     */
    private Integer reviews;
    
    /**
     * 上架店铺数量
     */
    private Integer putSalesNum;
    
    /**
     * 商品图片
     */
    private List<CoolProductImg> imgList;
    
    /**
     * 是否在该酷店上架 0：仓库中（未上架） 1：出售中（已上架）
     */
    private Boolean added;
    
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 排序号
     */
    private Integer order;
    
    /**
     * 是否新品 0：否 1：是
     */
    private String isNew;
    
    private Integer stock;
    
    /**
     * 商品是否下架 0:未下架 1：已下架
     */
    private Boolean shelves;
    
    

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGg() {
		return gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public BigDecimal getJg() {
		return jg;
	}

	public void setJg(BigDecimal jg) {
		this.jg = jg;
	}

	public BigDecimal getCxjg() {
		return cxjg;
	}

	public void setCxjg(BigDecimal cxjg) {
		this.cxjg = cxjg;
	}

	public BigDecimal getXsjg() {
		return xsjg;
	}

	public void setXsjg(BigDecimal xsjg) {
		this.xsjg = xsjg;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDrpPrice() {
		return drpPrice;
	}

	public void setDrpPrice(BigDecimal drpPrice) {
		this.drpPrice = drpPrice;
	}

	public BigDecimal getDivide() {
		return divide;
	}

	public void setDivide(BigDecimal divide) {
		this.divide = divide;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getSalesMonth() {
		return salesMonth;
	}

	public void setSalesMonth(Integer salesMonth) {
		this.salesMonth = salesMonth;
	}

	public Integer getReviews() {
		return reviews;
	}

	public void setReviews(Integer reviews) {
		this.reviews = reviews;
	}

	public Integer getPutSalesNum() {
		return putSalesNum;
	}

	public void setPutSalesNum(Integer putSalesNum) {
		this.putSalesNum = putSalesNum;
	}

	public List<CoolProductImg> getImgList() {
		return imgList;
	}

	public void setImgList(List<CoolProductImg> imgList) {
		this.imgList = imgList;
	}

	public Boolean getAdded() {
		return added;
	}

	public void setAdded(Boolean added) {
		this.added = added;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Boolean getShelves() {
		return shelves;
	}

	public void setShelves(Boolean shelves) {
		this.shelves = shelves;
	}

	public BigDecimal getcRate() {
		return cRate;
	}

	public void setcRate(BigDecimal cRate) {
		this.cRate = cRate;
	}

}

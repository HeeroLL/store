package com.isell.service.order.po;

import java.math.BigDecimal;

/**
 * 
 * 订单发货费舍尔相关信息
 * 
 * @author wangpeng
 *
 */
public class CoolOrderEcm {
	
	/**
     * 商品id
     */
    private Integer gId;
    
    /**
     * 商品名
     */
    private String name;
    
    /**
     * 
     */
    private String barCode;
    
    /**
     * 订购数量
     */
    private Integer count;
    
    /**
     * 订购单价
     */
    private BigDecimal price;
    
    /**
     * 
     */
    private String postTaxNo;
    
    /**
     * 
     */
    private String code;
    
    /**
     * 
     */
    private Double weight;

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPostTaxNo() {
		return postTaxNo;
	}

	public void setPostTaxNo(String postTaxNo) {
		this.postTaxNo = postTaxNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}

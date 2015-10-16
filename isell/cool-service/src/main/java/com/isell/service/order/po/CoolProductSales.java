package com.isell.service.order.po;

import java.math.BigDecimal;

/**
 * 统计商品销售量
 * 
 * @author wangpeng
 * 
 */
public class CoolProductSales {
	
	/**
	 * 无意义
	 */
	private Integer id;
	
	/**
	 * 商品名称
	 */
	private String pName;
	
	/**
	 * 商品规格id
	 */
	private String gid;
	
	/**
	 * 商品规格
	 */
	private String gg;
	
	/**
	 * 销量
	 */
	private Integer sales;
	
	/**
	 * 销售金额
	 */
	private BigDecimal total;
	
	/**
	 * 评分
	 */
	private Double score;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGg() {
		return gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}

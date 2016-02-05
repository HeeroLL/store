package com.isell.app.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderReturn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1918202569131420788L;
	private String orderno;
	private String goodname;
	private String remark;
	private BigDecimal total;
	
	
	
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	 
	
	
	
}

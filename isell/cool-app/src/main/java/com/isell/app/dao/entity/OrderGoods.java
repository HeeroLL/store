package com.isell.app.dao.entity;

import java.math.BigDecimal;

public class OrderGoods {
	private String goodimg;
	private String goodname;
	private String gname;
	private BigDecimal price;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getGoodimg() {
		return goodimg;
	}
	public void setGoodimg(String goodimg) {
		this.goodimg = goodimg;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
}

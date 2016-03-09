package com.isell.app.dao.entity;

import java.math.BigDecimal;

public class ProductGg {
	private int id;
	private int pid;
	private String ggname;
	private BigDecimal price;
	private BigDecimal cxprice;
	private int stock;
	private String weight;
	private String unit;
	private BigDecimal drp_price;
	private BigDecimal vipA;
	private BigDecimal vipB;
	private BigDecimal vipC;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	 
	public String getGgname() {
		return ggname;
	}
	public void setGgname(String ggname) {
		this.ggname = ggname;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getCxprice() {
		return cxprice;
	}
	public void setCxprice(BigDecimal cxprice) {
		this.cxprice = cxprice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getDrp_price() {
		return drp_price;
	}
	public void setDrp_price(BigDecimal drp_price) {
		this.drp_price = drp_price;
	}
	public BigDecimal getVipA() {
		return vipA;
	}
	public void setVipA(BigDecimal vipA) {
		this.vipA = vipA;
	}
	public BigDecimal getVipB() {
		return vipB;
	}
	public void setVipB(BigDecimal vipB) {
		this.vipB = vipB;
	}
	public BigDecimal getVipC() {
		return vipC;
	}
	public void setVipC(BigDecimal vipC) {
		this.vipC = vipC;
	}
	
}

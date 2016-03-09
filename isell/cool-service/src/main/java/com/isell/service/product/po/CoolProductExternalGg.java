package com.isell.service.product.po;

import java.math.BigDecimal;
/**
 * 对外商品查询
 * 
 * @author maoweijie
 * @version [版本号, 2016-1-15]
 */
public class CoolProductExternalGg {
    private String gg;
    private BigDecimal jg;
    private BigDecimal cxjg;
    private Float stock;
    private double weight;
    private String unit;
    private String spec;
    private int gid;
    
    
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
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
	public Float getStock() {
		return stock;
	}
	public void setStock(Float stock) {
		this.stock = stock;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
    
}

package com.isell.app.dao.entity;

public class CenterOrderItem {
	
	private String goodimg;
	private String goodname;
	private String gname;//规格名称
	private String price;
	private int quality;
	private String tax;
	private int orderitemid;
	
	public String getGoodimg() {
		if(goodimg==null)
		{
			goodimg="";
		}
		return goodimg;
	}
	public void setGoodimg(String goodimg) {
		this.goodimg = goodimg;
	}
	public String getGoodname() {
		if(goodname==null)
		{
			goodname="";
		}
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getGname() {
		if(gname==null)
		{
			gname="";
		}
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getPrice() {
		if(price==null)
		{
			price="";
		}
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public String getTax() {
		if(tax==null)
		{
			tax="0";
		}
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public int getOrderitemid() {
		return orderitemid;
	}
	public void setOrderitemid(int orderitemid) {
		this.orderitemid = orderitemid;
	}
	
}

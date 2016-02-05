package com.isell.app.dao.entity;

import java.io.Serializable;

public class Product implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1716021475167232660L;
	private int goodsid;
	private String name;
	private String img;
	private String price;
	private int shopid;
	private int isrec;// 0 已关注 不存在 1 已关注 已存在 3 未关注 
	
	
	public int getIsrec() {
		return isrec;
	}
	public void setIsrec(int isrec) {
		this.isrec = isrec;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}

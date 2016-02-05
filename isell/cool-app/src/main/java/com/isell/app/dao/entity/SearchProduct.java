package com.isell.app.dao.entity;

import java.io.Serializable;
import java.util.List;

public class SearchProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3599602660681821279L;
	private int goodid;
	private String goodname;
	private String price;
	private String fav_price;
	private String tax;
	private String img;
	private String cname;
	private String code;
	private String shopname;
	private String shopimg;
	
	
	private List<SearchShop>shoplist;
	
	
	public String getShopname() {
		if(shopname==null)
		{
			shopname="";
		}
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getShopimg() {
		if(shopimg==null)
		{
			shopimg="";
		}
		return shopimg;
	}
	public void setShopimg(String shopimg) {
		this.shopimg = shopimg;
	}
	public List<SearchShop> getShoplist() {
		return shoplist;
	}
	public void setShoplist(List<SearchShop> shoplist) {
		this.shoplist = shoplist;
	}
	public int getGoodid() {
		return goodid;
	}
	public void setGoodid(int goodid) {
		this.goodid = goodid;
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
	public String getFav_price() {
		if(fav_price==null)
		{
			fav_price="";
		}
		return fav_price;
	}
	public void setFav_price(String fav_price) {
		this.fav_price = fav_price;
	}
	public String getTax() {
		if(tax==null)
		{
			tax="";
		}
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getImg() {
		if(img==null)
		{
			img="";
		}
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCname() {
		if(cname==null)
		{
			cname="";
		}
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCode() {
		if(code==null)
		{
			code="";
		}
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
}

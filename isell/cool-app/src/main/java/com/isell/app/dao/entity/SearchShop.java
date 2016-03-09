package com.isell.app.dao.entity;

import java.io.Serializable;
import java.util.List;

public class SearchShop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 103642317596202547L;
	private int goodid;
	private String code;
	private String shopname;
	private String shopimg;
	private String anninfo;
	private String address;
	private String lon;
	private String lat;
	private String shopid;
	private String shopuserid;
	private String image_domain;
	private String qr_code;
	private int level;
	private int favnum;
	private List<Notice>noticelist;
	private List<ProductImg>shopimglist;
	private String mobile;
	private String shopcode;
	
	
	public String getShopcode() {
		return shopcode;
	}
	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<Notice> getNoticelist() {
		return noticelist;
	}
	public void setNoticelist(List<Notice> noticelist) {
		this.noticelist = noticelist;
	}
	public List<ProductImg> getShopimglist() {
		return shopimglist;
	}
	public void setShopimglist(List<ProductImg> shopimglist) {
		this.shopimglist = shopimglist;
	}
	public String getQr_code() {
		return qr_code;
	}
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getFavnum() {
		return favnum;
	}
	public void setFavnum(int favnum) {
		this.favnum = favnum;
	}
	public String getImage_domain() {
		return image_domain;
	}
	public void setImage_domain(String image_domain) {
		this.image_domain = image_domain;
	}
	public String getShopuserid() {
		if(shopuserid==null)
		{
			shopuserid=null;
		}
		return shopuserid;
	}
	public void setShopuserid(String shopuserid) {
		this.shopuserid = shopuserid;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getAnninfo() {
		if(anninfo==null)
		{
			anninfo="";
		}
		return anninfo;
	}
	public void setAnninfo(String anninfo) {
		this.anninfo = anninfo;
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
	public int getGoodid() {
		return goodid;
	}
	public void setGoodid(int goodid) {
		this.goodid = goodid;
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
	
	
}

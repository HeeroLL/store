package com.isell.app.dao.entity;

public class CollectInfo {

	private String id;
	private String showimg;
	private String showname;
	private String price;
	private String tax;
	private String postfee;
	private String shopcode;
	private int recid;
	private int shelves;
	private int is_down;
	private int g_id;
	private String ggname;
	
	
	
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getGgname() {
		return ggname;
	}
	public void setGgname(String ggname) {
		this.ggname = ggname;
	}
	public int getIs_down() {
		return is_down;
	}
	public void setIs_down(int is_down) {
		this.is_down = is_down;
	}
	public int getShelves() {
		return shelves;
	}
	public void setShelves(int shelves) {
		this.shelves = shelves;
	}
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public String getShopcode() {
		if(shopcode==null)
		{
			shopcode="";
		}
		return shopcode;
	}
	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShowimg() {
		return showimg;
	}
	public void setShowimg(String showimg) {
		this.showimg = showimg;
	}
	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getPostfee() {
		return postfee;
	}
	public void setPostfee(String postfee) {
		this.postfee = postfee;
	}
	
	
}

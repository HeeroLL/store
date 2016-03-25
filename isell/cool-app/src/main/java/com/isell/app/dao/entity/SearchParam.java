package com.isell.app.dao.entity;

import com.isell.core.mybatis.page.PageConfig;

public class SearchParam    extends PageConfig{
	
	
	/**
	 * 
	 */
	private String keywords;
	private int start;
	private int limit;
	private int type;//1 销量 2 价格升序 3 价格降序
	private  String img_domain;
	private int goodid;
	private String uncode;
	private int typeid;
	private int mId;
	private String sid;
	
	private int cId;
	private String name;
	private int ordertype;
	private int sorts;
	private String fav_price;
	private String shopcode;
	private String priceMin;
	private String priceMax;
	private String brandId;
	private String begintime;
	private String endtime;
	
	
	public int getSorts() {
		return sorts;
	}
	public void setSorts(int sorts) {
		this.sorts = sorts;
	}
	public String getFav_price() {
		return fav_price;
	}
	public void setFav_price(String fav_price) {
		this.fav_price = fav_price;
	}
	public String getShopcode() {
		return shopcode;
	}
	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}
	public String getFacv_price() {
		return fav_price;
	}
	public void setFacv_price(String facv_price) {
		this.fav_price = facv_price;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
	}
	 
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getUncode() {
		return uncode;
	}
	public void setUncode(String uncode) {
		this.uncode = uncode;
	}
	public int getGoodid() {
		return goodid;
	}
	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}
	public String getImg_domain() {
		return img_domain;
	}
	public void setImg_domain(String img_domain) {
		this.img_domain = img_domain;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	 
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(String priceMin) {
		this.priceMin = priceMin;
	}
	public String getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(String priceMax) {
		this.priceMax = priceMax;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
	
}

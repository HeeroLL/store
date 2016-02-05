package com.isell.app.dao.entity;

import java.io.Serializable;

public class CenterOrderParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3836982035680631619L;
	
	private String keywords;
	private int state;//0 待支付 1 待发货 2 待收货 3 待评价 4 已完成 5 已退款 11已通知海关发货 99已取消 -1 全部
	private int start;
	private int limit;
	private int mId;
	private String imgdomain;
	
	
	
	
	public String getImgdomain() {
		return imgdomain;
	}
	public void setImgdomain(String imgdomain) {
		this.imgdomain = imgdomain;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	
	
	
	
	
}

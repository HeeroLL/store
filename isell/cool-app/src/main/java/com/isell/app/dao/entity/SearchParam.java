package com.isell.app.dao.entity;

import java.io.Serializable;

public class SearchParam implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8921673715168016277L;
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
	
	
}

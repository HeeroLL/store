package com.isell.app.dao.entity;

import java.io.Serializable;


public class HomePageImage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1600529893294061592L;
	private int id;
	private String img;
	private int sort;
	private String url;
	private int hpid;
	private String titlename;
	
	public String getTitlename() {
		return titlename;
	}
	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getHpid() {
		return hpid;
	}
	public void setHpid(int hpid) {
		this.hpid = hpid;
	}
	
	
	
}

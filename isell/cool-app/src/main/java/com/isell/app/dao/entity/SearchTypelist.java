package com.isell.app.dao.entity;

import java.io.Serializable;

public class SearchTypelist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6171591949359229735L;
	private int id;
	private String title;
	private String img;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}

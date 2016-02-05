package com.isell.app.dao.entity;

import java.io.Serializable;

public class Hotword implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2327797460451760163L;
	private int id;
	private String name;
	private int sort;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
}

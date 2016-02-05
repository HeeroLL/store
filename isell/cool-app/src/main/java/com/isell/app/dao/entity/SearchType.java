package com.isell.app.dao.entity;

import java.io.Serializable;
import java.util.List;

public class SearchType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 842935931215232308L;
	private int id;
	private String name;
	private int sortnum;
	private List<SearchTypelist>searchTypelist;
	
	
	public List<SearchTypelist> getSearchTypelist() {
		return searchTypelist;
	}
	public void setSearchTypelist(List<SearchTypelist> searchTypelist) {
		this.searchTypelist = searchTypelist;
	}
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
	public int getSortnum() {
		return sortnum;
	}
	public void setSortnum(int sortnum) {
		this.sortnum = sortnum;
	}
	
	
}

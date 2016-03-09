package com.isell.service.order.vo;

public class Catelog {
	private int id;
	private String name;
	private String parentid;
	 
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
	public String getParentid() {
		if(parentid==null)
		{
			parentid="";
		}
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	 
	 
	
}

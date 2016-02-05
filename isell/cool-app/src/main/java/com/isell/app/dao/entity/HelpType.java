package com.isell.app.dao.entity;

import java.util.List;

public class HelpType {
	private int id;
	private String name;
	private String icon;
	private List<HelpList>helplist;
	
	
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<HelpList> getHelplist() {
		return helplist;
	}
	public void setHelplist(List<HelpList> helplist) {
		this.helplist = helplist;
	}
	
	
}

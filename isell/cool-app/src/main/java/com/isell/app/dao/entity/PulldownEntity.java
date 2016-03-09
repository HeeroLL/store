package com.isell.app.dao.entity;

import java.io.Serializable;

public class PulldownEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2103860158388224030L;
	
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

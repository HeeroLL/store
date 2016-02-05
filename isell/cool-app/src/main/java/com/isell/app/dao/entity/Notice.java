package com.isell.app.dao.entity;

import java.io.Serializable;

public class Notice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1840865998761154281L;
	private int id;
	private String title;
	private String content;
	private String createtime;
	private String noticetype;
	
	
	public String getNoticetype() {
		return noticetype;
	}
	public void setNoticetype(String noticetype) {
		this.noticetype = noticetype;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}

package com.isell.app.dao.entity;

public class ProductRec {
	
	private int id;
	private String content;
	private String createtime;
	private String m_name;
	private String headimg;
	
	
	
	public String getHeadimg() {
		if(headimg==null)
		{
			headimg="";
		}
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getM_name() {
		if(m_name==null)
		{
			m_name="";
		}
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	
}

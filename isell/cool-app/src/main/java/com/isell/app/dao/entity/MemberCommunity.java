package com.isell.app.dao.entity;

public class MemberCommunity {
	private String headimg;
	private String mname;
	private String createtime;
	private String content;
	private String showimg;
	private int userid;
	private int mid;
	private int type;//1 买家 2 卖家
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
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
	public String getMname() {
		if(mname==null)
		{
			mname="";
		}
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getContent() {
		if(content==null)
		{
			content="";
		}
		return content;
	}
	public void setContent(String content) {
		 
		this.content = content;
	}
	public String getShowimg() {
		if(showimg==null)
		{
			showimg="";
		}
		return showimg;
	}
	public void setShowimg(String showimg) {
		this.showimg = showimg;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
}

package com.isell.app.dao.entity;

public class CollParam {
	private int mId;
	private int pId;
	private String shopcode;
	private int cid;
	private int type;
	private String[]  recids;
	private String ids;
	
	
	 
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String[] getRecids() {
		return recids;
	}
	public void setRecids(String[] recids) {
		this.recids = recids;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getShopcode() {
		return shopcode;
	}
	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}
}

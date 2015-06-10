package com.zebone.dnode.etl.adapter.vo;

/**
 * 主表【表名、主键、主键值】
 */

public class MainTableInfo {
	/**表名*/
	private String tname;
	/**主键*/
	private String tkey;
	/**主键值*/
	private String tvalue;
	/**文档编号*/
	private String docNo;
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTkey() {
		return tkey;
	}
	public void setTkey(String tkey) {
		this.tkey = tkey;
	}
	public String getTvalue() {
		return tvalue;
	}
	public void setTvalue(String tvalue) {
		this.tvalue = tvalue;
	}
}

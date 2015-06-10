package com.zebone.dnode.etl.adapter.vo;

/**
 * 数据字典对照信息
 * @author: caixl
 * @date： 日期：Dec 26, 2013
 * @version 1.0
 */

public class DictMapping {
	/**标识*/
	private String id;
	/**机构字典信息表ID*/
	private String orgDictId;
	/**标准字典信息表ID*/
	private String dictId;
	/**对照机构编码*/
	private String orgCode;
	/**对照人*/
	private String userCode;
	/**对照时间*/
	private String userTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgDictId() {
		return orgDictId;
	}
	public void setOrgDictId(String orgDictId) {
		this.orgDictId = orgDictId;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserTime() {
		return userTime;
	}
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
}

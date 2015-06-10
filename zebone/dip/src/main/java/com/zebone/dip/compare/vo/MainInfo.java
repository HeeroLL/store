package com.zebone.dip.compare.vo;

/**
 * 机构主数据基本信息
 */

public class MainInfo {
	/**表名*/
	private String tableName;
	/**机构主数据标识*/
	private String id;
	/**标准主数据标识*/
	private String mid;
	/**主数据名称*/
	private String name;
	/**主数据编码*/
	private String code;
	/**用户标识*/
	private String userCode;
	/**机构编码*/
	private String orgCode;
	/**创建时间*/
	private String createTime;
	/**标准主数据名称*/
	private String stdName;
	/**标准主数据编码*/
	private String stdCode;
	/**标准主数据表名*/
	private String stdTableName;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getStdTableName() {
		return stdTableName;
	}
	public void setStdTableName(String stdTableName) {
		this.stdTableName = stdTableName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStdCode() {
		return stdCode;
	}
	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}
}

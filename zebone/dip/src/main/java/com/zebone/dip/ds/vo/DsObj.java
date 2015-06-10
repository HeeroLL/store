package com.zebone.dip.ds.vo;
/**
 * 数据源对象
 */
public class DsObj {
	/**数据源标识id*/
	private String id;
	/**驱动类*/
	private String pDriver;
	/**数据源url*/
	private String pUrl;
	/**数据源用户名*/
	private String pUserName;
	/**数据源密码*/
	private String pPassword;
	/**删除标识*/
	private String delFlag;
	/**数据源名称*/
	private String pName;
	/**数据源备注*/
	private String pRemark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getpDriver() {
		return pDriver;
	}

	public void setpDriver(String pDriver) {
		this.pDriver = pDriver == null ? null : pDriver.trim();
	}

	public String getpUrl() {
		return pUrl;
	}

	public void setpUrl(String pUrl) {
		this.pUrl = pUrl == null ? null : pUrl.trim();
	}

	public String getpUserName() {
		return pUserName;
	}

	public void setpUserName(String pUserName) {
		this.pUserName = pUserName == null ? null : pUserName.trim();
	}

	public String getpPassword() {
		return pPassword;
	}

	public void setpPassword(String pPassword) {
		this.pPassword = pPassword == null ? null : pPassword.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpRemark() {
		return pRemark;
	}

	public void setpRemark(String remark) {
		this.pRemark = remark;
	}
}
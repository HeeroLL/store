package com.zebone.dnode.engine.clean.pojo;


/**
 * 数据源管理表
 * P_DS_OBJ
 * @author cz
 *
 */
public class DsObj {

	private String id;
	
	/** 数据库驱动类**/
	private String driver;
	
	/** 数据库URL地址 **/
	private String url;
	
    /** 用户名 **/
	private String userName;
    
	/** 密码 **/
	private String password;
	
	/** 数据源名称 **/
	private String name;
	
	/** 数据源备注  **/
	private String remark;
	
	/** 删除标识  **/
	private String delFlag;
	
	public DsObj() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String usrName) {
		this.userName = usrName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	

}

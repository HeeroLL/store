package com.zebone.dip.resources.vo;

public class MdMedicalOrg {
	
	/**
	 * 机构id
	 */
	private String id;
	
	/**
	 * 机构编码
	 */
	private String orgCode;
	
	/**
	 * 机构名称
	 */
	private String orgName;
	
	/**
	 * 机构父编码
	 */
	private String orgParentCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgParentCode() {
		return orgParentCode;
	}

	public void setOrgParentCode(String orgParentCode) {
		this.orgParentCode = orgParentCode;
	}

	
}

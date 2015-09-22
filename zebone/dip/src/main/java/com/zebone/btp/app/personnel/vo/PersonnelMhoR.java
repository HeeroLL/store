package com.zebone.btp.app.personnel.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 医疗机构与工作人员关系对象
 * @author LinBin
 * 2012-11-23
 */
public class PersonnelMhoR implements Serializable{
	/** 医疗机构ID */
	private String mhoId;
	/** 医疗机构工作人员ID */
	private String personnelId;
	/** 人员类型(字典) */
	private String personnelType;
	/** 责任医生所属科室(使用字典值) */
	private String department;
	/** 机构内人员工号 */
	private String deptPersonnelCode;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private String creatorId;
	/** 是否兼职 0 否 1 是 */
	private Integer isPartTime;
	
	/** 医疗机构名称 */
	private String mhoName;
	/** 职称(字典值) */
	private String jobTitle;
	/** 姓名 */
	private String fullname;
	/** 性别 */
	private String sex;
	/** 账户是否启用 0 停用 1 启用 默认为停用状态 */
	private Integer isAccountEnable;
	public String getMhoId() {
		return mhoId;
	}
	public void setMhoId(String mhoId) {
		this.mhoId = mhoId;
	}
	public String getPersonnelId() {
		return personnelId;
	}
	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}
	public String getPersonnelType() {
		return personnelType;
	}
	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDeptPersonnelCode() {
		return deptPersonnelCode;
	}
	public void setDeptPersonnelCode(String deptPersonnelCode) {
		this.deptPersonnelCode = deptPersonnelCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getIsPartTime() {
		return isPartTime;
	}
	public void setIsPartTime(Integer isPartTime) {
		this.isPartTime = isPartTime;
	}
	public String getMhoName() {
		return mhoName;
	}
	public void setMhoName(String mhoName) {
		this.mhoName = mhoName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getIsAccountEnable() {
		return isAccountEnable;
	}
	public void setIsAccountEnable(Integer isAccountEnable) {
		this.isAccountEnable = isAccountEnable;
	}


}
package com.zebone.btp.app.personnel.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.app.role.vo.RoleAccountR;
/**
 * 医疗人员基本信息对象
 * @author LinBin
 * 2012-11-23
 */
public class Personnel implements Serializable{
	/** 医疗机构工作人ID */
	private String personnelId;
	/** 医疗机构工作人编码 */
	private String personnelCode;
	/** 姓名 */
	private String fullname;
	/** 姓名全拼 */
	private String fullnamePinyin;
	/** 全名简拼 */
	private String fullnameJianpin;
	/** 用户别名 */
	private String alias;
	/** 性别 */
	private String sex;
	/** 民族 */
	private String nation;
	/** 籍贯 */
	private String nativePlace;
	/** 出生日期 */
	private Date birthday;
	/** 职称(字典值) */
	private String jobTitle;
	/** 学历(字典值) */
	private String education;
	/** 政治面貌(字典值) */
	private String politicalStatus;
	/** 地址 */
	private String address;
	/** 电子邮件 */
	private String email;
	/** 联系电话 */
	private String phone;
	/** 手机号码 */
	private String mobile;
	/**  手机号是否可见 */
	private String isMobileHide;
	/** QQ号 */
	private String qq;
	/** 状态(字典值)（ 在职 离职 停职） */
	private String status;
	/** 顺序号。号码越小越排在前面 */
	private Integer orderNo;
	/** 创建人 */
	private String creatorId;
	/** 创建时间 */
	private Date createTime;
	/** 删除标识 0 未删除  1 删除 */
	private Integer isDeleted;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人 */
	private String operatorId;
	/** 登录名 */
	private String loginName;
	/** 登录密码 */
	private String password;
	/** 账户是否启用 0 停用 1 启用 默认为停用状态 */
	private Integer isAccountEnable;
	/** 账户启用日期 */
	private Date enableDate;
	/** 账户作废日期 */
	private Date disableDate;
	/** 与医疗关系 */
	private List<PersonnelMhoR> personnelMhoRs;
	/** 与角色关系 */
	List<RoleAccountR> roleAccountRs;
	/** 已拥有的角色 */
	private List<Role> roles;
	/** 所属机构下剩余角色 */
	private List<Role> otherRoles;

	/**
	 * 用户桌面皮肤。
	 */
	private String skin;
	
	private String[] mhoId;
	private String[] department;
	private String[] personnelType;
	private String[] deptPersonnelCode;
	
	private String oldePassword;
	
	private String haveRole;

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getPersonnelCode() {
		return personnelCode;
	}

	public void setPersonnelCode(String personnelCode) {
		this.personnelCode = personnelCode;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFullnamePinyin() {
		return fullnamePinyin;
	}

	public void setFullnamePinyin(String fullnamePinyin) {
		this.fullnamePinyin = fullnamePinyin;
	}

	public String getFullnameJianpin() {
		return fullnameJianpin;
	}

	public void setFullnameJianpin(String fullnameJianpin) {
		this.fullnameJianpin = fullnameJianpin;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsMobileHide() {
		return isMobileHide;
	}

	public void setIsMobileHide(String isMobileHide) {
		this.isMobileHide = isMobileHide;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsAccountEnable() {
		return isAccountEnable;
	}

	public void setIsAccountEnable(Integer isAccountEnable) {
		this.isAccountEnable = isAccountEnable;
	}

	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}

	public List<PersonnelMhoR> getPersonnelMhoRs() {
		return personnelMhoRs;
	}

	public void setPersonnelMhoRs(List<PersonnelMhoR> personnelMhoRs) {
		this.personnelMhoRs = personnelMhoRs;
	}

	public List<RoleAccountR> getRoleAccountRs() {
		return roleAccountRs;
	}

	public void setRoleAccountRs(List<RoleAccountR> roleAccountRs) {
		this.roleAccountRs = roleAccountRs;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getOtherRoles() {
		return otherRoles;
	}

	public void setOtherRoles(List<Role> otherRoles) {
		this.otherRoles = otherRoles;
	}

	public String[] getMhoId() {
		return mhoId;
	}

	public void setMhoId(String[] mhoId) {
		this.mhoId = mhoId;
	}

	public String[] getDepartment() {
		return department;
	}

	public void setDepartment(String[] department) {
		this.department = department;
	}

	public String[] getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String[] personnelType) {
		this.personnelType = personnelType;
	}

	public String[] getDeptPersonnelCode() {
		return deptPersonnelCode;
	}

	public void setDeptPersonnelCode(String[] deptPersonnelCode) {
		this.deptPersonnelCode = deptPersonnelCode;
	}

	public String getOldePassword() {
		return oldePassword;
	}

	public void setOldePassword(String oldePassword) {
		this.oldePassword = oldePassword;
	}

	public String getHaveRole() {
		return haveRole;
	}

	public void setHaveRole(String haveRole) {
		this.haveRole = haveRole;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}
	
}
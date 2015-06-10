package com.zebone.btp.app.role.vo;

import java.util.Date;

public class Role {
	private String roleId;

	private String name;

	private String nameSpell;

	private String remark;

	private Long orderNo;

	private String medicalOrganId;

	private Short isPublicRole;

	private String creatorId;

	private Date createTime;

	private String operatorId;

	private Date updateTime;

	private Short isDelete;
	
	private String temp;//一个临时的变量，用于存放机构名称
	
	private String mhoName;

	public String getMhoName() {
		return mhoName;
	}

	public void setMhoName(String mhoName) {
		this.mhoName = mhoName;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getNameSpell() {
		return nameSpell;
	}

	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell == null ? null : nameSpell.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getMedicalOrganId() {
		return medicalOrganId;
	}

	public void setMedicalOrganId(String medicalOrganId) {
		this.medicalOrganId = medicalOrganId == null ? null : medicalOrganId.trim();
	}

	public Short getIsPublicRole() {
		return isPublicRole;
	}

	public void setIsPublicRole(Short isPublicRole) {
		this.isPublicRole = isPublicRole;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId == null ? null : operatorId.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj instanceof Role){
			Role role = (Role)obj;
			if(role != null && this.roleId !=null){
				if(this.roleId.equals(role.roleId)){
					return true;
				}
			}
		}
		return false;
	}

	
	
	
}
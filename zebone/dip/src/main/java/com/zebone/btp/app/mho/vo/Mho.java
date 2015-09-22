package com.zebone.btp.app.mho.vo;

import java.util.Date;

public class Mho {
	/**医疗机构Id*/
	private String mhoId;
	/**医疗机构名称*/
	private String mhoName;
	/**医疗机构编码*/
	private String mhoCode;
	/**理疗机构层级码*/
	private String levelCode;
	/**上级ID*/
	private String parentId;
	/**地址*/
	private String address;
	/**面积*/
	private String area;
	/**联系电话*/
	private String phone;
	/**机构类型编码*/
	private String typeCode;
	/**排序号*/
	private Integer orderNo;
	/**负责人*/
	private String manager;
	/**删除标志*/
	private Short isDeleted;
	/**机构种类编码*/
	private String category;
	/**描述*/
	private String remark;
	/**医院性质编码*/
	private String hospitalNature;
	/**医院等级编码*/
	private String hospitalGrade;
	/**医院类型编码*/
	private String hospitalType;
	/**是否城镇职工基本医保定点*/
	private Short isDesignatedHospital;
	/**是否新农合医保定点*/
	private Short isNcms;
	/**创建人Id*/
	private String creatorId;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**操作人ID*/
	private String operatorId;
	/**邮政编码*/
	private String post;
	/**网址*/
	private String website;
	/**上级机构名称*/
	private String parentName; 
	
	

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the isDeleted
	 */
	public Short getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getMhoId() {
		return mhoId;
	}

	public void setMhoId(String mhoId) {
		this.mhoId = mhoId == null ? null : mhoId.trim();
	}

	public String getMhoName() {
		return mhoName;
	}

	public void setMhoName(String mhoName) {
		this.mhoName = mhoName == null ? null : mhoName.trim();
	}

	public String getMhoCode() {
		return mhoCode;
	}

	public void setMhoCode(String mhoCode) {
		this.mhoCode = mhoCode == null ? null : mhoCode.trim();
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode == null ? null : levelCode.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode == null ? null : typeCode.trim();
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager == null ? null : manager.trim();
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getHospitalNature() {
		return hospitalNature;
	}

	public void setHospitalNature(String hospitalNature) {
		this.hospitalNature = hospitalNature == null ? null : hospitalNature.trim();
	}

	public String getHospitalGrade() {
		return hospitalGrade;
	}

	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade == null ? null : hospitalGrade.trim();
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType == null ? null : hospitalType.trim();
	}

	public Short getIsDesignatedHospital() {
		return isDesignatedHospital;
	}

	public void setIsDesignatedHospital(Short isDesignatedHospital) {
		this.isDesignatedHospital = isDesignatedHospital;
	}

	public Short getIsNcms() {
		return isNcms;
	}

	public void setIsNcms(Short isNcms) {
		this.isNcms = isNcms;
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
		this.operatorId = operatorId == null ? null : operatorId.trim();
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post == null ? null : post.trim();
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website == null ? null : website.trim();
	}
}
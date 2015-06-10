package com.zebone.dip.resources.vo;

/**
 * 家庭成员信息表
 * @author LinBin
 *
 */
public class ResourceFamilyMember {

	/**
	 * 主键id
	 */
	private String familyMemberId;
	/**
	 * 家庭id
	 */
	private String famiId;
	/**
	 * 序号
	 */
	private String orderNumber;
	/**
	 * 与户主关系
	 */
	private String householdCode;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证件类别
	 */
	private String idcardType;
	/**
	 * 证件号
	 */
	private String cardNo;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出生日期
	 */
	private String birthDate;
	/**
	 * 文化程度
	 */
	private String educationDegree;
	/**
	 * 职业
	 */
	private String careerCode;
	/**
	 * 婚姻
	 */
	private String marriageCode;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 个人状态
	 */
	private String state;
	/**
	 * 家庭编号
	 */
	private String famiNo;
	
	
	public String getFamilyMemberId() {
		return familyMemberId;
	}
	public void setFamilyMemberId(String familyMemberId) {
		this.familyMemberId = familyMemberId;
	}
	public String getFamiId() {
		return famiId;
	}
	public void setFamiId(String famiId) {
		this.famiId = famiId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getHouseholdCode() {
		return householdCode;
	}
	public void setHouseholdCode(String householdCode) {
		this.householdCode = householdCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcardType() {
		return idcardType;
	}
	public void setIdcardType(String idcardType) {
		this.idcardType = idcardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getEducationDegree() {
		return educationDegree;
	}
	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}
	public String getCareerCode() {
		return careerCode;
	}
	public void setCareerCode(String careerCode) {
		this.careerCode = careerCode;
	}
	public String getMarriageCode() {
		return marriageCode;
	}
	public void setMarriageCode(String marriageCode) {
		this.marriageCode = marriageCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFamiNo() {
		return famiNo;
	}
	public void setFamiNo(String famiNo) {
		this.famiNo = famiNo;
	}
	
}

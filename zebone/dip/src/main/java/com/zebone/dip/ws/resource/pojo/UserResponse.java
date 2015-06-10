package com.zebone.dip.ws.resource.pojo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("response")
public class UserResponse {
	
	@XStreamAlias("head")
	private ResponseHead responseHead = new ResponseHead() ;
	
	@XStreamAlias("body")
	private UserResponseBody userResponseBody = new UserResponseBody();

	public ResponseHead getResponseHead() {
		return responseHead;
	}

	public void setResponseHead(ResponseHead responseHead) {
		this.responseHead = responseHead;
	}

	public UserResponseBody getUserResponseBody() {
		return userResponseBody;
	}

	public void setUserResponseBody(UserResponseBody userResponseBody) {
		this.userResponseBody = userResponseBody;
	}

	public static class UserResponseBody{
		
		@XStreamImplicit(itemFieldName="item")
		private List<ResponseUserItem> responseUserItemList = new ArrayList<ResponseUserItem>();

		public List<ResponseUserItem> getResponseUserItemList() {
			return responseUserItemList;
		}

		public void setResponseUserItemList(List<ResponseUserItem> responseUserItemList) {
			this.responseUserItemList = responseUserItemList;
		}
		
    }
	
	public static class ResponseUserItem{
		private String uId;
	   @XStreamAlias("userCode")
	    private String code;
	   @XStreamAlias("userName")
	    private String name;
	    private String alias;
	    @XStreamAlias("idType")
	    private String idTypeCode;
	    private String idNo;
	    @XStreamAlias("medicalOrgan")
	    private String medicalOrganCode;
	    @XStreamAlias("sex")
	    private String sexCode;
	    //国籍
	    private String nationalityCode;
	    //民族
	    @XStreamAlias("nationality")
	    private String nationCode;
	    //籍贯
	    private String nativePlace;
	    private String birthday;
	    @XStreamAlias("maritalStatus")
	    private String maritalStatusCode;
	    @XStreamAlias("deptCode")
	    private String departmentCode;
	    @XStreamAlias("jobCategory")
	    private String jobCategoryCode;
	    private String postCode;
	    @XStreamAlias("positionalTitles")
	    private String positionalTitlesCode;
	    @XStreamAlias("politicalStatus")
	    private String politicalStatusCode;
	    private String addressCode;
	    private String address;
	    private String tel;
	    private String mobile;
	    private String email;
	    private String qq;
	    private String msn;
	    private String entryDate;
	    private String qualificationDate;
	    private float examSource;
	    private String certificateNo;
	    private String school;
	    private String profession;
	    private String education;
	    private String degree;
	    private String diplomaNo;
	    private String degreeCertificateNo;
	    private String graduationDate;
	    private String schoolSystem;
	    private String healthCondition;
	    private String firstWorkDate;
	    private String resume;
	    private String createTime;
	    private String inputOrgCode;
	    private String inputUserCode;
		public String getuId() {
			return uId;
		}
		public void setuId(String uId) {
			this.uId = uId;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
		public String getIdTypeCode() {
			return idTypeCode;
		}
		public void setIdTypeCode(String idTypeCode) {
			this.idTypeCode = idTypeCode;
		}
		public String getIdNo() {
			return idNo;
		}
		public void setIdNo(String idNo) {
			this.idNo = idNo;
		}
		public String getMedicalOrganCode() {
			return medicalOrganCode;
		}
		public void setMedicalOrganCode(String medicalOrganCode) {
			this.medicalOrganCode = medicalOrganCode;
		}
		public String getSexCode() {
			return sexCode;
		}
		public void setSexCode(String sexCode) {
			this.sexCode = sexCode;
		}
		public String getNationalityCode() {
			return nationalityCode;
		}
		public void setNationalityCode(String nationalityCode) {
			this.nationalityCode = nationalityCode;
		}
		public String getNationCode() {
			return nationCode;
		}
		public void setNationCode(String nationCode) {
			this.nationCode = nationCode;
		}
		public String getNativePlace() {
			return nativePlace;
		}
		public void setNativePlace(String nativePlace) {
			this.nativePlace = nativePlace;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getMaritalStatusCode() {
			return maritalStatusCode;
		}
		public void setMaritalStatusCode(String maritalStatusCode) {
			this.maritalStatusCode = maritalStatusCode;
		}
		public String getDepartmentCode() {
			return departmentCode;
		}
		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
		}
		public String getJobCategoryCode() {
			return jobCategoryCode;
		}
		public void setJobCategoryCode(String jobCategoryCode) {
			this.jobCategoryCode = jobCategoryCode;
		}
		public String getPostCode() {
			return postCode;
		}
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
		public String getPositionalTitlesCode() {
			return positionalTitlesCode;
		}
		public void setPositionalTitlesCode(String positionalTitlesCode) {
			this.positionalTitlesCode = positionalTitlesCode;
		}
		public String getPoliticalStatusCode() {
			return politicalStatusCode;
		}
		public void setPoliticalStatusCode(String politicalStatusCode) {
			this.politicalStatusCode = politicalStatusCode;
		}
		public String getAddressCode() {
			return addressCode;
		}
		public void setAddressCode(String addressCode) {
			this.addressCode = addressCode;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getQq() {
			return qq;
		}
		public void setQq(String qq) {
			this.qq = qq;
		}
		public String getMsn() {
			return msn;
		}
		public void setMsn(String msn) {
			this.msn = msn;
		}
		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		public String getQualificationDate() {
			return qualificationDate;
		}
		public void setQualificationDate(String qualificationDate) {
			this.qualificationDate = qualificationDate;
		}
		public float getExamSource() {
			return examSource;
		}
		public void setExamSource(float examSource) {
			this.examSource = examSource;
		}
		public String getCertificateNo() {
			return certificateNo;
		}
		public void setCertificateNo(String certificateNo) {
			this.certificateNo = certificateNo;
		}
		public String getSchool() {
			return school;
		}
		public void setSchool(String school) {
			this.school = school;
		}
		public String getProfession() {
			return profession;
		}
		public void setProfession(String profession) {
			this.profession = profession;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getDiplomaNo() {
			return diplomaNo;
		}
		public void setDiplomaNo(String diplomaNo) {
			this.diplomaNo = diplomaNo;
		}
		public String getDegreeCertificateNo() {
			return degreeCertificateNo;
		}
		public void setDegreeCertificateNo(String degreeCertificateNo) {
			this.degreeCertificateNo = degreeCertificateNo;
		}
		public String getGraduationDate() {
			return graduationDate;
		}
		public void setGraduationDate(String graduationDate) {
			this.graduationDate = graduationDate;
		}
		public String getSchoolSystem() {
			return schoolSystem;
		}
		public void setSchoolSystem(String schoolSystem) {
			this.schoolSystem = schoolSystem;
		}
		public String getHealthCondition() {
			return healthCondition;
		}
		public void setHealthCondition(String healthCondition) {
			this.healthCondition = healthCondition;
		}
		public String getFirstWorkDate() {
			return firstWorkDate;
		}
		public void setFirstWorkDate(String firstWorkDate) {
			this.firstWorkDate = firstWorkDate;
		}
		public String getResume() {
			return resume;
		}
		public void setResume(String resume) {
			this.resume = resume;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getInputOrgCode() {
			return inputOrgCode;
		}
		public void setInputOrgCode(String inputOrgCode) {
			this.inputOrgCode = inputOrgCode;
		}
		public String getInputUserCode() {
			return inputUserCode;
		}
		public void setInputUserCode(String inputUserCode) {
			this.inputUserCode = inputUserCode;
		}
	}
}

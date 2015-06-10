package com.zebone.empi.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ResidentInfo {
   
    private String empi;
    
    /**
     * 查询系统名称
     */
    private String system_code;
    
    public String getSystem_code() {
		return system_code;
	}

	public void setSystem_code(String system_code) {
		this.system_code = system_code;
	}

	@Size(max = 50, message="dept_code")
    private String deptCode;
   
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	@Size(max=50, message="Name")
	private String name;

	@Size(max=2, message="Sex")
    private String sex;
	
    private Date birthday;

    private Short age;

	@Size(max=3, message="nationality")
    private String nationality;

	@Size(max=2, message="nation")
    private String nation;

	@Size(max=2, message="maritalStatus")
    private String maritalStatus;

	@Size(max=20, message="mobileNumber")
    private String mobileNumber;

	@Size(max=20, message="telNumber")
    private String telNumber;

	@Size(max=4, message="profession")
    private String profession;

	@Size(max=70, message="workUnit")
    private String workUnit;

	@Size(max=50, message="contacts")
    private String contacts;

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	@Size(max=20, message="contactsMobile")
    private String contactsMobile;

	@Size(max=50, message="permanentAddress")
    private String permanentAddress;

	@Size(max=70, message="permanentCode")
    private String permanentCode;

	@Size(max=70, message="permanentHouseNo")
    private String permanentHouseNo;

	@Pattern(regexp="^$|[1-9]\\d{5}", message="addressZipcode")
    private String permanentZipcode;

	@Size(max=70, message="address")
    private String address;

	@Size(max=70, message="addressCode")
    private String addressCode;

	@Size(max=70, message="addressHouseNo")
    private String addressHouseNo;

	@Pattern(regexp="^$|[1-9]\\d{5}", message="addressZipcode")
    private String addressZipcode;
	
    private Date modifiedTime;
    
    private List<ResidentCard> cardList;

    private EmpiLog empiLog;

    //照片信息
    private byte[] photo;
    //人员等级
    private String starLevel;

    
    public EmpiLog getEmpiLog() {
		return empiLog;
	}

	public void setEmpiLog(EmpiLog empiLog) {
		this.empiLog = empiLog;
	}

	public List<ResidentCard> getCardList() {
    	
		return cardList;
	}

	public void setCardList(List<ResidentCard> cardList) {
		this.cardList = cardList;
	}

	public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi == null ? null : empi.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber == null ? null : telNumber.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile == null ? null : contactsMobile.trim();
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress == null ? null : permanentAddress.trim();
    }

    public String getPermanentHouseNo() {
        return permanentHouseNo;
    }

    public void setPermanentHouseNo(String permanentHouseNo) {
        this.permanentHouseNo = permanentHouseNo == null ? null : permanentHouseNo.trim();
    }

    public String getPermanentZipcode() {
        return permanentZipcode;
    }

    public void setPermanentZipcode(String permanentZipcode) {
        this.permanentZipcode = permanentZipcode == null ? null : permanentZipcode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddressHouseNo() {
        return addressHouseNo;
    }

    public void setAddressHouseNo(String addressHouseNo) {
        this.addressHouseNo = addressHouseNo == null ? null : addressHouseNo.trim();
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode == null ? null : addressZipcode.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    /**
     * 获取一级标识卡信息列表
     * @return
     */
    public List<ResidentCard> getFirstClassCards(){
    	List<ResidentCard> fcList = new ArrayList<ResidentCard>();
    	for(ResidentCard rc : this.cardList){
			if(rc.getCardType().trim().equals("1")||rc.getCardType().trim().equals("2")||rc.getCardType().trim().equals("3")){
				fcList.add(rc);
			}
    	}
    	return fcList;
    }
}
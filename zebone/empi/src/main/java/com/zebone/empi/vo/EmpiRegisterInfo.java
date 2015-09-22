package com.zebone.empi.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * EMPI注册信息对象
 *
 * @author 杨英
 * @version 2014-4-15 上午11:08
 */
@XStreamAlias("empi_info")
public class EmpiRegisterInfo {
    //机构编码
    @XStreamAlias("dept_code")
    private String deptCode;
    //姓名
    private String name;
    //性别
    private String sex;
    //出生日期
    private String birthday;
    //年龄
    private String age;
    //照片
    private String photo;
    //居民等级
    @XStreamAlias("star_level")
    private String starLevel;
    //国籍
    private String nationality;
    //民族
    private String nation;
    //婚姻状况
    @XStreamAlias("marital_status")
    private String maritalStatus;
    //手机号码
    @XStreamAlias("mobile_number")
    private String mobileNumber;
    //电话号码
    @XStreamAlias("tel_number")
    private String telNumber;
    //职业
    private String profession;
    //工作单位
    @XStreamAlias("work_unit")
    private String workUnit;
    //联系人姓名
    private String contacts;
    //联系人电话
    @XStreamAlias("contacts_mobile")
    private String contactMobile;
    //户籍地址
    @XStreamAlias("permanent_address")
    private String permanentAddress;
    //户籍地址编码
    @XStreamAlias("permanent_code")
    private String permanentCode;
    //户籍门牌号
    @XStreamAlias("permanent_house_no")
    private String permanentHouseNo;
    //户籍邮编
    @XStreamAlias("permanent_zipcode")
    private String permanentZipcode;
    //常住地址
    private String address;
    //常州地址编码
    @XStreamAlias("address_code")
    private String addressCode;
    //常住地址门牌号
    @XStreamAlias("address_hose_no")
    private String addressHouseNo;
    //常住地址邮编
    @XStreamAlias("address_zipcode")
    private String addressZipcode;

    @XStreamAlias("card_list")
    private CardList cardList;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentCode() {
        return permanentCode;
    }

    public void setPermanentCode(String permanentCode) {
        this.permanentCode = permanentCode;
    }

    public String getPermanentHouseNo() {
        return permanentHouseNo;
    }

    public void setPermanentHouseNo(String permanentHouseNo) {
        this.permanentHouseNo = permanentHouseNo;
    }

    public String getPermanentZipcode() {
        return permanentZipcode;
    }

    public void setPermanentZipcode(String permanentZipcode) {
        this.permanentZipcode = permanentZipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressHouseNo() {
        return addressHouseNo;
    }

    public void setAddressHouseNo(String addressHouseNo) {
        this.addressHouseNo = addressHouseNo;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
    }

    public CardList getCardList() {
        return cardList;
    }

    public void setCardList(CardList cardList) {
        this.cardList = cardList;
    }

    public static class CardList {
        @XStreamImplicit(itemFieldName="card")
        private List<Card> cardInfo;

        public List<Card> getCardInfo() {
            return cardInfo;
        }

        public void setCardInfo(List<Card> cardInfo) {
            this.cardInfo = cardInfo;
        }
    }

    public static class Card {
        //卡号
        private String no;
        //卡类型
        private String type;
        //发卡机构
        private String org;
        //卡序号
        @XStreamAlias("serial_no")
        private String serialNo;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }
    }

}
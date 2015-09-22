package com.zebone.dnode.engine.empi.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * 推送的信息对象
 *
 * @author 杨英
 * @version 2014-6-16 下午1:18
 */
@XStreamAlias("response")
public class PushInfo {
    @XStreamAlias("resident_info")
    private PersonalInfo personalInfo = new PersonalInfo();

    @XStreamAlias("card_list")
    private Card card = new Card();

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public static class PersonalInfo {
        //主索引号
        private String empi;
        //姓名
        private String name;
        //性别
        private String sex;
        //出生日期
        private String birthday;
        //照片信息
        private byte[] photo;
        //年龄
        private Short age;
        //人员等级
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
        //联系人
        private String contacts;
        //联系人电话
        @XStreamAlias("contacts_mobile")
        private String contactsMobile;
        //户籍地址
        @XStreamAlias("permanent_address")
        private String permanentAddress;
        //户籍地址编码
        @XStreamAlias("permanent_code")
        private String permanentCode;
        //户籍地址门牌号
        @XStreamAlias("permanent_house_no")
        private String permanentHouseNo;
        //户籍地址邮编
        @XStreamAlias("permanent_zipcode")
        private String permanentZipcode;
        //常住地址
        private String address;
        //常住地址编码
        @XStreamAlias("address_code")
        private String addressCode;
        //常住地址门牌号
        @XStreamAlias("address_hose_no")
        private String addressHouseNo;
        //常住地址邮编
        @XStreamAlias("address_zipcode")
        private String addressZipcode;

        public String getEmpi() {
            return empi;
        }

        public void setEmpi(String empi) {
            this.empi = empi;
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

        public Short getAge() {
            return age;
        }

        public void setAge(Short age) {
            this.age = age;
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

        public String getContactsMobile() {
            return contactsMobile;
        }

        public void setContactsMobile(String contactsMobile) {
            this.contactsMobile = contactsMobile;
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

        public byte[] getPhoto() {
            return photo;
        }

        public void setPhoto(byte[] photo) {
            this.photo = photo;
        }
    }

    public static class Card {
        @XStreamImplicit(itemFieldName = "card")
        private List<CardInfo> cardInfoLic = new ArrayList<CardInfo>();

        public List<CardInfo> getCardInfoLic() {
            return cardInfoLic;
        }

        public void setCardInfoLic(List<CardInfo> cardInfoLic) {
            this.cardInfoLic = cardInfoLic;
        }
    }

    public static class CardInfo {
        //卡号
        @XStreamAlias("no")
        private String cardNo;
        //卡类型
        @XStreamAlias("type")
        private String cardType;
        //卡序号
        @XStreamAlias("serial_no")
        private String cardSerialNo;
        //发卡机构
        @XStreamAlias("org")
        private String cardOrg;
        //卡等级
        @XStreamAlias("level")
        private String cardLevel;
        //卡注册时间
        @XStreamAlias("create_date")
        private String createDate;
        //卡状态  1 正常  2 注销
        @XStreamAlias("status")
        private String cardStatus;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardSerialNo() {
            return cardSerialNo;
        }

        public void setCardSerialNo(String cardSerialNo) {
            this.cardSerialNo = cardSerialNo;
        }

        public String getCardOrg() {
            return cardOrg;
        }

        public void setCardOrg(String cardOrg) {
            this.cardOrg = cardOrg;
        }

        public String getCardLevel() {
            return cardLevel;
        }

        public void setCardLevel(String cardLevel) {
            this.cardLevel = cardLevel;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCardStatus() {
            return cardStatus;
        }

        public void setCardStatus(String cardStatus) {
            this.cardStatus = cardStatus;
        }
    }
}

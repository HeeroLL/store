package com.zebone.btp.empi.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 注册对象 对应居民信息表EMPI_USER
 * 
 * @author ouyangxin 2013-1-16
 */
public class EmpiUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private String userId;

	/** EMPI ID */
	private String empiId;

	/** 新EMPI ID */
	private String newEmpiId;

	/** EMPI类型: ET1身份证（default）;ET2护照; ET3军官证 */
	private String empiType;

	/** 用户姓名 */
	private String userName;

	/** 用户身份证 */
	private String icn;

	/** 用户性别 */
	private String sex;

	/** 用户生日 */
	private String birthday;

	/** 用户民族 */
	private String nation;

	/** 用户电话 */
	private String tel;

	/** 用户照片 */
	private String photo;

	/** 用户户籍地址 */
	private String regaddress;

	/** 用户常住地址 */
	private String preaddress;

	/** 状态 1正常 0注销 */
	private String state;

	/** 创建时间 */
	private String createDate;

	/** 更新时间 */
	private String updateDate;

	/** 创建人 */
	private String creator;

	/** 更新人 */
	private String updator;

	/** 标识 1 正常 0删除 */
	private String delFlag;

	/** 对应的Card列表 */
	private List<EmpiCard> cards;

	/** 接受页面Card信息传值 */
	private String[] cardNo;
	private String[] cardSid;
	private String[] cardType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmpiId() {
		return empiId;
	}

	public void setEmpiId(String empiId) {
		this.empiId = empiId;
	}

	public String getNewEmpiId() {
		return newEmpiId;
	}

	public void setNewEmpiId(String newEmpiId) {
		this.newEmpiId = newEmpiId;
	}

	public String getEmpiType() {
		return empiType;
	}

	public void setEmpiType(String empiType) {
		this.empiType = empiType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcn() {
		return icn;
	}

	public void setIcn(String icn) {
		this.icn = icn;
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

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRegaddress() {
		return regaddress;
	}

	public void setRegaddress(String regaddress) {
		this.regaddress = regaddress;
	}

	public String getPreaddress() {
		return preaddress;
	}

	public void setPreaddress(String preaddress) {
		this.preaddress = preaddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public List<EmpiCard> getCards() {
		return cards;
	}

	public void setCards(List<EmpiCard> cards) {
		this.cards = cards;
	}

	public String[] getCardNo() {
		return cardNo;
	}

	public void setCardNo(String[] cardNo) {
		this.cardNo = cardNo;
	}

	public String[] getCardSid() {
		return cardSid;
	}

	public void setCardSid(String[] cardSid) {
		this.cardSid = cardSid;
	}

	public String[] getCardType() {
		return cardType;
	}

	public void setCardType(String[] cardType) {
		this.cardType = cardType;
	}

	/**
	 * toString方法
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

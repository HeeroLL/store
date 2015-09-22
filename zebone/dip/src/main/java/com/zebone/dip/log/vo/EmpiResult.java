package com.zebone.dip.log.vo;

import java.sql.Timestamp;

/**
 * Empi 列表vo类
 * @author ycm
 *
 */
public class EmpiResult {
	//时间
	private String eventTime;
	//事件
	private String event;
	//姓名
	private String name;
	//卡类型
	private String cardType;
	//卡号
	private String cardNo;
	//状态
	private String status;
	//机构编号
	private String orgCode;
	//机构名称
	private String orgName;
	//错误描述
	private String errorDescription;
	private Timestamp createTime;
	
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	 
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
}

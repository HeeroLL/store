package com.zebone.dip.log.vo;

/**
 * Empi 查询类
 * @author ycm
 *
 */
public class EmpiSearchObj {
	//开始日期
	private String startTime;
	//结束日期
	private String endTime;
	//姓名
	private String name;
	//标识类型
	private String cardType;
	//标识号码
	private String cardNo;
	//状态--注册更新：1 成功，2 失败； 查询：0 empi未上传,1 已匹配,2 未匹配
	private String status;
	//机构编码
	private String orgCode;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	 
}

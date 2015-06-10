package com.zebone.empi.vo;

import java.sql.Timestamp;

/**
 * EMPI匹配日志
 * 
 * @author YinCM
 * @date 2013-10-22 16:31:50
 */

public class EmpiMatchLog {

	/**
     *Empi Match Log ID
     */
	private String id;

	/**
     *来源系统
     */
	private String systemCode;

	/**
     *卡号
     */
	private String cardNo;

	/**
     *卡类型
     */
	private String cardType;

	/**
     *姓名
     */
	private String name;

	/**
     *发卡机构
     */
	private String cardOrg;

	/**
     * 匹配状态
     */
	private String matchState;
	
	/**
     * 匹配时间
     */
	private String matchTime;

	/**
     * 查询开始时间
     */
    private String startSearchTime;
    
    /**
     * 查询结束时间
     */
    private String endSearchTime;
    
	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public String getStartSearchTime() {
		return startSearchTime;
	}

	public void setStartSearchTime(String startSearchTime) {
		this.startSearchTime = startSearchTime;
	}

	public String getEndSearchTime() {
		return endSearchTime;
	}

	public void setEndSearchTime(String endSearchTime) {
		this.endSearchTime = endSearchTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode == null ? null : systemCode.trim();
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType == null ? null : cardType.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(String cardOrg) {
		this.cardOrg = cardOrg == null ? null : cardOrg.trim();
	}

	public String getMatchState() {
		return matchState;
	}

	public void setMatchState(String matchState) {
		this.matchState = matchState == null ? null : matchState.trim();
	}
}
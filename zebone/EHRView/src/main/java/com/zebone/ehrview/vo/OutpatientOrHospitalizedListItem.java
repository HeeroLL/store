package com.zebone.ehrview.vo;

/**
 * 老年人随访
 * @author YinCM
 *
 */
public class OutpatientOrHospitalizedListItem {
	private String code;
	private String time;
	private String doctor;
	private String org;
	private String ruleFollowupFlag;
	private String suggest;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getRuleFollowupFlag() {
		return ruleFollowupFlag;
	}
	public void setRuleFollowupFlag(String ruleFollowupFlag) {
		this.ruleFollowupFlag = ruleFollowupFlag;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	
	
}

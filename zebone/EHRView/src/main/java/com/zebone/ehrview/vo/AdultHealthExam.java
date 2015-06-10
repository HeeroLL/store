package com.zebone.ehrview.vo;

/**
 * 成年人健康体检
 * @author YinCM
 *
 */
public class AdultHealthExam {
	private String code;
	private String date;
	private String doctor;
	private String org;
	private String healthFlag;
	private String healthGuide;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getHealthFlag() {
		return healthFlag;
	}
	public void setHealthFlag(String healthFlag) {
		this.healthFlag = healthFlag;
	}
	public String getHealthGuide() {
		return healthGuide;
	}
	public void setHealthGuide(String healthGuide) {
		this.healthGuide = healthGuide;
	}
	
}

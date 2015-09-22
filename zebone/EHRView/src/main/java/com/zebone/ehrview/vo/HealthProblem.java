package com.zebone.ehrview.vo;

/**
 * 健康与疾病问题
 * @author Administrator
 *
 */
public class HealthProblem {
	private String time;
	private String healthProblem;
	private String healthActivity;
	private String code;
	private String docNo;
	
	
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	 
	public String getHealthProblem() {
		return healthProblem;
	}
	public void setHealthProblem(String healthProblem) {
		this.healthProblem = healthProblem;
	}
	public String getHealthActivity() {
		return healthActivity;
	}
	public void setHealthActivity(String healthActivity) {
		this.healthActivity = healthActivity;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

package com.zebone.ehrview.vo;

/**
 * 生命阶段
 * @author YinCM
 *
 */
public class LifePeriod {
	private String time;
	private String lifePeriod;
    private String healthPro;
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
	public String getLifePeriod() {
		return lifePeriod;
	}
	public void setLifePeriod(String lifePeriod) {
		this.lifePeriod = lifePeriod;
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
    public String getHealthPro() {
        return healthPro;
    }
    public void setHealthPro(String healthPro) {
        this.healthPro = healthPro;
    }
}

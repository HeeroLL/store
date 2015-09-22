package com.zebone.ehrview.vo;

/**
 * 高血压对象,糖尿病共用
 * @author YinCM
 * @since 2013-8-27
 */
public class Hypertension {
	private String code;
	private String time;
	private String doctor;
	private String org;
	private String method;
    //随访方式
    private String mode;
	private String comment;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
}

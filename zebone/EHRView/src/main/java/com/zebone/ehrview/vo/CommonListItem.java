package com.zebone.ehrview.vo;

/**
 * 老年人随访
 * 
 * @author YinCM
 * 
 */
public class CommonListItem {
	//文档编号
	private String code;
	//时间
	private String time;
	//医生编码
	private String doctor;
	//科室
	private String office;
	//机构
	private String org;
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
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

}

package com.isell.ps.logistics.chongqing.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsMesAskInfo {
	@XmlElement(name="MESSAGE_TYPE")
	private String messageType;
	@XmlElement(name="WORK_NO")
	private String workNo;
	@XmlElement(name="OP_DATE")
	private Date opDate;
	@XmlElement(name="SUCCESS")
	private String success;
	@XmlElement(name="MEMO")
	private String memo;
	@XmlElement(name="UDF1")
	private String udf1;
	@XmlElement(name="UDF2")
	private String udf2;
	@XmlElement(name="UDF3")
	private String udf3;
	@XmlElement(name="UDF4")
	private String udf4;
	@XmlElement(name="UDF5")
	private String udf5;
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public Date getOpDate() {
		return opDate;
	}
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUdf1() {
		return udf1;
	}
	public void setUdf1(String udf1) {
		this.udf1 = udf1;
	}
	public String getUdf2() {
		return udf2;
	}
	public void setUdf2(String udf2) {
		this.udf2 = udf2;
	}
	public String getUdf3() {
		return udf3;
	}
	public void setUdf3(String udf3) {
		this.udf3 = udf3;
	}
	public String getUdf4() {
		return udf4;
	}
	public void setUdf4(String udf4) {
		this.udf4 = udf4;
	}
	public String getUdf5() {
		return udf5;
	}
	public void setUdf5(String udf5) {
		this.udf5 = udf5;
	}
	
	
}

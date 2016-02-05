package com.isell.demo.gztg.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseBody {
	/*原业务编号*/
	@XmlElement(name="DocumentNo")
	private String documentNo;
	/*处理状态*/
	@XmlElement(name="ReturnCode")
	private String returnCode;
	/*回执说明*/
	@XmlElement(name="ReturnInfo")
	private String returnInfo;
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}
	
}

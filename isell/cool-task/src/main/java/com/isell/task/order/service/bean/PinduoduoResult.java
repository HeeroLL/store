package com.isell.task.order.service.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 拼多多订单推送返回信息
 * 
 * @author wangpeng
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Rsp")
@XmlType(propOrder = {})
public class PinduoduoResult {
	
	/**
	 * 1: 成功 0：失败
	 */
	@XmlElement(name = "Result")
	private int result;
	
	/**
	 * 拒绝原因
	 */
	@XmlElement(name = "Cause")
	private String cause;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}

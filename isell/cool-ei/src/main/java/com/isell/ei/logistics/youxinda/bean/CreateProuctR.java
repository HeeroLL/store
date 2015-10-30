package com.isell.ei.logistics.youxinda.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * createProduct 响应:
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-28]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ns1:createProductResponse")
public class CreateProuctR {
	
	/**
	 * 1: 成功 0: 失败
	 */
	private String ask;
	
	/**
	 * 成功新增产品后的产品Sku代码
	 */
	private String skuNo;
	
	/**
	 * 系统返回的信息
	 */
	private String message;
	
	/**
	 * 异常集合
	 */
	@XmlElement(name ="Error")
	private List<ErrorType> errors;

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorType> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorType> errors) {
		this.errors = errors;
	}

}

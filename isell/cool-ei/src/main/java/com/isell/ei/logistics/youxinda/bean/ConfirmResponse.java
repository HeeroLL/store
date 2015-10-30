package com.isell.ei.logistics.youxinda.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 创建商品响应
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfirmResponse {
	
	/**
	 * 1: 成功 0: 失败
	 */
	private String ask;
	
	/**
	 * 系统返回的信息
	 */
	private String message;
	
	/**
	 * 成功新增产品后的产品Sku代码
	 */
	private String skuNo;
	
	/**
	 * 异常集合
	 */
	private List<ErrorType> error;

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public List<ErrorType> getError() {
		return error;
	}

	public void setError(List<ErrorType> error) {
		this.error = error;
	}

}

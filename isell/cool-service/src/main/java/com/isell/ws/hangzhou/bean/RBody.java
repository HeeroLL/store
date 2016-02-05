package com.isell.ws.hangzhou.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class RBody {
	/** 签名信息 */
	@XmlElement(name = "jkfSign")
	private RJkfSign jkfSign;
	/** 产品登记审批结果信息 */
	@XmlElement(name = "productRecord")
	private RProductRecord productRecord;
	
	@XmlElementWrapper(name = "list")  
    @XmlElement(name = "jkfResult") 
	private List<RJkfResult> results = new ArrayList<RJkfResult>();
	
	public RJkfSign getJkfSign() {
		return jkfSign;
	}
	public void setJkfSign(RJkfSign jkfSign) {
		this.jkfSign = jkfSign;
	}
	public RProductRecord getProductRecord() {
		return productRecord;
	}
	public void setProductRecord(RProductRecord productRecord) {
		this.productRecord = productRecord;
	}
	public List<RJkfResult> getResults() {
		return results;
	}
	public void setResults(List<RJkfResult> results) {
		this.results = results;
	}
}

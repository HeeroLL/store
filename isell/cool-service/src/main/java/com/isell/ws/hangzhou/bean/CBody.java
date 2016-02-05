package com.isell.ws.hangzhou.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class CBody {

	@XmlElementWrapper(name = "orderInfoList")  
    @XmlElement(name = "orderInfo") 
	private List<COrderInfo> orderInfos;
	@XmlElementWrapper(name = "productRecordList")  
    @XmlElement(name = "productRecord") 
	private List<CProductRecord> productRecords;

	public List<COrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(List<COrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

	public List<CProductRecord> getProductRecords() {
		return productRecords;
	}

	public void setProductRecords(List<CProductRecord> productRecords) {
		this.productRecords = productRecords;
	}
	
}

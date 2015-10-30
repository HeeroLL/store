package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建商品第三层
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HeaderRequest")
public class CreateProduct {
	
	@XmlElement(name = "HeaderRequest")
	private HeaderRequest headerRequest;
	
	@XmlElement(name = "ProductInfo")
	private ProductInfo productInfo;

	public HeaderRequest getHeaderRequest() {
		return headerRequest;
	}

	public void setHeaderRequest(HeaderRequest headerRequest) {
		this.headerRequest = headerRequest;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

}

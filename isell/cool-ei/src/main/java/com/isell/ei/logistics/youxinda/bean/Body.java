package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 创建商品第二层
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {
	
	@XmlElement(name = "ns1:createProduct")
	private CreateProduct createProduct;
	
	@XmlElement(name = "ns1:createProductResponse")
	private CreateProuctR createProuctR;
	
	@XmlElement(name = "ns1:createOrder")
    private CreateOrder createOrder;
	
	@XmlElement(name = "ns1:createOrderResponse")
	private CreateOrderR createOrderR;

	public CreateProduct getCreateProduct() {
		return createProduct;
	}

	public void setCreateProduct(CreateProduct createProduct) {
		this.createProduct = createProduct;
	}

	public CreateOrder getCreateOrder() {
		return createOrder;
	}

	public void setCreateOrder(CreateOrder createOrder) {
		this.createOrder = createOrder;
	}
}

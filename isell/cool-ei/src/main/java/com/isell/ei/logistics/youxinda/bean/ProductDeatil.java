package com.isell.ei.logistics.youxinda.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单商品信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orderProduct")
public class ProductDeatil {
	
	/**
	 * 产品sku
	 */
	private String productSku;
	
	/**
	 * 产品数量
	 */
	private int opQuantity;
	
	/**
	 * 交易价格
	 */
	private Float transactionPrice;
	
	/**
	 * 成交单价
	 */
	private Float dealPrice;

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public int getOpQuantity() {
		return opQuantity;
	}

	public void setOpQuantity(int opQuantity) {
		this.opQuantity = opQuantity;
	}

	public Float getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(Float transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public Float getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(Float dealPrice) {
		this.dealPrice = dealPrice;
	}

}

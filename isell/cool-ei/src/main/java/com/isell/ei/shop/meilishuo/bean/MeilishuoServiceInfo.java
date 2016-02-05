package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说订单客服信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoServiceInfo {
	
    /** 发票抬头 */
	private String consumer_hotline;
	
	/** 发票信息 */
	private MeilishuoInvoiceInfo invoice_info;

	/** 发票抬头 */
	public String getConsumer_hotline() {
		return consumer_hotline;
	}

	/** 发票抬头 */
	public void setConsumer_hotline(String consumer_hotline) {
		this.consumer_hotline = consumer_hotline;
	}

	/** 发票信息 */
	public MeilishuoInvoiceInfo getInvoice_info() {
		return invoice_info;
	}

	/** 发票信息 */
	public void setInvoice_info(MeilishuoInvoiceInfo invoice_info) {
		this.invoice_info = invoice_info;
	}

}

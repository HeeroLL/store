package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说订单发票信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoInvoiceInfo {
	
	/** 是否需要发票 */
	private boolean need_invoice;
	
	/** 发票抬头 */
	private String invoice_title;
	
	/** 发票内容 */
	private String invoice_content;

	/** 是否需要发票 */
	public boolean isNeed_invoice() {
		return need_invoice;
	}

	/** 是否需要发票 */
	public void setNeed_invoice(boolean need_invoice) {
		this.need_invoice = need_invoice;
	}

	/** 发票抬头 */
	public String getInvoice_title() {
		return invoice_title;
	}

	/** 发票抬头 */
	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	/** 发票内容 */
	public String getInvoice_content() {
		return invoice_content;
	}

	/** 发票内容 */
	public void setInvoice_content(String invoice_content) {
		this.invoice_content = invoice_content;
	}

}

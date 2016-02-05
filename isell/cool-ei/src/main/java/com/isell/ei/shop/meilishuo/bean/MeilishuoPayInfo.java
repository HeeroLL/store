package com.isell.ei.shop.meilishuo.bean;

import java.util.Date;

/**
 * 美丽说订单支付信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoPayInfo {
	
	/** 支付企业 */
	private String pay_enterprise;
	
	/** 支付企业编号 */
	private String bank_code;
	
	/** 支付流水号 */
	private String gateway_pay_no;
	
	/** 海关报关流水号 */
	private String customs_declare_no;
	
	/** 下单时间 */
	private Date pay_time;

	/** 支付企业 */
	public String getPay_enterprise() {
		return pay_enterprise;
	}

	/** 支付企业 */
	public void setPay_enterprise(String pay_enterprise) {
		this.pay_enterprise = pay_enterprise;
	}

	/** 支付企业编号 */
	public String getBank_code() {
		return bank_code;
	}

	/** 支付企业编号 */
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	/** 支付流水号 */
	public String getGateway_pay_no() {
		return gateway_pay_no;
	}

	/** 支付流水号 */
	public void setGateway_pay_no(String gateway_pay_no) {
		this.gateway_pay_no = gateway_pay_no;
	}

	/** 下单时间 */
	public Date getPay_time() {
		return pay_time;
	}

	/** 下单时间 */
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	/** 海关报关流水号 */
	public String getCustoms_declare_no() {
		return customs_declare_no;
	}

	/** 海关报关流水号 */
	public void setCustoms_declare_no(String customs_declare_no) {
		this.customs_declare_no = customs_declare_no;
	}

}

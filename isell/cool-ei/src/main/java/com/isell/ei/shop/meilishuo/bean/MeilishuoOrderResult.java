package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说订单返回
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoOrderResult {
	
	/**
	 * 订单返回信息
	 */
	private MeilishuoOrderListResult higo_order_search;
	
	/**
	 * 错误信息
	 */
	private MeilishuoErrorInfo error_response;

	/**
	 * 订单返回信息
	 */
	public MeilishuoOrderListResult getHigo_order_search() {
		return higo_order_search;
	}

	/**
	 * 订单返回信息
	 */
	public void setHigo_order_search(MeilishuoOrderListResult higo_order_search) {
		this.higo_order_search = higo_order_search;
	}

	/**
	 * 错误信息
	 */
	public MeilishuoErrorInfo getError_response() {
		return error_response;
	}

	/**
	 * 错误信息
	 */
	public void setError_response(MeilishuoErrorInfo error_response) {
		this.error_response = error_response;
	}

}

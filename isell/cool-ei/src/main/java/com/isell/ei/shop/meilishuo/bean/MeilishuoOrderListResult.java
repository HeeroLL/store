package com.isell.ei.shop.meilishuo.bean;

import java.util.List;

/**
 * 美丽说订单列表返回信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoOrderListResult {
	
	/** 订单列表 */
	private List<MeilishuoOrderData> order_data;
	
	/** 单页记录数，默认20 */
	private int page_size;
	
	/** 当前页 */
	private int page_curr;
	
	/** 总记录数 */
	private int total_num;

	/** 订单列表 */
	public List<MeilishuoOrderData> getOrder_data() {
		return order_data;
	}
	
	/** 订单列表 */
	public void setOrder_data(List<MeilishuoOrderData> order_data) {
		this.order_data = order_data;
	}

	/** 单页记录数，默认20 */
	public long getPage_size() {
		return page_size;
	}
	
	/** 单页记录数，默认20 */
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	/** 当前页 */
	public int getPage_curr() {
		return page_curr;
	}

	/** 当前页 */
	public void setPage_curr(int page_curr) {
		this.page_curr = page_curr;
	}

	/** 总记录数 */
	public int getTotal_num() {
		return total_num;
	}

	/** 总记录数 */
	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

}

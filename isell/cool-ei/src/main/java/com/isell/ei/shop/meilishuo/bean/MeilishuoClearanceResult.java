package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说清关状态回传
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoClearanceResult {
	
	/** 订单号 */
	private long order_id;
	
	/** 清关状态 */
	private String clearance_status;
	
	/** 清关状态说明 */
	private String clearance_status_text;
	
	/** 处理结果 true：成功  false 失败 */
	private Boolean  operate_success;

	/** 订单号 */
	public long getOrder_id() {
		return order_id;
	}

	/** 订单号 */
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	/** 清关状态 */
	public String getClearance_status() {
		return clearance_status;
	}

	/** 清关状态 */
	public void setClearance_status(String clearance_status) {
		this.clearance_status = clearance_status;
	}

	/** 清关状态说明 */
	public String getClearance_status_text() {
		return clearance_status_text;
	}
	
	/** 清关状态说明 */
	public void setClearance_status_text(String clearance_status_text) {
		this.clearance_status_text = clearance_status_text;
	}

	/** 处理结果 true：成功  false 失败 */
	public Boolean getOperate_success() {
		return operate_success;
	}

	/** 处理结果 true：成功  false 失败 */
	public void setOperate_success(Boolean operate_success) {
		this.operate_success = operate_success;
	}

}

package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说发货返回值
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoSendGoodsResult {
	
	/** 订单号 */
	private long order_id;
	
	/** 发货方式 */
	private long send_type;
	
	/**  物流 */
	private String express_company;
	
	/** 运单号 */
	private String express_number;
	
	/** 处理结果 true：成功  false 失败 */
	private Boolean operate_success;

	/** 订单号 */
	public long getOrder_id() {
		return order_id;
	}

	/** 订单号 */
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	/** 发货方式 */
	public long getSend_type() {
		return send_type;
	}

	/** 发货方式 */
	public void setSend_type(long send_type) {
		this.send_type = send_type;
	}

	/**  物流 */
	public String getExpress_company() {
		return express_company;
	}
	
	/**  物流 */
	public void setExpress_company(String express_company) {
		this.express_company = express_company;
	}

	/** 运单号 */
	public String getExpress_number() {
		return express_number;
	}

	/** 运单号 */
	public void setExpress_number(String express_number) {
		this.express_number = express_number;
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

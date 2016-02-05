package com.isell.ei.shop.meilishuo.bean;

import java.util.Date;

/**
 * 美丽说订单详情
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoOrderInfo {
	
	/**  */
	private boolean bondedArea;

	/** 订单号 */
	private long order_id;
	
	/**
	 * 订单状态，取值如下： 1001：订单关闭； 1002：订单正常； 1003：交易成功； 1004：订单已结算
	 */
	private int order_state;
	
	/**
	 * 订单状态，取值如下： 1001：订单关闭； 1002：订单正常； 1003：交易成功； 1004：订单已结算
	 */
	private String order_state_text;
	
	/** 
	 * 订单流转状态，取值如下： 1000：未支付； 1001：已支付； 1002：备货中； 1003：已发货； 1004：确认收货； 1005：申请退货；1006：批准退货
	 */
	private int order_status;
	
	/**
	 * 订单流转状态，取值如下： 1000：未支付； 1001：已支付； 1002：备货中； 1003：已发货； 1004：确认收货； 1005：申请退货；1006：批准退货
	 */
	private String order_status_text;
	
	/** 订单总价，三位小数 */
	private double order_price;
	
	/** 运费 */
	private double shipping_fee;
	
	/** 价格单位，默认CNY */
	private String price_unit;
	
	/** 商家优惠券 */
	private double shop_coupon_amount;
	
	/** 平台优惠券 */
	private double platform_coupon_amount;
	
	/** 下单时间 */
	private Date order_ctime;
	
	/** 买家备注 */
	private String buyer_comment;
	
	/** 备注时间 */
	private Date comment_time;
	
	/** 保税区 id */
	private String bonded_area_id;
	
	/**
	 * 是否是保税区订单
	 */
	private boolean is_bonded_area;
	
	/**
	 * 保税区名称
	 */
	private String bonded_area_name;
	
	/**  */
	public boolean isBondedArea() {
		return bondedArea;
	}

	/**  */
	public void setBondedArea(boolean bondedArea) {
		this.bondedArea = bondedArea;
	}

	/** 订单号 */
	public long getOrder_id() {
		return order_id;
	}

	/** 订单号 */
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	/** 订单状态 */
	public int getOrder_status() {
		return order_status;
	}

	/** 订单状态 */
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	
	/** 订单状态说明 */
	public String getOrder_status_text() {
		return order_status_text;
	}

	/** 订单状态说明 */
	public void setOrder_status_text(String order_status_text) {
		this.order_status_text = order_status_text;
	}

	/** 订单总价，三位小数 */
	public double getOrder_price() {
		return order_price;
	}

	/** 订单总价，三位小数 */
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}

	/** 运费 */
	public double getShipping_fee() {
		return shipping_fee;
	}

	/** 运费 */
	public void setShipping_fee(double shipping_fee) {
		this.shipping_fee = shipping_fee;
	}

	/** 价格单位，默认CNY */
	public String getPrice_unit() {
		return price_unit;
	}

	/** 价格单位，默认CNY */
	public void setPrice_unit(String price_unit) {
		this.price_unit = price_unit;
	}

	/** 商家优惠券 */
	public double getShop_coupon_amount() {
		return shop_coupon_amount;
	}

	/** 商家优惠券 */
	public void setShop_coupon_amount(double shop_coupon_amount) {
		this.shop_coupon_amount = shop_coupon_amount;
	}

	/** 平台优惠券 */
	public double getPlatform_coupon_amount() {
		return platform_coupon_amount;
	}

	/** 平台优惠券 */
	public void setPlatform_coupon_amount(double platform_coupon_amount) {
		this.platform_coupon_amount = platform_coupon_amount;
	}

	/** 下单时间 */
	public Date getOrder_ctime() {
		return order_ctime;
	}

	/** 下单时间 */
	public void setOrder_ctime(Date order_ctime) {
		this.order_ctime = order_ctime;
	}
	
	/** 买家备注 */
	public String getBuyer_comment() {
		return buyer_comment;
	}
	
	/** 买家备注 */
	public void setBuyer_comment(String buyer_comment) {
		this.buyer_comment = buyer_comment;
	}

	/** 备注时间 */
	public Date getComment_time() {
		return comment_time;
	}

	/** 备注时间 */
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}

	/**
	 * 订单状态，取值如下： 1001：订单关闭； 1002：订单正常； 1003：交易成功； 1004：订单已结算
	 */
	public int getOrder_state() {
		return order_state;
	}

	/**
	 * 订单状态，取值如下： 1001：订单关闭； 1002：订单正常； 1003：交易成功； 1004：订单已结算
	 */
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}

	/**
	 * 订单状态，取值如下： 1001：订单关闭； 1002：订单正常； 1003：交易成功； 1004：订单已结算
	 */
	public String getOrder_state_text() {
		return order_state_text;
	}

	/**
	 * 订单状态，取值如下： 1001：订单关闭； 1002：订单正常； 1003：交易成功； 1004：订单已结算
	 */
	public void setOrder_state_text(String order_state_text) {
		this.order_state_text = order_state_text;
	}

	/** 保税区 id */
	public String getBonded_area_id() {
		return bonded_area_id;
	}

	/** 保税区 id */
	public void setBonded_area_id(String bonded_area_id) {
		this.bonded_area_id = bonded_area_id;
	}

	/**
	 * 是否是保税区订单
	 */
	public boolean isIs_bonded_area() {
		return is_bonded_area;
	}

	/**
	 * 是否是保税区订单
	 */
	public void setIs_bonded_area(boolean is_bonded_area) {
		this.is_bonded_area = is_bonded_area;
	}

	/**
	 * 保税区名称
	 */
	public String getBonded_area_name() {
		return bonded_area_name;
	}

	/**
	 * 保税区名称
	 */
	public void setBonded_area_name(String bonded_area_name) {
		this.bonded_area_name = bonded_area_name;
	}

}

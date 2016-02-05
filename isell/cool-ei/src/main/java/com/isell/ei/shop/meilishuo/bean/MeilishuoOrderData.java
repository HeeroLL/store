package com.isell.ei.shop.meilishuo.bean;

import java.util.List;

/**
 * 美丽说订单信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoOrderData {
	
	/** 订单信息 */
	private MeilishuoOrderInfo order_info;
	
	/** 商品信息 */
	private 	List<MeilishuoGoodsInfo> goods_info;
	
	/** 支付信息 */
	private MeilishuoPayInfo pay_info;
	
	/** 收货信息 */
	private MeilishuoAddressInfo address_info;
	
	/** 购买信息 */
	private MeilishuoBuyerInfo buyer_info;
	
	/** 售后信息 */
	private MeilishuoServiceInfo service_info;

	/** 订单信息 */
	public MeilishuoOrderInfo getOrder_info() {
		return order_info;
	}

	/** 订单信息 */
	public void setOrder_info(MeilishuoOrderInfo order_info) {
		this.order_info = order_info;
	}

	/** 商品信息 */
	public List<MeilishuoGoodsInfo> getGoods_info() {
		return goods_info;
	}

	/** 商品信息 */
	public void setGoods_info(List<MeilishuoGoodsInfo> goods_info) {
		this.goods_info = goods_info;
	}

	/** 支付信息 */
	public MeilishuoPayInfo getPay_info() {
		return pay_info;
	}

	/** 支付信息 */
	public void setPay_info(MeilishuoPayInfo pay_info) {
		this.pay_info = pay_info;
	}

	/** 收货信息 */
	public MeilishuoAddressInfo getAddress_info() {
		return address_info;
	}

	/** 收货信息 */
	public void setAddress_info(MeilishuoAddressInfo address_info) {
		this.address_info = address_info;
	}

	/** 购买信息 */
	public MeilishuoBuyerInfo getBuyer_info() {
		return buyer_info;
	}

	/** 购买信息 */
	public void setBuyer_info(MeilishuoBuyerInfo buyer_info) {
		this.buyer_info = buyer_info;
	}

	/** 售后信息 */
	public MeilishuoServiceInfo getService_info() {
		return service_info;
	}

	/** 售后信息 */
	public void setService_info(MeilishuoServiceInfo service_info) {
		this.service_info = service_info;
	}

}

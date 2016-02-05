package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说订单购买人信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoBuyerInfo {
	
	/** 购买人 */
	private String buyer_nick_name;
	
	/** 购买人id */
	private String buyer_id;

	/** 购买人 */
	public String getBuyer_nick_name() {
		return buyer_nick_name;
	}

	/** 购买人 */
	public void setBuyer_nick_name(String buyer_nick_name) {
		this.buyer_nick_name = buyer_nick_name;
	}

	/** 购买人id */
	public String getBuyer_id() {
		return buyer_id;
	}

	/** 购买人id */
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

}

package com.isell.service.shop.po;

import java.math.BigDecimal;

/**
 *  生意参谋返回类
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-30]
 */
public class CoonShopBusinessInfo {
	
	/**
	 * 时间
	 */
	private String timeKey;
	
	/**
	 * 值
	 */
	private BigDecimal value;

	public String getTimeKey() {
		return timeKey;
	}

	public void setTimeKey(String timeKey) {
		this.timeKey = timeKey;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}

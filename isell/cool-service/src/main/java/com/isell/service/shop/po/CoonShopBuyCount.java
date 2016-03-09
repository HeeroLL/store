package com.isell.service.shop.po;


/**
 * 酷店购买数量
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-26]
 */
public class CoonShopBuyCount {
	
	/**
	 * 时间
	 */
	private String selectTime ;
	
	/**
	 * 购买人次
	 */
	private int count;

	public String getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	} 

}

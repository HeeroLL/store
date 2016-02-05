package com.isell.service.shop.po;

/**
 *  生意参谋参数类
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-30]
 */
public class CoonShopBusinessParam {
	
	/**
	 * 时间类型 1：本周 2：近一周 3：本月 4：近一月 5：今年 6：近一年
	 */
	public static final String TIME_TYPE_1 = "1";
	
	/**
	 * 时间类型 1：本周 2：近一周 3：本月 4：近一月 5：今年 6：近一年
	 */
	public static final String TIME_TYPE_2 = "2";
	
	/**
	 * 时间类型 1：本周 2：近一周 3：本月 4：近一月 5：今年 6：近一年
	 */
	public static final String TIME_TYPE_3 = "3";
	
	/**
	 * 时间类型 1：本周 2：近一周 3：本月 4：近一月 5：今年 6：近一年
	 */
	public static final String TIME_TYPE_4 = "4";
	
	/**
	 * 时间类型 1：本周 2：近一周 3：本月 4：近一月 5：今年 6：近一年
	 */
	public static final String TIME_TYPE_5 = "5";
	
	/**
	 * 时间类型 1：本周 2：近一周 3：本月 4：近一月 5：今年 6：近一年
	 */
	public static final String TIME_TYPE_6= "6";
	
	/**
	 * 查询类型 1：店铺收入 2：店铺订单量 3：店铺交易额 4：店铺访客数 5：店铺浏览量
	 */
	public static final String select_Type_1= "1";
	
	/**
	 * 查询类型 1：店铺收入 2：店铺订单量 3：店铺交易额 4：店铺访客数 5：店铺浏览量
	 */
	public static final String select_Type_2= "2";
	
	/**
	 * 查询类型 1：店铺收入 2：店铺订单量 3：店铺交易额 4：店铺访客数 5：店铺浏览量
	 */
	public static final String select_Type_3= "3";
	
	/**
	 * 查询类型 1：店铺收入 2：店铺订单量 3：店铺交易额 4：店铺访客数 5：店铺浏览量
	 */
	public static final String select_Type_4= "4";
	
	/**
	 * 查询类型 1：店铺收入 2：店铺订单量 3：店铺交易额 4：店铺访客数 5：店铺浏览量
	 */
	public static final String select_Type_5= "5";
	
	/**
	 * 酷店主键
	 */
	private String shopId;
	
	/**
	 * 时间类型 1：本周 2：近一周 3：本月 4：近一月 5：今年 6：近一年
	 */
	private String timeType;
	
	/**
	 * 查询类型 1：店铺收入 2：店铺订单量 3：店铺交易额 4：店铺访客数 5：店铺浏览量
	 */
	private String selectType;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	
	

}

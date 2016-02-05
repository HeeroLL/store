package com.isell.service.order.po;
/**
 * 运单信息参数
 * 
 * @author maowejie
 * @version [版本号, 2016-1-21]
 */
public class CoolOrderWayBill {
	private String orderOldno;
	private String beginTime;
	private String endTime;
	private String timeType;
	private String supplier;
	public String getOrderOldno() {
		return orderOldno;
	}
	public void setOrderOldno(String orderOldno) {
		this.orderOldno = orderOldno;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
}

package com.isell.service.order.po;
/**
 * 运单信息返回
 * 
 * @author maowejie
 * @version [版本号, 2016-1-21]
 */
public class CoolOrderWayBillReturn {
	/**
	 * 外部系统订单号
	 */
	private String orderOldNo;
	/**
	 * 货运单号
	 */
	private String wayBillNo;
	/**
	 * 发货方式（中文 例：申通速递 圆通速递等）
	 */
	private String sendStyle;
	/**
	 * 查询失败原因
	 */
	private String cause;
	/**
	 * 订单创建时间
	 */
	private String createtime;
	/**
	 * 订单发货时间
	 */
	private String sendtime;
	/**
	 * 订单发货精确时间
	 */
	private String time;
	public String getOrderOldNo() {
		return orderOldNo;
	}
	public void setOrderOldNo(String orderOldNo) {
		this.orderOldNo = orderOldNo;
	}
	public String getWayBillNo() {
		return wayBillNo;
	}
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	public String getSendStyle() {
		return sendStyle;
	}
	public void setSendStyle(String sendStyle) {
		this.sendStyle = sendStyle;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}	

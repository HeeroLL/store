package com.zebone.dip.operate.vo;

/**
 * 订阅事件
 * @author LinBin
 * May 16, 2014
 */
public class SubscribeEvent {
	/**主键id*/
	private String id;
	/**服务ID*/
	private String serviceId;
	/**订阅事件*/
	private String subTime;
	/**订阅机构（平台机构）*/
	private String orgCode;
	/**用户id*/
	private String userId;
	/**事件发生时间*/
	private String eventTime;
	/**事件类型(1:节点停止，2.服务停止)*/
	private String eventType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSubTime() {
		return subTime;
	}
	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}

package com.zebone.dip.operate.vo;

/**
 * 订阅服务关系
 * @author LinBin
 * Apr 28, 2014
 */
public class SubscribeService {
	
	/**主键id*/
	private String id;
	/**服务ID*/
	private String serviceId;
	/**是否历史数据*/
	private String isHistory;
	/**订阅服务开始时间*/
	private String beginDate;
	/**订阅服务结束时间*/
	private String endDate;
	/**是否当前数据*/
	private String isCurrent;
	/**订阅频率(非历史数据)*/
	private String subFrequ;
	/**是否订阅*/
	private String isOrder;
	/**订阅时间*/
	private String subTime;
	/**订阅用户ID*/
	private String userId;
	/**订阅机构*/
	private String orgCode;
	/**服务类型*/
	private String serviceType;
	/**服务名称*/
	private String serviceName;
	/**节点名字*/
	private String nodeName;
	/**历史数据是否跑完*/
	private String isHistoryRun;
	/**节点状态*/
	private String nodeState;
	/**服务状态*/
	private String serviceState;
	
	public String getNodeState() {
		return nodeState;
	}
	public void setNodeState(String nodeState) {
		this.nodeState = nodeState;
	}
	public String getServiceState() {
		return serviceState;
	}
	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}
	public String getIsHistoryRun() {
		return isHistoryRun;
	}
	public void setIsHistoryRun(String isHistoryRun) {
		this.isHistoryRun = isHistoryRun;
	}
	public String getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
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
	public String getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSubFrequ() {
		return subFrequ;
	}
	public void setSubFrequ(String subFrequ) {
		this.subFrequ = subFrequ;
	}
	public String getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	public String getSubTime() {
		return subTime;
	}
	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}

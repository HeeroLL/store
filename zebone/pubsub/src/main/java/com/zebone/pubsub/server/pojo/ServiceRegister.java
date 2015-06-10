package com.zebone.pubsub.server.pojo;

/**
 * 服务注册
 * @author LinBin
 * Apr 28, 2014
 */
public class ServiceRegister {
	
	/**主键id*/
	private String id;
	/**服务名称*/
	private String serviceName;
	/**服务编码*/
	private String serviceCode;
	/**服务状态(启用,停用)*/
	private String serviceState;
	/**服务类型(1,文档服务,2资源服务等)*/
	private String serviceType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceState() {
		return serviceState;
	}
	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

}

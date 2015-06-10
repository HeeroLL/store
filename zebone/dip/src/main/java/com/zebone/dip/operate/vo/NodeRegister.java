package com.zebone.dip.operate.vo;

/**
 * 节点注册
 * @author LinBin
 * Apr 28, 2014
 */
public class NodeRegister {
	
	/**主键id*/
	private String id;
	/**节点名字*/
	private String nodeName1;
	/**节点描述*/
	private String nodeDesc;
	/**节点ip*/
	private String nodeIp;
	/**MQ接受队列名称*/
	private String mqQueueUrl;
	/**节点状态（启动，停止）*/
	private String nodeState;
	/**机构*/
	private String nodeOrg;
	/**机构名称*/
	private String nodeOrgName;
	
	public String getNodeOrgName() {
		return nodeOrgName;
	}
	public void setNodeOrgName(String nodeOrgName) {
		this.nodeOrgName = nodeOrgName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeName1() {
		return nodeName1;
	}
	public void setNodeName1(String nodeName1) {
		this.nodeName1 = nodeName1;
	}
	public String getNodeDesc() {
		return nodeDesc;
	}
	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	public String getMqQueueUrl() {
		return mqQueueUrl;
	}
	public void setMqQueueUrl(String mqQueueUrl) {
		this.mqQueueUrl = mqQueueUrl;
	}
	public String getNodeState() {
		return nodeState;
	}
	public void setNodeState(String nodeState) {
		this.nodeState = nodeState;
	}
	public String getNodeOrg() {
		return nodeOrg;
	}
	public void setNodeOrg(String nodeOrg) {
		this.nodeOrg = nodeOrg;
	}

}

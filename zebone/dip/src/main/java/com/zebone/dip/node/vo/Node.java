package com.zebone.dip.node.vo;
/**
 * 节点管理配置
 */
public class Node {
	/**主键ID*/
	private String id;
	/**节点描述*/
	private String nodeDesc;
	/**节点状态（启动，停止)*/
	private String nodeState;
	/**节点IP地址*/
	private String nodeIp;
	/**节点名称*/
	private String nodesName;
	/**节点网络状态*/
	private String nodeNet;
	/**节点运行状态（正常，异常）*/
	private String nodeRun;
	/**节点端口号*/
	private String nodePort;
	/**任务删除标志*/
	private String delFlag;

	public String getNodesName() {
		return nodesName;
	}

	public void setNodesName(String nodesName) {
		this.nodesName = nodesName;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getNodeDesc() {
		return nodeDesc;
	}

	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc == null ? null : nodeDesc.trim();
	}

	public String getNodeState() {
		return nodeState;
	}

	public void setNodeState(String nodeState) {
		this.nodeState = nodeState == null ? null : nodeState.trim();
	}

	public String getNodeIp() {
		return nodeIp;
	}

	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp == null ? null : nodeIp.trim();
	}

	public String getNodeNet() {
		return nodeNet;
	}

	public void setNodeNet(String nodeNet) {
		this.nodeNet = nodeNet == null ? null : nodeNet.trim();
	}

	public String getNodeRun() {
		return nodeRun;
	}

	public void setNodeRun(String nodeRun) {
		this.nodeRun = nodeRun == null ? null : nodeRun.trim();
	}

	public String getNodePort() {
		return nodePort;
	}

	public void setNodePort(String nodePort) {
		this.nodePort = nodePort;
	}
}
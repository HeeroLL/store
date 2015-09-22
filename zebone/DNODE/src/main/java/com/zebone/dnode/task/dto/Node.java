package com.zebone.dnode.task.dto;

import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = 4198278333863185434L;
	
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
	
	
	public Node() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeDesc() {
		return nodeDesc;
	}
	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	public String getNodeState() {
		return nodeState;
	}
	public void setNodeState(String nodeState) {
		this.nodeState = nodeState;
	}
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	public String getNodesName() {
		return nodesName;
	}
	public void setNodesName(String nodesName) {
		this.nodesName = nodesName;
	}
	public String getNodeNet() {
		return nodeNet;
	}
	public void setNodeNet(String nodeNet) {
		this.nodeNet = nodeNet;
	}
	public String getNodeRun() {
		return nodeRun;
	}
	public void setNodeRun(String nodeRun) {
		this.nodeRun = nodeRun;
	}
	public String getNodePort() {
		return nodePort;
	}
	public void setNodePort(String nodePort) {
		this.nodePort = nodePort;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
    
	
}
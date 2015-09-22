package com.zebone.dip.etl.vo;

import java.util.Date;

/**
 * 节点和转换关系。
 * @author songjunjie
 *
 */
public class NodeAndTransRule {
	private String nodeId;
	private Integer transId;
	private Date createTime;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getTransId() {
		return transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

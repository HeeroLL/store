package com.zebone.dip.etl.vo;

import java.util.Date;

/**
 * 节点和任务关系。
 * @author songjunjie
 *
 */
public class NodeAndJobRule {
	private String nodeId;
	private Integer jobId;
	private Date createTime;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

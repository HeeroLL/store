package com.zebone.dip.clear.vo;
/**
 * 清洗配置表
 */
public class ClearConf {
	/**主键ID*/
	private String id;
	/**表配置ID*/
	private String tableId;
	/**发布标志*/
	private String deployFlag;
	/**重新清洗标志*/
	private String againFlag;
	/**任务ID*/
	private String taskId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId == null ? null : tableId.trim();
	}

	public String getDeployFlag() {
		return deployFlag;
	}

	public void setDeployFlag(String deployFlag) {
		this.deployFlag = deployFlag == null ? null : deployFlag.trim();
	}

	public String getAgainFlag() {
		return againFlag;
	}

	public void setAgainFlag(String againFlag) {
		this.againFlag = againFlag == null ? null : againFlag.trim();
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId == null ? null : taskId.trim();
	}
}
package com.zebone.dip.task.vo;
/**
 * 任务管理
 */
public class Task {
	/**主键ID*/
	private String id;
	/**任务编码*/
	private String taskCode;
	/**任务描述*/
	private String taskDesc;
	/**任务状态（启动 1，停止 0)*/
	private String taskState;
	/**任务发布状态*/
	private String taskDeploy;
	/**任务执行频率（仅执行一次，每隔多久执行一次 ）*/
	private String taskFreq;
	/**任务执行时间(随任务启动执行，定时执行）*/
	private String taskTime;
	/**任务类型（ETL任务，SQL任务，其他任务等）*/
	private String taskType;
	/**任务发布的前置节点*/
	private String taskNode;
	/**任务删除标志*/
	private String delFlag;
	/**任务定时配置*/
	private String taskFreqSm;

	public String getTaskFreqSm() {
		return taskFreqSm;
	}

	public void setTaskFreqSm(String taskFreqSm) {
		this.taskFreqSm = taskFreqSm;
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

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode == null ? null : taskCode.trim();
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc == null ? null : taskDesc.trim();
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState == null ? null : taskState.trim();
	}

	public String getTaskDeploy() {
		return taskDeploy;
	}

	public void setTaskDeploy(String taskDeploy) {
		this.taskDeploy = taskDeploy == null ? null : taskDeploy.trim();
	}

	public String getTaskFreq() {
		return taskFreq;
	}

	public void setTaskFreq(String taskFreq) {
		this.taskFreq = taskFreq == null ? null : taskFreq.trim();
	}

	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime == null ? null : taskTime.trim();
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType == null ? null : taskType.trim();
	}

	public String getTaskNode() {
		return taskNode;
	}

	public void setTaskNode(String taskNode) {
		this.taskNode = taskNode == null ? null : taskNode.trim();
	}
}
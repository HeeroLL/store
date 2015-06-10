package com.zebone.dnode.task.dto;

import java.io.Serializable;

import com.zebone.dnode.task.enums.TaskType;

/**
 * 任务
 * @author cz
 *
 */
public class Task implements Serializable{

	private static final long serialVersionUID = 3617177851380434784L;
	
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
	private TaskType taskType;
	/**任务发布的前置节点*/
	private String taskNode;
	/** 执行的任务类  **/
	private String taskTarget;
	/**任务删除标志*/
	private String delFlag;
    
	public Task() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getTaskDeploy() {
		return taskDeploy;
	}

	public void setTaskDeploy(String taskDeploy) {
		this.taskDeploy = taskDeploy;
	}

	public String getTaskFreq() {
		return taskFreq;
	}

	public void setTaskFreq(String taskFreq) {
		this.taskFreq = taskFreq;
	}

	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public String getTaskNode() {
		return taskNode;
	}

	public void setTaskNode(String taskNode) {
		this.taskNode = taskNode;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	

	public String getTaskTarget() {
		return taskTarget;
	}

	public void setTaskTarget(String taskTarget) {
		this.taskTarget = taskTarget;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((taskCode == null) ? 0 : taskCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskCode == null) {
			if (other.taskCode != null)
				return false;
		} else if (!taskCode.equals(other.taskCode))
			return false;
		return true;
	}	
	
}
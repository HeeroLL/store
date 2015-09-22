package com.zebone.dnode.task.service;

import java.util.List;

import com.zebone.dnode.task.dto.Node;
import com.zebone.dnode.task.dto.Task;

public interface CenterTaskService {
	
	/**
	 * 获取中心的任务
	 * @return
	 */
	List<Task> getCenterTasks(String taskCode);
	
	/**
	 * 获取任务节点
	 * @return
	 */
	Node getTaskNode();
	

	
	/**
	 * 更新中心任务执行情况
	 * @param desc
	 * @param taskId
	 */
	void updateCenterTaskExecuteStatus(String taskState, String taskId);
}

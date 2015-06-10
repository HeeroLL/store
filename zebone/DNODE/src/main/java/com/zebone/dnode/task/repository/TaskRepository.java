package com.zebone.dnode.task.repository;

import java.util.List;

import com.zebone.dnode.task.dto.Node;
import com.zebone.dnode.task.dto.Task;

/**
 * 任务Repository 
 * @author cz
 *
 */
public interface TaskRepository {
	
	/**
	 * 获取中心任务
	 * @return
	 */
	List<Task> getTasks(String taskCode);
	
	
	/**
	 * 获取任务节点
	 * @return
	 */
    Node getTaskNode();
    
    
	
	/**
	 * 更新任务执行状态
	 * @param desc
	 * @param taskId
	 */
	void updateTaskExecuteStatus(String taskState, String taskId);
}

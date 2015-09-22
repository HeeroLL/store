package com.zebone.dnode.task.service;

import java.util.List;

import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.enums.TaskOp;

public interface TaskService {
	/**
	 * 执行任务操作
	 * @param task
	 * @param op
	 */
	void executeTask(Task task,TaskOp op);
	
	/**
	 * 删除任务集
	 * @param tasks
	 */
	void deleteTasks(List<Task> tasks);
	
	/**
	 * 删除单个任务
	 * @param task
	 */
	void deleteTask(Task task);
}

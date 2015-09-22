package com.zebone.dnode.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.dnode.task.dto.Node;
import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.repository.TaskRepository;
import com.zebone.dnode.task.service.CenterTaskService;

@Service("centerTaskService")
public class CenterTaskServiceImpl implements CenterTaskService {
    
    @Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<Task> getCenterTasks(String taskCode) {
		// TODO Auto-generated method stub
		return taskRepository.getTasks(taskCode);
	}

	@Override
	public void updateCenterTaskExecuteStatus(String taskState, String taskId) {
		// TODO Auto-generated method stub
		taskRepository.updateTaskExecuteStatus(taskState, taskId);
	}

	@Override
	public Node getTaskNode() {
		// TODO Auto-generated method stub
		return taskRepository.getTaskNode();
	}

}

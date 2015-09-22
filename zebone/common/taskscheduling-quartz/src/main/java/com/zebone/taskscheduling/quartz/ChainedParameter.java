package com.zebone.taskscheduling.quartz;

import java.util.List;
import java.util.Map;

public class ChainedParameter {
	
	private Map<String,String> taskChainedMap;
	
	private Map<String,List<JobParameter>> taskMap;

	public Map<String, String> getTaskChainedMap() {
		return taskChainedMap;
	}

	public void setTaskChainedMap(Map<String, String> taskChainedMap) {
		this.taskChainedMap = taskChainedMap;
	}

	public Map<String, List<JobParameter>> getTaskMap() {
		return taskMap;
	}

	public void setTaskMap(Map<String, List<JobParameter>> taskMap) {
		this.taskMap = taskMap;
	}

	
	
}

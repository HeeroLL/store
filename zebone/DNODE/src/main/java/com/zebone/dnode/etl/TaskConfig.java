package com.zebone.dnode.etl;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("taskConfig")
public class TaskConfig {

	@XStreamImplicit(itemFieldName="taskProcess")
	private List<TaskProcess> taskProcess;
	
	@XStreamImplicit(itemFieldName="task")
	private List<Task> task;

	
	public List<TaskProcess> getTaskProcess() {
		return taskProcess;
	}
	

	public void setTaskProcess(List<TaskProcess> taskProcess) {
		this.taskProcess = taskProcess;
	}

	

	public List<Task> getTask() {
		return task;
	}


	public void setTask(List<Task> task) {
		this.task = task;
	}



	public static class TaskProcess{
		 @XStreamAsAttribute
		 private String id;
		 
		 @XStreamAsAttribute
		 private String policy;
		 
		 @XStreamAsAttribute
		 private String startTask;
	     
		 @XStreamImplicit(itemFieldName="taskInstance")
		 private List<TaskInstance> taskInstance;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPolicy() {
			return policy;
		}

		public void setPolicy(String policy) {
			this.policy = policy;
		}

		public String getStartTask() {
			return startTask;
		}

		public void setStartTask(String startTask) {
			this.startTask = startTask;
		}

		public List<TaskInstance> getTaskInstance() {
			return taskInstance;
		}

		public void setTaskInstance(List<TaskInstance> taskInstance) {
			this.taskInstance = taskInstance;
		}
       
	}
	
	
	
	public static class TaskInstance{
		@XStreamAsAttribute
		private String taskId;
		
		@XStreamAsAttribute
		private String pramaMap;

		public String getTaskId() {
			return taskId;
		}

		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}

		public String getPramaMap() {
			return pramaMap;
		}

		public void setPramaMap(String pramaMap) {
			this.pramaMap = pramaMap;
		}
				
	}
	
	@XStreamAlias("task")
	public static class Task{
	    @XStreamAsAttribute
		private String id;
	    
	    @XStreamImplicit(itemFieldName="taskExecute")
	    private List<TaskExecute> taskExecute;
	    
	    @XStreamImplicit(itemFieldName="taskEnd")
	    private List<TaskEnd> taskEnd;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<TaskExecute> getTaskExecute() {
			return taskExecute;
		}

		public void setTaskExecute(List<TaskExecute> taskExecute) {
			this.taskExecute = taskExecute;
		}

		public List<TaskEnd> getTaskEnd() {
			return taskEnd;
		}

		public void setTaskEnd(List<TaskEnd> taskEnd) {
			this.taskEnd = taskEnd;
		}
	    	
	}
	
	
	public static class TaskExecute{
		@XStreamAlias("class")
		@XStreamAsAttribute
		private String execlass;
		
		@XStreamAsAttribute
		private String pramaMap;

		public String getExeclass() {
			return execlass;
		}

		public void setExeclass(String execlass) {
			this.execlass = execlass;
		}

		public String getPramaMap() {
			return pramaMap;
		}

		public void setPramaMap(String pramaMap) {
			this.pramaMap = pramaMap;
		}
			
	}
	
	public static class TaskEnd{
		@XStreamAsAttribute
		private String taskStart;

		public String getTaskStart() {
			return taskStart;
		}

		public void setTaskStart(String taskStart) {
			this.taskStart = taskStart;
		}
		
	}
}

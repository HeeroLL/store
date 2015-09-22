package com.zebone.dnode.task.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebone.dnode.task.dto.Node;
import com.zebone.dnode.task.dto.Task;
import com.zebone.dnode.task.enums.TaskOp;
import com.zebone.dnode.task.enums.TaskType;
import com.zebone.dnode.task.service.CenterTaskService;
import com.zebone.dnode.task.service.TaskService;
import com.zebone.dnode.task.util.Cache;
import com.zebone.dnode.task.util.CacheKey;
import com.zebone.dnode.task.util.TaskQuartzUtil;
import com.zebone.dnode.task.util.TaskKey;




/**
 * 获取采集任务job
 * @author cz
 *
 */
public class GetCenterTaskJob implements Job {
	
	public static final Logger logger = LoggerFactory.getLogger(GetCenterTaskJob.class);
	

    private CenterTaskService centerTaskService;
    
    private TaskService sqlTaskService;
    
    private TaskService etlTaskService;
    
    private TaskService javaTaskService;
   
    private TaskService otherTaskService;
    
    private TaskService testTaskService;
    
    private TaskService cleanTaskService;
    
    private Cache cache;
    
    private String nodeName;
    
    private Scheduler sch;
        
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		Node node = centerTaskService.getTaskNode();
		/** 判断节点是否启动 **/
		if(node == null){
			logger.info("中心没有" + nodeName + "节点");
		}else if( "1".equals(node.getNodeState()) && "1".equals(node.getDelFlag())){
			logger.info("节点"+nodeName+"的状态为启动");
			List<Task> tnewTasks = centerTaskService.getCenterTasks(node.getId());
			List<Task> oldTasks = (List<Task>) cache.get(CacheKey.TASK_KEY);
			List<Task> newTasks = new ArrayList<Task>(tnewTasks);			
			
			if(tnewTasks != null && tnewTasks.size() > 0){
				logger.info("节点" +nodeName + "开始获取任务总数:" + newTasks.size());
				if(oldTasks != null && oldTasks.size() > 0){
					for(Task nTask : tnewTasks){	
						TaskService ts  = getTaskService(nTask.getTaskType());
						/** 停止任务  **/
						if(nTask.getTaskDeploy() == null || "0".equals(nTask.getTaskDeploy()) || "0".equals(nTask.getDelFlag())){
							if(TaskQuartzUtil.containsTask(nTask, oldTasks)){
								logger.info("停止任务:"+nTask.getTaskCode());
								ts.deleteTask(nTask);
								oldTasks.remove(nTask);
							}		
							newTasks.remove(nTask);
							continue;
						}
					    if(TaskQuartzUtil.containsTask(nTask, oldTasks)){
					    	int chage = TaskQuartzUtil.isTaskChange(nTask, oldTasks);
							if(chage == 2){
								/** 更新任务 **/
								logger.info("更新任务(任务类型改变):"+nTask.getTaskCode());
								ts.deleteTask(nTask);
								ts.executeTask(nTask, TaskOp.ADD);
							}else if(chage == 1){
								logger.info("更新任务(任务执行周期改变):"+nTask.getTaskCode());
								ts.executeTask(nTask, TaskOp.UPDATE);
							}
						}else{							
							/** 新建任务 **/
							logger.info("新建任务:"+nTask.getTaskCode());
							ts.executeTask(nTask, TaskOp.ADD);	
						}
					}
					
					/** 删除不需要执行的任务 **/
					List<Task> delTask = TaskQuartzUtil.getNotContainsTasks(oldTasks,newTasks);
					if(delTask != null && delTask.size() > 0){
						testTaskService.deleteTasks(delTask);
					}
					
				}else{
					for(Task nTask : tnewTasks){
						TaskService ts  = getTaskService(nTask.getTaskType());
						if(nTask.getTaskDeploy() == null || "0".equals(nTask.getTaskDeploy()) || "0".equals(nTask.getDelFlag())){
							newTasks.remove(nTask);
						}else{
							logger.info("新建任务:"+nTask.getTaskCode());
							ts.executeTask(nTask, TaskOp.ADD);
						}	
					}
				}
				cache.put(CacheKey.TASK_KEY, newTasks);
			}else{
				logger.info("节点" +nodeName + "开始获取任务总数:0" );
			}
		}else{
			logger.info("节点"+nodeName+"的状态为停止或者删除");
			try {
				TaskQuartzUtil.delJob(sch, TaskKey.TASK_GROUP);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
			cache.evict(CacheKey.TASK_KEY);
		}
		
	}
   
    private TaskService getTaskService(TaskType taskType){
    	switch (taskType) {
		case ETL:
			return etlTaskService;
		case SQL:
			return sqlTaskService;
		case JAVA:
		    return javaTaskService;
		case OTHER:
			return otherTaskService;
		case CLEAN:
			return cleanTaskService;
		default:
			return testTaskService;
		}
    }
    
	public void setCenterTaskService(CenterTaskService centerTaskService) {
		this.centerTaskService = centerTaskService;
	}

	public void setSqlTaskService(TaskService sqlTaskService) {
		this.sqlTaskService = sqlTaskService;
	}

	public void setEtlTaskService(TaskService etlTaskService) {
		this.etlTaskService = etlTaskService;
	}
    
	public void setJavaTaskService(TaskService javaTaskService) {
		this.javaTaskService = javaTaskService;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public void setOtherTaskService(TaskService otherTaskService) {
		this.otherTaskService = otherTaskService;
	}

	public void setSch(Scheduler sch) {
		this.sch = sch;
	}

	public void setTestTaskService(TaskService testTaskService) {
		this.testTaskService = testTaskService;
	}

	public void setCleanTaskService(TaskService cleanTaskService) {
		this.cleanTaskService = cleanTaskService;
	}
	
	
	

}

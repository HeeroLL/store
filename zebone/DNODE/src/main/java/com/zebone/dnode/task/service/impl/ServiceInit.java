package com.zebone.dnode.task.service.impl;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.zebone.dnode.task.job.GetCenterTaskJob;
import com.zebone.dnode.task.service.CenterTaskService;
import com.zebone.dnode.task.service.TaskService;
import com.zebone.dnode.task.util.Cache;
import com.zebone.dnode.task.util.DNJobKey;
import com.zebone.dnode.task.util.TaskKey;


/**
 * 获取中心任务
 * 
 * @author cz
 * 
 */
public class ServiceInit {
	
	private final static Logger logger = LoggerFactory.getLogger(ServiceInit.class);
	
    @Autowired
	private Scheduler sch;
    
    @Autowired
    private Cache cache;
	
    @Autowired
	private CenterTaskService centerTaskService;
    
    @Autowired @Qualifier("etlTaskService")
    private TaskService etlTaskService;
    
    @Autowired @Qualifier("sqlTaskService")
    private TaskService sqlTaskService;
    
    @Autowired @Qualifier("javaTaskService")
    private TaskService javaTaskService;
    
    @Autowired @Qualifier("otherTaskService")
    private TaskService otherTaskService;
    
    @Autowired @Qualifier("testTaskService")
    private TaskService testTaskService;
    
    @Autowired @Qualifier("cleanTaskService")
    private TaskService cleanTaskService;
	
//	@Value("#{syspt['node.name']}")
	private String nodeName;
   
	public void init() {
	    boolean startSucess = true;
		JobDetail jd = JobBuilder.newJob(GetCenterTaskJob.class)
				.withIdentity(TaskKey.GET_TASK, TaskKey.GET_TASK_GROUP).build();
		
		setJobMapPara(jd.getJobDataMap());
		
		Trigger jdt = TriggerBuilder
				.newTrigger()
				.withIdentity(TaskKey.GET_TASK_TRIGGER,TaskKey.GET_TASK_TRIGGER_GROUP)
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
				.build();
		try {
			sch.scheduleJob(jd,jdt);
			sch.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.error("启动中心任务轮询失败!", e);
			startSucess = false;
		}
		if(startSucess){
			logger.info("######## 启动中心任务轮询成功 ########");
		}
	}
	
	private void setJobMapPara(JobDataMap jdm){
	    jdm.put(DNJobKey.NODE_NAME, nodeName);
	    jdm.put(DNJobKey.CACHE, cache);
        jdm.put(DNJobKey.Scheduler, sch);
	    
		jdm.put(DNJobKey.CENTER_TASK_SERVICE, centerTaskService);
		
        jdm.put(DNJobKey.ETL_TASK_SERVICE, etlTaskService);
        jdm.put(DNJobKey.SQL_TASK_SERVICE, sqlTaskService);     
        jdm.put(DNJobKey.JAVA_TASK_SERVICE, javaTaskService);
        jdm.put(DNJobKey.OTHER_TASK_SERVICE, otherTaskService);
        jdm.put(DNJobKey.TEST_TASK_SERVICE, testTaskService);
        jdm.put(DNJobKey.CLEAN_TASK_SERVICE, cleanTaskService);
	}
}

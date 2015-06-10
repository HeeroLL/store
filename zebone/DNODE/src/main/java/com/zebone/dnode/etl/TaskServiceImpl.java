package com.zebone.dnode.etl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zebone.btp.core.AppContext;
import com.zebone.dnode.etl.TaskConfig.Task;
import com.zebone.dnode.etl.TaskConfig.TaskEnd;
import com.zebone.dnode.etl.TaskConfig.TaskExecute;
import com.zebone.dnode.etl.TaskConfig.TaskInstance;
import com.zebone.dnode.etl.TaskConfig.TaskProcess;
import com.zebone.taskscheduling.quartz.ChainedParameter;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	   
	private static final Log log = LogFactory.getLog(TaskServiceImpl.class);
     
	
	private JobDataMap parseParameter(String para){
	     JobDataMap jdm = new JobDataMap();
	     if(StringUtils.isNotEmpty(para)){
	    	String[] iparas = para.split("#");
	    	for(String ipara : iparas){
	    		 String[] beanPara = ipara.split(":");
	    		 if(AppContext.getApplicationContext().containsBean(beanPara[1])){
	    			 Object beanValue = AppContext.getApplicationContext().getBean(beanPara[1]);
	    			 jdm.put(beanPara[0], beanValue);
	    		 }else{
	    			 jdm.put(beanPara[0], beanPara[1]);
	    		 }
	    	}
	     }
	     return jdm;
	}
	

	@Override
	public void taskStart() {
		// TODO Auto-generated method stub
		try {
			ClassPathResource task = new ClassPathResource("com/zebone/dnode/etl/task.xml");
			XStream xs = new XStream(new StaxDriver());
			xs.processAnnotations(TaskConfig.class);
			TaskConfig taskConfig = (TaskConfig) xs.fromXML(task.getInputStream());
			
			Map<String,List<JobParameter>> taskMap = new HashMap<String, List<JobParameter>>();
			/** 当前任务执行完之后需要执行的任务  **/
			Map<String,String> nextTaskMap = new HashMap<String, String>();
			Map<String,String> taskInstanceParaMap = new HashMap<String, String>();
			
			List<JobParameter> runJobParameter = new ArrayList<JobParameter>();
			
			List<Task> taskList = taskConfig.getTask();
            
			
			/** 获取执行任务  **/
			if(taskList != null && taskList.size() > 0){
				for(Task tk : taskList){
					List<TaskExecute> teList= tk.getTaskExecute();
					if(teList != null && teList.size() > 0){
						List<JobParameter> jpList = new ArrayList<JobParameter>();
					    for(TaskExecute te:teList){
					    	JobParameter jp = new JobParameter();
					    	String exeClass = te.getExeclass();
					    	Class<?> jobClass = ClassUtils.getClass(exeClass);
					    	Job job =(Job)jobClass.newInstance();
					    	JobDataMap jdm = parseParameter(te.getPramaMap());
					    	jdm.put("taskId", tk.getId());
					    	jp.setJobClass(job.getClass());
					    	jp.setJobDataMap(jdm);
					    	jpList.add(jp);
					    }
						taskMap.put(tk.getId(), jpList);
					}
					
					List<TaskEnd> taskEndList = tk.getTaskEnd();
					if(taskEndList != null && taskEndList.size() > 0){
						String taskIds = "";
						for(TaskEnd te : taskEndList){
							taskIds = te.getTaskStart() +":";
						}
						nextTaskMap.put(tk.getId(), taskIds.substring(0, taskIds.lastIndexOf(":")));
					}
				}
			}
			
			List<TaskProcess> taskProcessList = taskConfig.getTaskProcess();
			if(taskProcessList != null && taskProcessList.size() > 0){
				ChainedParameter cp  = new ChainedParameter();
				cp.setTaskMap(taskMap);
				cp.setTaskChainedMap(nextTaskMap);
				for(TaskProcess tp : taskProcessList){
					/** 初始化taskInstance 获取 taskInstance中的参数   **/
					List<TaskInstance> taskInstanceList = tp.getTaskInstance();
			        if(taskInstanceList != null && taskInstanceList.size() > 0){
			            for(TaskInstance ti : taskInstanceList)	{
			            	taskInstanceParaMap.put(ti.getTaskId(),ti.getPramaMap());
			            }
			        }
			        
					String tpId = tp.getId();
					String policy = tp.getPolicy();
					String startTask = tp.getStartTask();
					List<JobParameter> jpList = taskMap.get(startTask);
					if(jpList !=null && jpList.size() > 0){
						for(int i=0,j=jpList.size(); i < j; i++){
							JobParameter jp = jpList.get(i);
							jp.setJobKey(JobKey.jobKey(startTask+"_"+i,tpId));
							jp.setTriggerKey(TriggerKey.triggerKey(startTask+"_"+i,tpId));
							if(StringUtils.isNotEmpty(policy)){
								jp.setScheduleBuilder(QuartzUtil.cronSchedule(policy));
							}
							jp.getJobDataMap().put("chainedParameter", cp);
							jp.getJobDataMap().put("taskProcessId", tpId);
							jp.getJobDataMap().putAll(parseParameter(taskInstanceParaMap.get(startTask)));
							runJobParameter.add(jp);
						}
					}
			        
				}
			}
			
			for(JobParameter jp : runJobParameter){
				QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
				quartzManager.addTask(jp);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);		
		}
	}
	
	public static void main(String[] args){
		new TaskServiceImpl().taskStart();
	}

}

package com.zebone.taskscheduling.quartz;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 *  任务链job
 * @author 陈阵 
 * @date 2014-2-17 上午9:30:54
 */
public class SequenceJob {
	
	/**
	 * 是否执行任务链
	 */
	private boolean chained = true;
	
	private ChainedParameter chainedParameter;

	
	public void execteChained(JobExecutionContext jec){
		if(chained){
			String taskProcessId = jec.getJobDetail().getJobDataMap().getString("taskProcessId");
			String taskId = jec.getJobDetail().getJobDataMap().getString("taskId");
			QuartzManager qm =  QuartzManager.getSchedulerFactory();
			String nextTaskId = chainedParameter.getTaskChainedMap().get(taskId);

			if(StringUtils.isNotEmpty(nextTaskId)){
				String[] nextTaskIds = null;
				if(nextTaskId.indexOf(":")== -1){
					nextTaskIds = new String[]{nextTaskId};
				}else if(nextTaskId.indexOf(":") != -1){
					nextTaskIds = nextTaskId.split(":");
				}
				for(String ntid:nextTaskIds){
					List<JobParameter> jpList = chainedParameter.getTaskMap().get(ntid);
					if(jpList != null && jpList.size() > 0){
						for(int i=0,size=jpList.size(); i < size; i++){
							JobParameter jp = jpList.get(i);
							jp.setJobKey(JobKey.jobKey(ntid+"_"+i,taskProcessId));
							jp.setTriggerKey(TriggerKey.triggerKey(ntid+"_"+i,taskProcessId));
							JobDataMap jdm =  jp.getJobDataMap();
							jdm.put("taskId",nextTaskId);
							jdm.put("taskProcessId", taskProcessId);
							jdm.put("chainedParameter", chainedParameter);
							qm.addTask(jp);
						}
					}
				}
			
			}
		}
	}


	public void setChained(boolean chained) {
		this.chained = chained;
	}



	public void setChainedParameter(ChainedParameter chainedParameter) {
		this.chainedParameter = chainedParameter;
	}
  
	

    

   
}

package com.zebone.dnode.etl.load.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.dnode.etl.load.pojo.LoadConfig;
import com.zebone.dnode.etl.load.service.LoadService;
import com.zebone.taskscheduling.quartz.SequenceJob;


/**
 * 加载任务
 * @author 陈阵 
 * @date 2014-2-26 下午1:30:05
 */
public class LoadJob extends SequenceJob implements Job {

    private static final Log log = LogFactory.getLog(LoadJob.class);

    private LoadService loadService;
    
    private LoadConfig  loadConfig;

	public void setLoadService(LoadService loadService) {
		this.loadService = loadService;
	}

	public void setLoadConfig(LoadConfig loadConfig) {
		this.loadConfig = loadConfig;
	}

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			log.info("############# 装载任务开始 #############");
			loadService.loadData(loadConfig);
			log.info("############# 装载任务结束 #############");
			execteChained(jec);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}

	}
      
}

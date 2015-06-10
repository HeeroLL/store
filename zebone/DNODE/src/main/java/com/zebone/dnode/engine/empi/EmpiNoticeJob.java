package com.zebone.dnode.engine.empi;

import com.zebone.dnode.engine.empi.service.DispatchService;
import org.quartz.Job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主索引注册通知任务
 *
 * @author 杨英
 * @version 2013-10-30 上午10:40
 */
@DisallowConcurrentExecution
public class EmpiNoticeJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(EmpiNoticeJob.class);

    private DispatchService dispatchService;

    public void setDispatchService(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	try{
    		dispatchService.doDispatch();
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		e.printStackTrace();
    	}
        
    }
}

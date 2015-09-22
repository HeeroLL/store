package com.zebone.dnode.etl.check.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.dnode.etl.check.pojo.CheckConfig;
import com.zebone.dnode.etl.check.service.CheckService;
import com.zebone.taskscheduling.quartz.SequenceJob;

/**
 * 数据验证任务
 *
 * @author 杨英
 * @version 2014-02-13 下午02:47
 */
public class CheckJob extends SequenceJob implements Job {

    private static final Log log = LogFactory.getLog(CheckJob.class);

    private CheckService checkService;

    private CheckConfig checkConfig;
    
    private String orgCode;
    
    private String startTime;
    
    private String endTime;

    public void setCheckService(CheckService checkService) {
        this.checkService = checkService;
    }

    public void setCheckConfig(CheckConfig checkConfig) {
        this.checkConfig = checkConfig;
    }
    
    
    public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("############ 检查任务开始 ##################");
        try{
            Map<String,String> oMap = new HashMap<String,String>();
            oMap.put("orgCode",orgCode);
            oMap.put("startTime",startTime);
            oMap.put("endTime",endTime);
            checkService.checkSourceData(checkConfig,oMap);
            log.info("####### 检查任务结束 ##############");
            execteChained(jobExecutionContext);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}

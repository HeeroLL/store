package com.zebone.dnode.etl.convert.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.dnode.etl.convert.pojo.ConvertConfig;
import com.zebone.dnode.etl.convert.service.ConvertService;
import com.zebone.taskscheduling.quartz.SequenceJob;

/**
 * 数据清洗转换任务
 *
 * @author 杨英
 * @version 2014-02-18 上午08:48
 */
public class ConvertJob extends SequenceJob implements Job {

    private static final Log log = LogFactory.getLog(ConvertJob.class);

    private ConvertService convertService;

    private ConvertConfig convertConfig;
    
    private String orgCode;
    
    private String startTime;
    
    private String endTime;
    
    public void setConvertService(ConvertService convertService) {
        this.convertService = convertService;
    }

    public void setConvertConfig(ConvertConfig convertConfig) {
        this.convertConfig = convertConfig;
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
        try{
            log.info("################ 清洗转化任务开始 ###############");
            Map<String,String> oMap = new HashMap<String,String>();
            System.out.println("convert orgCode :"+orgCode);
            oMap.put("orgCode",orgCode);
            oMap.put("startTime",startTime);
            oMap.put("endTime",endTime);
            convertService.convertData(convertConfig,oMap);
            log.info("################ 清洗转化任务结束  ###############");
            execteChained(jobExecutionContext);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}

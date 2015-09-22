package com.zebone.dnode.engine.analyze;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.analyze.service.DocumentAnalyzeService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.util.DateUtil;

/**
 * 文档解析任务启动器
 * @author songjunjie
 * @version 2013-8-17 上午09:55:40
 */

@Service("documentAnalyzeJobStarter")
public class DocumentAnalyzeJobStarter {
	
	private static final Log log = LogFactory.getLog(DocumentAnalyzeJobStarter.class);
	
	@Resource
	private DocumentAnalyzeService documentAnalyzeService;
	
	@Value("${orgcode}")
	private String orgCode;

    //解析线程总数
    @Value("${thread_count}")
    private String analyzeThreads;
    //线程编号列表
    @Value("${thread_no}")
    private String THREAD_NO;
	
	public void startJob(){
        String date = DateUtil.getCurrentDate();
        log.info("######解析引擎启动######" + THREAD_NO);
        QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
        String[] threadNoList = THREAD_NO.split(",");
        if (threadNoList != null && threadNoList.length > 0) {
            for (int i = 0; i < threadNoList.length; i++) {
                JobParameter jobParameter = new JobParameter();
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("documentAnalyzeService", documentAnalyzeService);
                jobDataMap.put("analyzeThreads", analyzeThreads);
                jobDataMap.put("threadNo",threadNoList[i]);
                jobParameter.setJobClass(DocumentAnalyzeJob.class);
                jobParameter.setJobKey(JobKey.jobKey(date, "analyze_jk"));
                jobParameter.setTriggerKey(TriggerKey.triggerKey(date, "analyze_tr"));
                jobParameter.setJobDataMap(jobDataMap);
                quartzManager.addTask(jobParameter);
            }
        }

		
	}
}

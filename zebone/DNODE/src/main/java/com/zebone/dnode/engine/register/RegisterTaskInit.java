package com.zebone.dnode.engine.register;

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

import com.zebone.dnode.engine.register.job.RegisterJob;
import com.zebone.dnode.engine.register.service.RegisterService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.util.DateUtil;

/**
 * 启动校验引擎工具类
 * 
 * @author 陈阵
 * @date 2013-8-16 上午10:26:43
 */
@Service("registerTaskInit")
public class RegisterTaskInit {

	private static final Log log = LogFactory.getLog(RegisterTaskInit.class);

	@Value("${orgcode}")
	private String orgCode;
	
	//解析线程总数
    @Value("${thread_count}")
    private String analyzeThreads;
    //线程编号列表
    @Value("${thread_no}")
    private String THREAD_NO;

	@Resource
	private RegisterService registerService;

	public void init() {
		String date = DateUtil.getCurrentDate();
		QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
//		if (StringUtils.isEmpty(orgCode)) {
		String[] threadNoList = THREAD_NO.split(",");
        if (threadNoList != null && threadNoList.length > 0) {
            for (int i = 0; i < threadNoList.length; i++) {
			JobParameter jobParameter = new JobParameter();
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("registerService", registerService);
			jobDataMap.put("analyzeThreads", analyzeThreads);
            jobDataMap.put("threadNo",threadNoList[i]);
			jobParameter.setJobClass(RegisterJob.class);
			jobParameter.setJobKey(JobKey.jobKey(date, "jk_register_task"));
			jobParameter.setTriggerKey(TriggerKey.triggerKey(date,"tr_register_task"));
			jobParameter.setJobDataMap(jobDataMap);
			quartzManager.addTask(jobParameter);
            }
        }

			log.info("######注册引擎启动######");
//		} else {
//			String[] orgCodes = orgCode.split(",");
//			if (ArrayUtils.isNotEmpty(orgCodes)) {
//				for (String tOrgCode : orgCodes) {
//					JobParameter jobParameter = new JobParameter();
//
//					JobDataMap jobDataMap = new JobDataMap();
//					jobDataMap.put("registerService", registerService);
//					jobDataMap.put("docOrgCode", tOrgCode);
//
//					jobParameter.setJobClass(RegisterJob.class);
//
//					jobParameter.setJobKey(JobKey.jobKey(date+"_"+tOrgCode,"jk_register_task"));
//					jobParameter.setTriggerKey(TriggerKey.triggerKey(date+"_"+tOrgCode,"tr_register_task"));
//					jobParameter.setJobDataMap(jobDataMap);
//					quartzManager.addTask(jobParameter);
//				}
//				log.info("######注册引擎启动######");
//			} else {
//				log.info("######注册引擎启动失败:机构编码格式不正确以,分割######");
//			}
//		}

	}
}
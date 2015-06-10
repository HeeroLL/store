package com.zebone.dnode.engine.store;

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

import com.zebone.dnode.engine.store.job.StorageJob;
import com.zebone.dnode.engine.store.service.DocumentStoreService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.util.DateUtil;

@Service("storageTaskInit")
public class StorageTaskInit {

	private static final Log log = LogFactory.getLog(StorageTaskInit.class);

	@Resource
	private DocumentStoreService documentStoreService;
    
	@Value("${orgcode}")
	private String orgCode;
	
	//解析线程总数
    @Value("${thread_count}")
    private String analyzeThreads;
    //线程编号列表
    @Value("${thread_no}")
    private String THREAD_NO;

	public void init() {
		QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
		String[] threadNoList = THREAD_NO.split(",");
		String date = DateUtil.getCurrentDate();
		if (StringUtils.isEmpty(orgCode)) {
			for (int i = 0; i < threadNoList.length; i++) {
				JobParameter jobParameter = new JobParameter();
	
				JobDataMap jobDataMap = new JobDataMap();
				jobDataMap.put("documentStoreService", documentStoreService);
				jobDataMap.put("analyzeThreads", analyzeThreads);
	            jobDataMap.put("threadNo",threadNoList[i]);
				jobParameter.setJobClass(StorageJob.class);
				jobParameter.setJobKey(JobKey.jobKey(date,"jk_storage_task"));
				jobParameter.setTriggerKey(TriggerKey.triggerKey(date,"tr_storage_task"));
				jobParameter.setJobDataMap(jobDataMap);
	
				quartzManager.addTask(jobParameter);
				log.info("######存储引擎启动######");
			}
		}else{
			String[] orgCodes = orgCode.split(",");
			if(ArrayUtils.isNotEmpty(orgCodes)){
				for(String tOrgCode : orgCodes){
					JobParameter jobParameter = new JobParameter();
					JobDataMap jobDataMap = new JobDataMap();
					jobDataMap.put("documentStoreService", documentStoreService);
					jobDataMap.put("docOrgCode", tOrgCode);
					jobParameter.setJobClass(StorageJob.class);
					jobParameter.setJobKey(JobKey.jobKey(date+"_"+tOrgCode,"jk_storage_task"));
					jobParameter.setTriggerKey(TriggerKey.triggerKey(date+"_"+tOrgCode,"tr_storage_task"));
					jobParameter.setJobDataMap(jobDataMap);

					quartzManager.addTask(jobParameter);
				}
				log.info("######存储引擎启动######");
			}else{
				log.info("######存储引擎启动失败:机构编码格式不正确以,分割######");
			}
		}
		
	}
}

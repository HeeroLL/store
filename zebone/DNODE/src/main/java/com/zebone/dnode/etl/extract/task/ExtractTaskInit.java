package com.zebone.dnode.etl.extract.task;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.core.io.ClassPathResource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zebone.dnode.etl.extract.job.ExtractJob;
import com.zebone.dnode.etl.extract.pojo.ExtractConfig;
import com.zebone.dnode.etl.extract.service.ExtractService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;

public class ExtractTaskInit {

	private static final Log log = LogFactory.getLog(ExtractTaskInit.class);
	
	@Resource
	private ExtractService extractService;

	public void init() {
		try {
			log.info("######抽取引擎启动######");

			ClassPathResource node = new ClassPathResource("com/zebone/dnode/etl/extract/config/node.xml");
			XStream xs = new XStream(new StaxDriver());
			xs.processAnnotations(ExtractConfig.class);
			ExtractConfig extractConfig = (ExtractConfig) xs.fromXML(node.getInputStream());
			
			
			QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
			JobParameter jobParameter = new JobParameter();
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("extractService", extractService);
			jobDataMap.put("extractConfig", extractConfig);

			jobParameter.setJobClass(ExtractJob.class);
			jobParameter.setJobKey(JobKey.jobKey("1","jk_extract_task"));
			jobParameter.setTriggerKey(TriggerKey.triggerKey("1","tr_extract_task"));
			//jobParameter.setScheduleBuilder(QuartzUtil.cronSchedule(extractConfig.getNodePolicy()));
			jobParameter.setJobDataMap(jobDataMap);
			
			quartzManager.addTask(jobParameter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}

	}

}

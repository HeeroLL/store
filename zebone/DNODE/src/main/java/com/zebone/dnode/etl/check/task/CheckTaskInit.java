package com.zebone.dnode.etl.check.task;

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
import com.zebone.dnode.etl.check.job.CheckJob;
import com.zebone.dnode.etl.check.pojo.CheckConfig;
import com.zebone.dnode.etl.check.service.CheckService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;

/**
 * 数据验证服务启动类
 *
 * @author 杨英
 * @version 2014-02-13 下午02:43
 */
public class CheckTaskInit {

    private static final Log log = LogFactory.getLog(CheckTaskInit.class);

    @Resource
    private CheckService checkService;

    public void init() {
        try {
            log.info("######数据验证启动######");

            ClassPathResource checkConf = new ClassPathResource("com/zebone/dnode/etl/check/config/checkConf.xml");
            XStream xs = new XStream(new StaxDriver());
            xs.processAnnotations(CheckConfig.class);
            CheckConfig checkConfig = (CheckConfig) xs.fromXML(checkConf.getInputStream());


            QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
            JobParameter jobParameter = new JobParameter();
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("checkService", checkService);
            jobDataMap.put("checkConfig", checkConfig);

            jobParameter.setJobClass(CheckJob.class);
            jobParameter.setJobKey(JobKey.jobKey("1", "jk_check_task"));
            jobParameter.setTriggerKey(TriggerKey.triggerKey("1", "tk_check_task"));
            //jobParameter.setScheduleBuilder(QuartzUtil.cronSchedule(extractConfig.getNodePolicy()));
            jobParameter.setJobDataMap(jobDataMap);

            quartzManager.addTask(jobParameter);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

    }
}

package com.zebone.dnode.etl.convert.task;

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
import com.zebone.dnode.etl.convert.job.ConvertJob;
import com.zebone.dnode.etl.convert.pojo.ConvertConfig;
import com.zebone.dnode.etl.convert.service.ConvertService;
import com.zebone.taskscheduling.quartz.JobParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;

/**
 * 数据清洗转换服务启动类
 *
 * @author 杨英
 * @version 2014-02-18 上午09:58
 */
public class ConvertTaskInit {

    private static final Log log = LogFactory.getLog(ConvertTaskInit.class);

    @Resource
    private ConvertService convertService;

    public void init() {
        try {
            log.info("######数据清洗转换任务启动######");

            ClassPathResource checkConf = new ClassPathResource("com/zebone/dnode/etl/convert/config/convertConf.xml");
            XStream xs = new XStream(new StaxDriver());
            xs.processAnnotations(ConvertConfig.class);
            ConvertConfig convertConfig = (ConvertConfig) xs.fromXML(checkConf.getInputStream());

            QuartzManager quartzManager = QuartzManager.getSchedulerFactory();
            JobParameter jobParameter = new JobParameter();
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("convertService", convertService);
            jobDataMap.put("convertConfig", convertConfig);

            jobParameter.setJobClass(ConvertJob.class);
            jobParameter.setJobKey(JobKey.jobKey("1", "jk_convert_task"));
            jobParameter.setTriggerKey(TriggerKey.triggerKey("1", "tk_convert_task"));
            //jobParameter.setScheduleBuilder(QuartzUtil.cronSchedule(extractConfig.getNodePolicy()));
            jobParameter.setJobDataMap(jobDataMap);

            quartzManager.addTask(jobParameter);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

    }

}

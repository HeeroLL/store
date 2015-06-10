package com.zebone.pubsub.server.service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.zebone.pubsub.server.FreqUtil;
import com.zebone.pubsub.server.job.DcDataJob;
import com.zebone.pubsub.server.pojo.DcDataJobParameter;
import com.zebone.pubsub.server.pojo.RunPara;
import com.zebone.taskscheduling.quartz.MultiCycleJobParameter;
import com.zebone.taskscheduling.quartz.MultiCycleJobParameter.TriggerParameter;
import com.zebone.taskscheduling.quartz.QuartzManager;
import com.zebone.taskscheduling.quartz.util.QuartzUtil;
import com.zebone.util.DateUtil;


@Service("basePubSubServiceInit")
public class BasePubSubServiceInit implements PubSubInitService {
	
	private static final Log log = LogFactory.getLog(BasePubSubServiceInit.class);
     
	@Resource(name="jdbcTemplateDIP")
	private JdbcTemplate jdbcTemplateDip;
	
	
	@Resource(name="dcPubSubService")
    private PubSubService<DcDataJobParameter> pubSubService; 
	
	@Resource
	private CacheManager cacheManager;
	
    private final Map<String,Class<? extends Job>> jobMap = new HashMap<String, Class<? extends Job>>();
    
    private final Map<String,MultiCycleJobParameter> multiCycleJobParameterMap = new HashMap<String, MultiCycleJobParameter>();
    
    private final Map<String,Boolean> hasCycleJobParameterMap = new HashMap<String, Boolean>();
    
    
	
	void initJobMap(){
		jobMap.put("1", DcDataJob.class);
	}
	

	@Override
	public void init() {
       try{
			initJobMap();
			
			List<JobKey> runJob = new ArrayList<JobKey>();
			String sql = "SELECT A.ID_,A.IS_HISTORY,A.IS_CURRENT,A.BEGIN_DATE,A.END_DATE,A.SUB_FREQU,A.IS_HISTORY_RUN,B.SERVICE_NAME,B.SERVICE_CODE,B.SERVICE_TYPE,C.NODE_NAME,D.MAIN_ORG_CODE FROM  P_SUBSCRIBE_SERVICE A LEFT JOIN P_SERVICE_REGISTER B ON A.SERVICE_ID = B.ID_ LEFT JOIN P_NODE_REGISTER C ON  A.ORG_CODE = C.NODE_ORG LEFT JOIN P_ORG_MAPPING D ON A.ORG_CODE = D.DIP_ORG_CODE WHERE C.NODE_STATE = 1 AND B.SERVICE_STATE = 1";
			List<RunPara> runParaList = jdbcTemplateDip.query(sql, new PubSubRunParaRowMapper());
					
			QuartzManager qm = QuartzManager.getSchedulerFactory();
			
			if (runParaList != null && runParaList.size() > 0) {
				for (RunPara rp : runParaList) {
					String serviceType = rp.getServiceType();
					String serviceCode = rp.getServiceCode();
					String serviceName = rp.getServiceName();
					String nodeName = rp.getNodeName();
					String orgCode = rp.getMainOrgCode();
					String pubSubServiceID = rp.getPubSubServiceID();
					
					String isCurrent = rp.getIsCurrent();
					String isHistory = rp.getIsHistory();
					String isHistoryRun = rp.getIsHistoryRun();
					String subFrequ = FreqUtil.getFre(rp.getSubFrequ());

					String cycleJobParameterKey = orgCode + "_" + serviceCode;
					Boolean hasCycleJobParameter = hasCycleJobParameterMap
							.get(cycleJobParameterKey);
					MultiCycleJobParameter mcjp = null;

					if (hasCycleJobParameter != null && hasCycleJobParameter) {
						mcjp = multiCycleJobParameterMap.get(cycleJobParameterKey);
					} else {
						mcjp = new MultiCycleJobParameter();
						JobKey jobKey = JobKey.jobKey(nodeName+"_"+serviceName, orgCode+"_"+serviceCode);
						mcjp.setJobKey(jobKey);
						mcjp.setJobClass(jobMap.get(serviceType));
						multiCycleJobParameterMap.put(cycleJobParameterKey,mcjp);
						hasCycleJobParameterMap.put(cycleJobParameterKey, true);
					}

				
					if ("1".equals(isHistoryRun) && "1".equals(isHistory)) {
						TriggerParameter tp = new TriggerParameter();
        				tp.setTriggerKey(TriggerKey.triggerKey("历史数据(执行一次)", orgCode));
        				JobDataMap jd = new JobDataMap();
        				if("1".equals(serviceType)){
        					DcDataJobParameter ddjp = new DcDataJobParameter();
        					ddjp.setOrgCode(orgCode);
        					ddjp.setBeginDate(rp.getBeginDate()+"T000000");
        					ddjp.setEndDate(rp.getEndDate()+"T240000");
        					ddjp.setDocTypeCode(serviceCode);
        					ddjp.setPubSubserviceId(pubSubServiceID);
        					ddjp.setHistory(true);
        					jd.put("ddjp",ddjp);
        					jd.put("pubSubService", pubSubService);
        				}
        				tp.setTriggerJobDataMap(jd);
        				mcjp.getTriggerParameterList().add(tp);			
					}
					
					if("1".equals(isCurrent)){
						if(StringUtils.isNotEmpty(subFrequ)){
    						TriggerParameter tp = new TriggerParameter();
            				tp.setTriggerKey(TriggerKey.triggerKey("当前数据执行频率:("+subFrequ+")", orgCode));
            				tp.setScheduleBuilder(QuartzUtil.cronSchedule(subFrequ));
            				JobDataMap jd = new JobDataMap();
            				if("1".equals(serviceType)){
            					DcDataJobParameter ddjp = new DcDataJobParameter();
            					ddjp.setOrgCode(orgCode);
            					String date = DateUtil.getCurrentDate("yyyyMMdd");
            					ddjp.setBeginDate(date+"T000000");
            					ddjp.setEndDate(date+"T240000");
            					ddjp.setDocTypeCode(serviceCode);
            					jd.put("ddjp",ddjp);
            					jd.put("pubSubService", pubSubService);
            				}
            				tp.setTriggerJobDataMap(jd);
            				mcjp.getTriggerParameterList().add(tp);
    					}
					}

				}

			}
			
			Cache cache =  cacheManager.getCache("pubsub");	
			for (Map.Entry<String, MultiCycleJobParameter> entry : multiCycleJobParameterMap
					.entrySet()) {
				MultiCycleJobParameter mcjp = entry.getValue();
				if (mcjp.getTriggerParameterList().size() > 0) {
					qm.addMultiCycleTask(mcjp);
					runJob.add(mcjp.getJobKey());
				}
			}
			cache.put("hasRun",runJob);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
		}	
	}
	
	
	private static final class PubSubRunParaRowMapper implements RowMapper<RunPara> {
		@Override
		public RunPara mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			RunPara rp = new RunPara();
			rp.setIsCurrent(rs.getString("IS_CURRENT"));
		    rp.setIsHistory(rs.getString("IS_HISTORY"));
		    rp.setBeginDate(rs.getString("BEGIN_DATE"));
		    rp.setEndDate(rs.getString("END_DATE"));
		    rp.setSubFrequ(rs.getString("SUB_FREQU"));
		    rp.setIsHistoryRun(rs.getString("IS_HISTORY_RUN"));
		    rp.setServiceCode(rs.getString("SERVICE_CODE"));
		    rp.setServiceType(rs.getString("SERVICE_TYPE"));
		    rp.setMainOrgCode(rs.getString("MAIN_ORG_CODE"));
		    rp.setIsCurrent(rs.getString("IS_CURRENT"));
		    rp.setServiceName(rs.getString("SERVICE_NAME"));
		    rp.setNodeName(rs.getString("NODE_NAME"));
		    rp.setPubSubServiceID(rs.getString("ID_"));
			return rp;
		}

	}


}

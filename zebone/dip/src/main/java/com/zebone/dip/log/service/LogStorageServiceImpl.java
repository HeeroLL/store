package com.zebone.dip.log.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zebone.dip.log.dao.DocLogMapper;
import com.zebone.dip.log.dao.LogDetailMapper;
import com.zebone.dip.log.vo.DocLog;
import com.zebone.dip.log.vo.LogDetail;
import com.zebone.dip.log.vo.Syslog;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 类描述：日志记录接口实现类
 * @author: caixl
 * @date： 日期：Sep 10, 2013
 * @version 1.0
 */
@Service("logStorageServiceImpl")
public class LogStorageServiceImpl implements LogStorageService {
	
	private static final Log log = LogFactory.getLog(LogStorageServiceImpl.class); 

	@Resource
	private DocLogMapper docLogMapper;
	@Resource
	private LogDetailMapper logDetailMapper;
	
	/**
	 * 方法描述: 存储日志信息
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param logxml 日志信息
	 * void
	 */
	@Override
	public void saveLog(String logxml) {
		Syslog syslog = (Syslog)TransformXmlObj.xmlToObj(logxml, Syslog.class);
		DocLog docLog = new DocLog();
		String saveFlag = "2";//存储标识 1、添加 2、更新
		LogDetail logDetail = null;
		if("1".equals(syslog.getCategory())){
			/*****************日志赋值start*********************/
			docLog.setId(syslog.getLogID());
			docLog.setDocNo(syslog.getDocNo());
			docLog.setDocSourceOrgan(syslog.getDeptCode());
			docLog.setDocType(syslog.getDoctype());
			docLog.setExecTime(syslog.getExecTime());
			
			if("1".equals(syslog.getType())){
				docLog.setUploadStatus(syslog.getStatus());
				saveFlag = "1";
				docLog.setUploadTime(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			}else if("2".equals(syslog.getType())){
				docLog.setReceiveStatus(syslog.getStatus());
			}else if("3".equals(syslog.getType())){
				docLog.setCheckStatus(syslog.getStatus());
			}else if("4".equals(syslog.getType())){
				docLog.setStorageStatus(syslog.getStatus());
			}else if("5".equals(syslog.getType())){
				docLog.setRegisterStatus(syslog.getStatus());
			}else if("6".equals(syslog.getType())){
				docLog.setAnalyzeStatus(syslog.getStatus());
			}
			
			if("0".equals(syslog.getStatus())){
				logDetail = new LogDetail();
				logDetail.setId(UUIDUtil.getUuid());
				logDetail.setDocNo(syslog.getDocNo());
				if(syslog.getError()!=null){
					logDetail.setErrorCode(syslog.getError().getCode());
					logDetail.setLogDetails(syslog.getError().getDesc());
				}
				logDetail.setEventTime(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				logDetail.setEventType(syslog.getType());
				logDetail.setLogId(syslog.getLogID());
			}
			/*****************日志赋值end*********************/
			//存储日志
			try{
				if("1".equals(saveFlag)){//保存日志概要
					log.info("保存日志  文档编号 :"+docLog.getDocNo()+ " 日志类型："+syslog.getType()+" 日志id:"+docLog.getId());
					docLogMapper.insertSelective(docLog);
				}else if("2".equals(saveFlag)){//更新日志概要
					log.info("更新日志  文档编号 :"+docLog.getDocNo()+ " 日志类型："+syslog.getType()+" 日志id:"+docLog.getId());
					docLogMapper.updateByPrimaryKeySelective(docLog);
				}
				if(logDetail != null){//保存日志详情
					log.info("保存日志详细   文档编号 :"+logDetail.getDocNo()+ " 日志类型："+syslog.getType()+" 详细日志logId:"+ logDetail.getLogId());
					logDetailMapper.insertSelective(logDetail);
				}
			}catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage(),e);
			}
		
		}
	}

}

package com.zebone.dip.log;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zebone.dip.log.service.LogStorageService;
import com.zebone.dip.log.vo.Syslog;
import com.zebone.util.TransformXmlObj;

/**
 * 类描述：日志客户端
 * @author: caixl
 * @date： 日期：Sep 11, 2013
 * @version 1.0
 */

public class LogServiceClient {
	
	private static Log log = LogFactory.getLog(LogServiceClient.class);
	
	private LogStorageService logStorageService;
	
	
    
	public void saveLog(Syslog syslog){
		 try{
			 String logxml = TransformXmlObj.objToXml(syslog, Syslog.class);
			 logStorageService.saveLog(logxml);
		 }catch(Exception e){
			 log.error("调用保存日志接口失败", e);
		 }
	 }



	public void setLogStorageService(LogStorageService logStorageService) {
		this.logStorageService = logStorageService;
	}
	
	
	
}

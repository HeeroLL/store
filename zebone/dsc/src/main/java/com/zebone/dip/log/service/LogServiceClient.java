package com.zebone.dip.log.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.zebone.dip.log.vo.Syslog;

/**
 * 类描述：日志客户端
 * @author: caixl
 * @date： 日期：Sep 11, 2013
 * @version 1.0
 */

public class LogServiceClient {
	
	private static Log log = LogFactory.getLog(LogServiceClient.class);
	
	private  LogStorageService logStorageService;
	
	private String sysLogWsdl;
	
	
	public void init(){
		 JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
		 jw.setAddress(sysLogWsdl);
		 jw.setServiceClass(LogStorageService.class);
		 logStorageService = (LogStorageService)jw.create();
	}
	
		
	public void setSysLogWsdl(String sysLogWsdl) {
		this.sysLogWsdl = sysLogWsdl;
	}
	
	
	public void saveLog(Syslog syslog){
		 try{
			 String logxml = TransformXmlObj.objToXml(syslog, Syslog.class);
			 logStorageService.saveLog(logxml);
		 }catch(Exception e){
			 log.error("调用保存日志接口失败", e);
		 }
	 }
	

}

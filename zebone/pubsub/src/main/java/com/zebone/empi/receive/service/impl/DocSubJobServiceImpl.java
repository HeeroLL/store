package com.zebone.empi.receive.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
//import com.zebone.docSub.DocSubService;
import com.zebone.empi.receive.pojo.DocSubRequest;
import com.zebone.empi.receive.service.DocSubJobService;
import com.zebone.util.DateUtil;

/**
 * 文档订阅任务服务实现类
 * 
 * @author 杨英
 * @version 2014-8-13 下午2:08
 */
@Service("docSubJobService")
public class DocSubJobServiceImpl 
//implements DocSubJobService 
{

	private static final Log log = LogFactory
			.getLog(DocSubJobServiceImpl.class);

	// 订阅机构
	@Value("${subOrg}")
	private String subOrg;
	// 需要获取的文档的所属结构
	@Value("${docOrg}")
	private String docOrg;
	// 需要获取的文档类型
	@Value("${docType}")
	private String docType;
	// 卫生服务活动的起始日期
	@Value("${startDt}")
	private String startDt;
	// 卫生服务活动的截止日期
	@Value("${endDt}")
	private String endDt;

	// 文档订阅的webService地址
	@Value("${docSubUrl}")
	private String docSubUrl;

//	@Override
//	public void docSub() {
//		try {
//			XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
//			xs.processAnnotations(DocSubRequest.class);
//			
//			JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
//			jw.setAddress(docSubUrl);
//			jw.setServiceClass(DocSubService.class);
//			DocSubService docSubService = (DocSubService) jw.create();
//			Client proxy = ClientProxy.getClient(docSubService);
//			HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
//			HTTPClientPolicy policy = new HTTPClientPolicy();
//			policy.setReceiveTimeout(1800000);// 请求超时时间.
//			conduit.setClient(policy);
//			
//			String[] docOrgs = docOrg.split(",");
//			if(docOrgs.length > 0){
//				for(String org:docOrgs){
//					DocSubRequest docSubRequest = new DocSubRequest();
//					docSubRequest.setSubOrg(subOrg);
//					docSubRequest.setDocOrg(org);
//					docSubRequest.setDocType(docType);
//
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//					Date beginDate = formatter.parse(startDt + "000000");
//					Date endDate = formatter.parse(endDt + "000000");
//
//					Date ndate = DateUtils.addMinutes(beginDate, 1);
//					
//					while (ndate.before(endDate) || ndate.equals(endDate)) {
//						long time1 = System.currentTimeMillis();
//						String nBeginDate = new StringBuilder(formatter.format(beginDate)).toString();
//						String nEndDate = new StringBuilder(formatter.format(ndate)).toString();
//
//						docSubRequest.setStartDt(nBeginDate);
//						docSubRequest.setEndDt(nEndDate);
//						
//						String xmlParam = xs.toXML(docSubRequest);
//						docSubService.docSub(xmlParam);
//						long time2 = System.currentTimeMillis();
//						long diff = time2-time1;
//						if(diff > 30000){
//							log.info("获取机构:"+org+" "+nBeginDate+"---"+nEndDate+"时间段健康档案   耗时:"+ diff +"毫秒");
//
//						}
//						beginDate = DateUtils.addMinutes(beginDate, 1);
//						ndate = DateUtils.addMinutes(beginDate, 1);
//					}
//                   log.info("全量数据["+startDt+"------"+endDt+"]"+"获取机构"+org+"数据完毕");
//				}
//			}		
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		}
//
//	}

//	@Override
//	public void incrementDocSub() {
//		// TODO Auto-generated method stub
//		try {
//			XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
//			xs.processAnnotations(DocSubRequest.class);
//			
//			JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
//			jw.setAddress(docSubUrl);
//			jw.setServiceClass(DocSubService.class);
//			DocSubService docSubService = (DocSubService) jw.create();
//			Client proxy = ClientProxy.getClient(docSubService);
//			HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
//			HTTPClientPolicy policy = new HTTPClientPolicy();
//			policy.setReceiveTimeout(1800000);// 请求超时时间.
//			conduit.setClient(policy);
//			
//			String[] docOrgs = docOrg.split(",");
//			if(docOrgs.length > 0){
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//				
//				String endDateStr = DateUtil.getCurrentDate("yyyyMMddHHmmss");
//				String startDateStr = formatter.format(DateUtils.addHours(formatter.parse(endDateStr), -15));
//				
//				for(String org:docOrgs){
//					DocSubRequest docSubRequest = new DocSubRequest();
//					docSubRequest.setSubOrg(subOrg);
//					docSubRequest.setDocOrg(org);
//					docSubRequest.setDocType(docType);
//
//					Date beginDate = formatter.parse(startDateStr);
//					Date endDate = formatter.parse(endDateStr);
//
//					Date ndate = DateUtils.addMinutes(beginDate, 1);
//					
//					while (ndate.before(endDate) || ndate.equals(endDate)) {
//						long time1 = System.currentTimeMillis();
//						String nBeginDate = new StringBuilder(formatter.format(beginDate)).toString();
//						String nEndDate = new StringBuilder(formatter.format(ndate)).toString();
//
//						docSubRequest.setStartDt(nBeginDate);
//						docSubRequest.setEndDt(nEndDate);
//						
//						String xmlParam = xs.toXML(docSubRequest);
//						docSubService.docSub(xmlParam);
//						long time2 = System.currentTimeMillis();
//						long diff = time2-time1;
//						if(diff > 5000){
//							log.info("获取机构:"+org+" "+nBeginDate+"---"+nEndDate+"时间段健康档案   耗时:"+ diff +"毫秒");
//						}
//						beginDate = DateUtils.addMinutes(beginDate, 1);
//						ndate = DateUtils.addMinutes(beginDate, 1);
//					}
//                   log.info("增量数据["+startDateStr+"------"+endDateStr+"]"+"获取机构"+org+"数据完毕");
//				}
//			}		
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		}
//
//	}

}

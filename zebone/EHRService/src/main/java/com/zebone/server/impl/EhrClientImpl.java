/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * Administrator              New             May 8, 2013
 */
package com.zebone.server.impl;

import java.util.Map;

import com.zebone.activemq.ActiveMqSender;
import com.zebone.dip.log.LogServiceClient;
import com.zebone.dip.log.vo.Error;
import com.zebone.dip.log.vo.Syslog;
import com.zebone.mq.ISender;
import com.zebone.server.DocExtInfoService;
import com.zebone.server.EhrClient;
import com.zebone.server.ResultSet;
import com.zebone.server.DocExtendInfo;
import com.zebone.util.DateUtil;
import com.zebone.util.XmlParser;

public class EhrClientImpl implements EhrClient {

	private ISender sender = null;
	
	private LogServiceClient logServiceClient = null;
	
	public void setLogServiceClient(LogServiceClient logServiceClient) {
		this.logServiceClient = logServiceClient;
	}
	public void setSender(ISender sender){
		this.sender = sender;
	}
	/**
	 * @author caixl
	 * @date May 8, 2013
	 * @description TODO 数据采集标准接口方法
	 * @param header 头信息
	 * @param body 主体信息
	 * @return ResultSet
	 */
	public ResultSet EHRSyncTransport(String xml) {
		/**校验*/
		
		//System.out.println(header);
		//System.out.println(body);
		
		/**拼装*/
		//StringBuffer sf = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><zebone>");
		//sf.append(header).append(body).append("</zebone>");
		
		/**将数据传到消息队列中去*/
		//System.out.println(sf.toString());
		
		//添加注释
		String docXml = DocExtInfoService.addExtInfo(xml);
		
		ResultSet rs = new ResultSet();
		
		/********拼装日志信息********/
		Syslog syslog = new Syslog();
		
		XmlParser xp = new XmlParser();
		Map<String, String> map = xp.getParamInfo(xml);
		if(map!=null && !map.isEmpty()){
			syslog.setDocNo(map.get("EVENT-EX00.00.000.54"));
			syslog.setDeptCode(map.get("GENERATE-EX00.00.000.06"));
			syslog.setDoctype(map.get("EVENT-EX00.00.000.55"));
		}
		
		DocExtendInfo returnObject = DocExtInfoService.getExtInfo(docXml);
		if(returnObject!=null){
			syslog.setLogID(returnObject.getUuid());
		}
		syslog.setCategory("1");
		syslog.setLogTime(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		syslog.setType(Syslog.TYPE_UPLOAD);
		//syslog.s
		try{
			syslog.setStatus("1");
			logServiceClient.saveLog(syslog);
			sender.send(docXml);
		}catch(Exception e){
			Error error = new Error();
			error.setCode("F_001");
			error.setDesc("提交文档到MQ发生错误");
			syslog.setError(error);
			syslog.setStatus("0");
			logServiceClient.saveLog(syslog);
			
			e.printStackTrace();
			rs.setResult("");
			rs.setReturnCode(0);
			rs.setMessage("false");
			return rs;
		}
		//System.out.println(xml);
		
		/**返回对应的值*/
		rs.setReturnCode(1);
		rs.setResult("");
		rs.setMessage("success");
		return rs;
	}

}

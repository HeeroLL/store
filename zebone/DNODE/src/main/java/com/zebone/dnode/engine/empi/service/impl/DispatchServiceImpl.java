package com.zebone.dnode.engine.empi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.empi.dao.EmpiMatchLogMapper;
import com.zebone.dnode.engine.empi.service.DispatchService;
import com.zebone.dnode.engine.empi.vo.EmpiMatchLog;
import com.zebone.register.RegisterNotice;

@Service("dispatchService")
public class DispatchServiceImpl implements DispatchService {
	
	private static Logger log = Logger.getLogger(DispatchServiceImpl.class);
	
	@Resource
	EmpiMatchLogMapper empiMatchLogMapper;

    @Value("${url.docNotice}")
    private String docNotice;
	
	@Override
	public List<EmpiMatchLog> getMatchedLog() {
		List<EmpiMatchLog> matchedList = new ArrayList<EmpiMatchLog>();
		List<EmpiMatchLog> unMatchedLog = empiMatchLogMapper.getUnmatchedEmpiMatchLog();
		for(EmpiMatchLog log : unMatchedLog){
			//记录存在于empi card中，说明新近注册过
			int count = empiMatchLogMapper.countCardByNoAndType(log);
			if(count>0 && "DRC".equals(log.getSystemCode())){
				matchedList.add(log);
			}
		}
		return matchedList;
	}

	@Override
	public void updateResistedMatchLog(EmpiMatchLog empiMtchedLog) {
		//标记为已经匹配“1”
		empiMtchedLog.setMatchState("1");
		empiMatchLogMapper.updateEmpiMatchLog(empiMtchedLog);
	}

	@Override
	public void doDispatch() {

		List<EmpiMatchLog> empiMatchLogList = getMatchedLog();
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(docNotice);
		factory.setServiceClass(RegisterNotice.class);
		RegisterNotice registerNotice = (RegisterNotice)factory.create();
		
		for(EmpiMatchLog eml : empiMatchLogList){
			//TODO webservice 调用文档注册接口	
			String result="0";
			String regXML = "<param><card_no>"+eml.getCardNo()+"</card_no><card_type>"+eml.getCardType()+"</card_type><name>"+eml.getName()+"</name></param>";
			try {
				result = registerNotice.DocumentRegistryNotice(regXML);
			} catch (Exception e) {
				e.printStackTrace();
				result="0";
			}
			if(result.equals("1")){
				updateResistedMatchLog(eml);
			}
		}
	}

	public EmpiMatchLogMapper getEmpiMatchLogMapper() {
		return empiMatchLogMapper;
	}

	public void setEmpiMatchLogMapper(EmpiMatchLogMapper empiMatchLogMapper) {
		this.empiMatchLogMapper = empiMatchLogMapper;
	}

	
	
}

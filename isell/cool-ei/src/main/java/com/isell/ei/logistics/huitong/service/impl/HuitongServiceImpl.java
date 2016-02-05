package com.isell.ei.logistics.huitong.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.huitong.bean.CreateResponse;
import com.isell.ei.logistics.huitong.bean.Order;
import com.isell.ei.logistics.huitong.bean.QueryResponse;
import com.isell.ei.logistics.huitong.bean.StockResponse;
import com.isell.ei.logistics.huitong.service.HuitongService;

@Service("huitongService")
public class HuitongServiceImpl implements HuitongService{

	@Override
	public CreateResponse createMail(Order order,String method) {
		Map<String, String> paramMap = new HashMap<String, String>();
        String xml = JaxbUtil.convertToXml(order);
        xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
	      System.out.println(xml);
	      paramMap.put("id", "0002");
	      paramMap.put("method", "bbc.create");
	      paramMap.put("returnStyle", "xml");
	      paramMap.put("datetime", "20151204175600");
	      paramMap.put("content",  Coder.encryptBASE64(xml));
	      paramMap.put("verify", Coder.encodeMd5("0002"+"20151204175600"+ Coder.encryptBASE64(xml)+123456));
	      String result = HttpUtils.httpPost("http://sys.httx56.com:8058", paramMap);  
	      CreateResponse response = JaxbUtil.converyToJavaBean(result, CreateResponse.class);
	      return response;
	}

	@Override
	public QueryResponse queryBondOrMail(String eNo, String method) {
		Map<String, String> paramMap = new HashMap<String, String>();
		 paramMap.put("id", "0002");
	      paramMap.put("method", method);
	      paramMap.put("datetime", "20151204175600");
	      paramMap.put("content",  Coder.encryptBASE64(eNo));
	      paramMap.put("verify", Coder.encodeMd5("0002"+"20151204175600"+ Coder.encryptBASE64(eNo)+123456));
	      String result = HttpUtils.httpPost("http://sys.httx56.com:8058", paramMap);  
	      QueryResponse response = JsonUtil.readValue(result, QueryResponse.class);
	      return response;
	}

	@Override
	public StockResponse queryBondOrMail(String eNo) {
		Map<String, String> paramMap = new HashMap<String, String>();
		  paramMap.put("id", "0002");
	      paramMap.put("method", "bbc.stock");
	      paramMap.put("datetime", "20151204175600");
	      paramMap.put("content",  Coder.encryptBASE64(eNo));
	      paramMap.put("verify", Coder.encodeMd5("0002"+"20151204175600"+ Coder.encryptBASE64(eNo)+123456));
	      String result = HttpUtils.httpPost("http://sys.httx56.com:8058", paramMap);  
	      StockResponse response = JsonUtil.readValue(result,StockResponse.class);
	      return response;
	}
	
	
	
}

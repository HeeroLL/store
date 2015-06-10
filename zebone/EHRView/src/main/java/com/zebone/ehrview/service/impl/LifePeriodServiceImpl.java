package com.zebone.ehrview.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import com.zebone.ehrview.service.BasicInfoService;
import com.zebone.ehrview.service.LifePeriodService;
import com.zebone.ehrview.vo.LifePeriod;

/**
 * 生命阶段服务层实现类
 * @author YinCM
 *
 */
@Service
public class LifePeriodServiceImpl implements LifePeriodService {
	
	@Resource
	public BasicInfoService basicInfoService;
	
	@Override
	public List<LifePeriod> getLifePeriodList(String empiId,
			String period) {
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>C_L_"+period+"</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<LifePeriod> lifePeriodList = new ArrayList<LifePeriod>();
		//获取xml流中的高血压对象，转换为高血压的list列表，存入model
		try {
			   if(resultStr==null || resultStr.trim().equals("")){
				   return null;
			   }
			   Document document =  DocumentHelper.parseText(resultStr);
			   List<Node> nodeList = document.selectNodes("/result/entity/item");
			   for(Node node : nodeList){
				   String tempHL = node.getText().trim();
				   if(tempHL!=null && !tempHL.trim().equals("")){
					   String[] str = tempHL.split(",");
					   LifePeriod lifePeriod = new LifePeriod();
                       String time = str[0];
                       if(time.length()>=7){
                           time = time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8);
                       }
					   lifePeriod.setTime(time);
                       lifePeriod.setHealthPro(str[1]);
					   lifePeriod.setHealthActivity(str[2]);
					   lifePeriod.setDocNo(str[4]);
					   lifePeriod.setCode(str[3]);
					   lifePeriodList.add(lifePeriod);
				   }
			   }
		 }catch(DocumentException de){
			 de.printStackTrace(); 
		 }
		
		return lifePeriodList;
	}

	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	
}

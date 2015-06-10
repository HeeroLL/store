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
import com.zebone.ehrview.service.HealthProblemService;
import com.zebone.ehrview.vo.HealthProblem;
import com.zebone.ehrview.vo.Hypertension;

/**
 * 健康与疾病问题服务层实现类
 * @author YinCM
 *
 */
@Service
public class HealthProblemServiceImpl implements HealthProblemService {
	
	@Resource
	public BasicInfoService basicInfoService;
	
	@Override
	public List<HealthProblem> getHealthProblemList(String empiId,
			String dataCode) {
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>B_L_"+dataCode+"</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<HealthProblem> healthProblemList = new ArrayList<HealthProblem>();
		//获取xml流中的高血压对象，转换为高血压的list列表，存入model
		try {
			   Document document =  DocumentHelper.parseText(resultStr);
			   List<Node> nodeList = document.selectNodes("/result/entity/item");
			   for(Node node : nodeList){
				   String tempHL = node.getText().trim();
				   if(tempHL!=null && !tempHL.trim().equals("")){
					   String[] str = tempHL.split(",");
					   HealthProblem healthProblem = new HealthProblem();
                       String time = str[0];
                       if(time.length()>=7){
                           time = time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8);
                       }
                       healthProblem.setTime(time);
					   healthProblem.setHealthProblem(str[1]);
					   healthProblem.setHealthActivity(str[2]);
					   healthProblem.setDocNo(str[3]);
					   healthProblem.setCode(str[4]);
					   healthProblemList.add(healthProblem);
				   }
			   }
		 }catch(DocumentException de){
			 de.printStackTrace(); 
		 }
		
		return healthProblemList;
	}

	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	
}

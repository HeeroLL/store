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
import com.zebone.ehrview.service.HealthActivityService;
import com.zebone.ehrview.vo.AdultHealthExam;
import com.zebone.ehrview.vo.CommonListItem;
import com.zebone.ehrview.vo.Hypertension;
import com.zebone.ehrview.vo.OldersFollowup;

/**
 * 卫生活动实现类
 * @author YinCM
 *
 */
@Service
public class HealthActivityServiceImpl implements HealthActivityService{

	@Resource
	public BasicInfoService basicInfoService;
	
	@Override
	public List<Hypertension> getHypertensionFollowupList(String empiId) {
		
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>A_L_B04.01.02.00</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<Hypertension> hypertensionList = new ArrayList<Hypertension>();
		//获取xml流中的高血压对象，转换为高血压的list列表，存入model
		try {
			   Document document =  DocumentHelper.parseText(resultStr);
			   List<Node> nodeList = document.selectNodes("/result/entity/item");
			   for(Node node : nodeList){
				   String tempHL = node.getText().trim();
				   if(tempHL!=null && !tempHL.trim().equals("")){
					   String[] str = tempHL.split(",");
					   Hypertension hypertension = new Hypertension();
                       String time = str[0];
                       if(time.length()>=7){
                           time = time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8);
                       }
					   hypertension.setTime(time);
					   hypertension.setDoctor(str[1]);
					   hypertension.setOrg(str[2]);
					   hypertension.setMethod(str[3]);
					   hypertension.setComment(str[4]);
					   hypertension.setCode(str[5]);
                       hypertension.setMode(str[6]);
					   hypertensionList.add(hypertension);
				   }
			   }
		 }catch(DocumentException de){
			 de.printStackTrace(); 
		 }
		
		return hypertensionList;
	}

	
	@Override
	public List<CommonListItem> getCommonList(String empiId, String docType) {
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>A_L_"+docType+"</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<CommonListItem> commonList = new ArrayList<CommonListItem>();
		if(resultStr.trim().equals("")){
			return commonList;
		}
		//获取xml流中的高血压对象，转换为高血压的list列表，存入model
		try {
			   Document document =  DocumentHelper.parseText(resultStr);
			   List<Node> nodeList = document.selectNodes("/result/entity/item");
			   for(Node node : nodeList){
				   String tempHL = node.getText().trim();
				   if(tempHL!=null && !tempHL.trim().equals("")){
					   String[] str = tempHL.split(",");
					   //公用一个Hypertension类
					   CommonListItem commonListItem = new CommonListItem();
                       String date = str[0];
                       if(date.length()>=7){
                           date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
                       }
                       commonListItem.setTime(date);
                       commonListItem.setDoctor(str[1]);
                       commonListItem.setOrg(str[2]);
                       commonListItem.setOffice(str[3]);
                       commonListItem.setCode(str[4]);
                       commonList.add(commonListItem);
				   }
			   }
		 }catch(DocumentException de){
			 de.printStackTrace(); 
		 }
		return commonList;
	}


	@Override
	public List<Hypertension> getDiabetesFollowupList(String empiId) {
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>A_L_B04.02.02.00</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<Hypertension> diabetesList = new ArrayList<Hypertension>();
		//获取xml流中的高血压对象，转换为高血压的list列表，存入model
		try {
			   Document document =  DocumentHelper.parseText(resultStr);
			   List<Node> nodeList = document.selectNodes("/result/entity/item");
			   for(Node node : nodeList){
				   String tempHL = node.getText().trim();
				   if(tempHL!=null && !tempHL.trim().equals("")){
					   String[] str = tempHL.split(",");
					   //公用一个Hypertension类
					   Hypertension diabetes = new Hypertension();
                       String time = str[0];
                       if(time.length()>=7){
                           time = time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8);
                       }
					   diabetes.setTime(time);
					   diabetes.setDoctor(str[1]);
					   diabetes.setOrg(str[2]);
					   diabetes.setMethod(str[3]);
					   diabetes.setComment(str[4]);
					   diabetes.setCode(str[5]);
                       diabetes.setMode(str[6]);
					   diabetesList.add(diabetes);
				   }
			   }
		 }catch(DocumentException de){
			 de.printStackTrace(); 
		 }
		
		return diabetesList;
	}

	@Override
	public List<AdultHealthExam> getAdultHealthExamList(String empiId) {
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>A_L_C00.04.01.00</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<AdultHealthExam> adultHealthExamList = new ArrayList<AdultHealthExam>();
		//获取xml流中的高血压对象，转换为高血压的list列表，存入model
		try {
			   Document document =  DocumentHelper.parseText(resultStr);
			   List<Node> nodeList = document.selectNodes("/result/entity/item");
			   for(Node node : nodeList){
				   String tempHL = node.getText().trim();
				   if(tempHL!=null && !tempHL.trim().equals("")){
					   String[] str = tempHL.split(",");
					   //公用一个Hypertension类
					   AdultHealthExam adultHealthExam = new AdultHealthExam();
                       String date = str[0];
                       if(date.length()>=7){
                           date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
                       }
					   adultHealthExam.setDate(date);
					   adultHealthExam.setDoctor(str[1]);
					   adultHealthExam.setOrg(str[2]);
					   adultHealthExam.setHealthFlag(str[3]);
					   adultHealthExam.setHealthGuide(str[4]);
					   adultHealthExam.setCode(str[5]);
					   adultHealthExamList.add(adultHealthExam);
				   }
			   }
		 }catch(DocumentException de){
			 de.printStackTrace(); 
		 }
		return adultHealthExamList;
	}

	@Override
	public List<OldersFollowup> getOldersFollowupList(String empiId) {
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>A_L_B04.04.01.00</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<OldersFollowup> oldersFollowupList = new ArrayList<OldersFollowup>();
		//获取xml流中的高血压对象，转换为高血压的list列表，存入model
		try {
			   Document document =  DocumentHelper.parseText(resultStr);
			   List<Node> nodeList = document.selectNodes("/result/entity/item");
			   for(Node node : nodeList){
				   String tempHL = node.getText().trim();
				   if(tempHL!=null && !tempHL.trim().equals("")){
					   String[] str = tempHL.split(",");
					   //公用一个Hypertension类
					   OldersFollowup oldersFollowup = new OldersFollowup();
                       String time = str[0];
                       if(time.length()>=7){
                           time = time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8);
                       }
					   oldersFollowup.setTime(time);
					   oldersFollowup.setDoctor(str[1]);
					   oldersFollowup.setOrg(str[2]);
					   oldersFollowup.setRuleFollowupFlag(str[3]);
					   oldersFollowup.setSuggest(str[4]);
					   oldersFollowup.setCode(str[5]);
					   oldersFollowupList.add(oldersFollowup);
				   }
			   }
		 }catch(DocumentException de){
			 de.printStackTrace(); 
		 }
		return oldersFollowupList;
	}
	
	
	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}
}

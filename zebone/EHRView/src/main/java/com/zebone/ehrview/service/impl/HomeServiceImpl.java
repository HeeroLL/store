package com.zebone.ehrview.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import com.zebone.ehrview.service.BasicInfoService;
import com.zebone.ehrview.service.HomeService;
import com.zebone.ehrview.vo.HealthMenuItem;
import com.zebone.ehrview.vo.ResidentCard;
import com.zebone.ehrview.vo.ResidentInfo;
import com.zebone.ehrview.vo.ServiceMenuItem;
import com.zebone.ehrview.vo.SpecialDocument;

/**
* 为HomeController提供相应数据服务
* @author YinCM
* @since 2013-8-27
*/
@Service
public class HomeServiceImpl implements HomeService{

	@Resource
	public BasicInfoService basicInfoService;
	
	@Override
	public ResidentInfo getPatientBasicInfo(String name, String card_no, String card_type,String orgCode) {
		//新建需要返回的对象，空对象
		ResidentInfo ri = new ResidentInfo();
		Map<String, String> map = ri.getInfoMap();
		
		String empiXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><empi_info><name>"+name+"</name><system_code>EHRView</system_code><card_list>";
		String empiXML1 = "<card><no>"+card_no+"</no><type>"+card_type+"</type><org>"+orgCode+"</org></card></card_list></empi_info>";
		
		String str = basicInfoService.getEmpiBasicInfo(empiXML+empiXML1);
		try {
			   Document document = DocumentHelper.parseText(str);
			   List<Node> nodeList = document.selectNodes("/empi_info/*");
			   for(Node node : nodeList){
				   if(!node.getName().equals("card_list")){
					   Element el = (Element)node;
					   map.put(node.getName(),el.attributeValue("displayName"));
				   }
			   }
			   List<Node> nodeCardList = document.selectNodes("/empi_info/card_list/card/org");
			   for(Node node : nodeCardList){
				   if(node.getText().trim().equals("A1")){
					   Node parentNode = node.getParent();
					   List<Node> nodeIdCardList = parentNode.selectNodes("./*");
					   for(Node nodeId : nodeIdCardList){
						   Element el = (Element)nodeId;
						   if(nodeId.getName().trim().equals("no")){
							   map.put("card_no",nodeId.getText());
						   }else if(nodeId.getName().trim().equals("type")){
							   map.put("card_type",el.attributeValue("displayName"));
						   }
					   }
				   }
			   }
			   
			   //获取card节点，将card节点中字段存入新建的residentcard中
			   List<Node> cardList = document.selectNodes("/empi_info/card_list/card");
			   for(Node node : cardList){
				   ResidentCard rc = new ResidentCard();
				  // rc.setCardLevel(node.selectSingleNode("").getText());
				   rc.setCardNo(node.selectSingleNode("no").getText());
				   rc.setCardOrg(node.selectSingleNode("org").getText());
				   rc.setCardSerialNo(node.selectSingleNode("serial_no").getText());
				   rc.setCardType(((Element)node.selectSingleNode("type")).attributeValue("displayName"));
				   //rc.setCreateDate("");
				   //  rc.setEmpi(node.selectSingleNode("").getText());
				   //rc.setId(node.selectSingleNode("").getText());
				   ri.getCardList().add(rc);
			   }
		 }catch(DocumentException de){
			 de.printStackTrace();
		 }
		//存入map对象
		ri.setInfoMap(map);
		return ri;
	}
	
	@Override
	public List<ServiceMenuItem> getServiceMenuItems(String empiId) {
		
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>A</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<ServiceMenuItem> serviceMenuItemList = new ArrayList<ServiceMenuItem>();
		//从xml中获取document对象，并迭代赋值给menuitem对象，最终返回menuitem的list对象
		try{
			Document document = DocumentHelper.parseText(resultStr);
			List<Node> nodeList = document.selectNodes("/result/entity/item");
			for(Node node : nodeList){
				Element ele = (Element)node;
				ServiceMenuItem smi = new ServiceMenuItem();
				smi.setCode(ele.attributeValue("code"));
				smi.setUrl(getMenuUrlByCode(smi.getCode()));
				//smi.setUrl("healthActivityCommonList.zb");
                if (ele.attributeValue("code").equals("C00.01.02.00")) {
                    smi.setName("门诊");
                } else {
                    smi.setName(ele.getTextTrim());
                }
                serviceMenuItemList.add(smi);
			}
		}catch(DocumentException e){
			e.printStackTrace();
		}
		return serviceMenuItemList;
	}
	
	
	@Override
	public List<SpecialDocument> getSpecialDoc(String empiId) {
		List<SpecialDocument> sdList = new ArrayList<SpecialDocument>();
		//通过ws获取专项档案的xml文档
		String xml = basicInfoService.getHealthRecordInfo("<param><empi_id>"+empiId+"</empi_id><data_code>M_002</data_code><doc_no>aa001</doc_no></param>");
		//将获取的xml文档转换为文档对象列表List<SpecialDocument>
		try{
			Document document = DocumentHelper.parseText(xml);
			List<Node> nodeList = document.selectNodes("/result/entity/item");
			for(Node node : nodeList){
				SpecialDocument sd = new SpecialDocument();
				String xmlStr = node.getText();
				String[] xmlStrA = xmlStr.split(",");
				sd.setName(xmlStrA[0]);
                String date = xmlStrA[1];
                if(date.length()>=7){
                    date = date.substring(0,4)+"."+date.substring(4,6)+"."+date.substring(6,8);
                }
				sd.setDate(date);
				sd.setOrg(xmlStrA[2]);
				sd.setCode(xmlStrA[3]);
				sd.setDocNo(xmlStrA[4]);
				sdList.add(sd);
			}
		}catch(DocumentException e){
			e.printStackTrace();
		}
		return sdList;
	}

	@Override
	public Map<String, String> getHealthHomeInfo(String empiId, String dataCode) {
		String xml = basicInfoService.getHealthRecordInfo("<param><empi_id>"+empiId+"</empi_id><data_code>M_"+dataCode+"</data_code><doc_no></doc_no></param>");
		//返回首页信息的键值对map
		Map<String, String> map = new HashMap<String, String>();
		//将首页信息转换为map对象
		try{
			Document document = DocumentHelper.parseText(xml);
			List<Node> nodeList = document.selectNodes("/result/entity/item");
			for(Node node : nodeList){
				Element el = (Element)node;
				map.put(el.attributeValue("code"), node.getText().trim());
			}
		}catch(DocumentException e){
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public List<HealthMenuItem> getHealthMenuItems(String empiId) {
		String xmlStr = "<param><empi_id>"+empiId+"</empi_id><data_code>B</data_code><doc_no></doc_no></param>";
		String resultStr = basicInfoService.getHealthRecordInfo(xmlStr);
		List<HealthMenuItem> healthMenuItemList = new ArrayList<HealthMenuItem>();
		//从xml中获取document对象，并迭代赋值给menuitem对象，最终返回menuitem的list对象
		try{
			Document document = DocumentHelper.parseText(resultStr);
			List<Node> nodeList = document.selectNodes("/result/entity/item");
			for(Node node : nodeList){
				Element ele = (Element)node;
				HealthMenuItem smi = new HealthMenuItem();
				smi.setCode(ele.attributeValue("code"));
				smi.setName(ele.getTextTrim());
				healthMenuItemList.add(smi);
			}
		}catch(DocumentException e){
			e.printStackTrace();
		}
		return healthMenuItemList;
	}

	/**
	 * 通过menu的code获取菜单所指向的url
	 * 
	 * @param code
	 * @return
	 */
	public String getMenuUrlByCode(String code){
		if(code==null){
			return null;
		}else
		if(code.trim().equals("A00.01.01.00")){
			return "basicInfo.zb";
		}else
		if(code.trim().equals("B04.01.01.00")){
			return "hypertensionRep.zb";
		}else
		if(code.trim().equals("B04.02.01.00")){
			return "diabetesRep.zb";
		}else{
			return "healthActivityCommonList.zb";
		}
		
	}

	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

}

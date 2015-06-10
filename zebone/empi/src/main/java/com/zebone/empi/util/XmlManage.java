package com.zebone.empi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zebone.empi.init.DictionaryConvert;
import com.zebone.empi.vo.EmpiLog;
import com.zebone.empi.vo.ResidentCard;
import com.zebone.empi.vo.ResidentInfo;

/**
 * XML操作类?xml中日期格式~
 * @author YinCm
 * @version 2013-7-31 上午午8:10:20
 */
public class XmlManage {
	
	/**
	 * 将xml解析为EmpiDocument类
	 * @param xml
	 * @return
	 */
	public static ResidentInfo xmlToResidentInfo(String xml, EmpiLog empiLog) throws Exception{
		Document doc = null;
		doc = DocumentHelper.parseText(xml.trim());
		Element root = doc.getRootElement();
		ResidentInfo residentInfo = rootElementToResidentInfo(root, empiLog);
		return residentInfo;
	}
	
	/**
	 * 将EmpiDocument转换为XML字符串
	 * @param ri
	 * @return
	 */
	public static String ResidentInfoToXml(ResidentInfo ri){
		
		Document doc = residentInfoToDocument(ri);
		//将document对象转换为xml字符串
		String xmlStr = doc.asXML().toString();
		return xmlStr;
	}
	
	/**
	 * 将resident对象转换为document对象
	 * @return
	 */
	public static Document residentInfoToDocument(ResidentInfo ri){
		Map<String, Map<String, String>> typMap = DictionaryConvert.getDictionary();
		Document doc = DocumentHelper.createDocument();
		String tempStr = "";
		Element rootEl = doc.addElement("empi_info");
		tempStr = ri.getEmpi()!=null?ri.getEmpi():"";
		rootEl.addElement("empi").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getName()!=null?ri.getName():"";
		rootEl.addElement("name").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getSex()!=null?ri.getSex():"";
		rootEl.addElement("sex").addText(tempStr).addAttribute("displayName", typMap.get("sex").containsKey(tempStr)?typMap.get("sex").get(tempStr):"");
		tempStr = ri.getBirthday()!=null?new SimpleDateFormat("yyyyMMdd").format(ri.getBirthday()).toString():"";
		rootEl.addElement("birthday").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getAge()!=null?new Short(ri.getAge()).toString():"";
		rootEl.addElement("age").addText(tempStr).addAttribute("displayName", tempStr);
        byte[] photo = ri.getPhoto();
        if (photo != null && photo.length > 0) {
            tempStr = new String(photo);
            rootEl.addElement("photo").addText(tempStr).addAttribute("displayName", tempStr);
        }
        tempStr = ri.getStarLevel()!=null?ri.getStarLevel():"";
        rootEl.addElement("star_level").addText(tempStr).addAttribute("displayName",tempStr);
        tempStr = ri.getNationality()!=null?ri.getNationality():"";
		rootEl.addElement("nationality").addText(tempStr).addAttribute("displayName", typMap.get("nationality").containsKey(tempStr)?typMap.get("nationality").get(tempStr):"");
		tempStr = ri.getNation()!=null?ri.getNation():"";
		rootEl.addElement("nation").addText(tempStr).addAttribute("displayName", typMap.get("nation").containsKey(tempStr)?typMap.get("nation").get(tempStr):"");
		tempStr = ri.getMaritalStatus()!=null?ri.getMaritalStatus():"";
		rootEl.addElement("marital_status").addText(tempStr).addAttribute("displayName", typMap.get("matrimony").containsKey(tempStr)?typMap.get("matrimony").get(tempStr):"");
		tempStr = ri.getTelNumber()!=null?ri.getTelNumber():"";
		rootEl.addElement("tel_number").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getProfession()!=null?ri.getProfession():"";
		rootEl.addElement("profession").addText(tempStr).addAttribute("displayName", typMap.get("profession").containsKey(tempStr)?typMap.get("profession").get(tempStr):"");
		tempStr = ri.getWorkUnit()!=null?ri.getWorkUnit():"";
		rootEl.addElement("work_unit").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getContacts()!=null?ri.getContacts():"";
		rootEl.addElement("contacts").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getContactsMobile()!=null?ri.getContactsMobile():"";
		rootEl.addElement("contacts_mobile").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getPermanentAddress()!=null?ri.getPermanentAddress():"";
		rootEl.addElement("permanent_address").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getPermanentCode()!=null?ri.getPermanentCode():"";
		rootEl.addElement("permanent_code").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getPermanentHouseNo()!=null?ri.getPermanentHouseNo():"";
		rootEl.addElement("permanent_house_no").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getPermanentZipcode()!=null?ri.getPermanentZipcode():"";
		rootEl.addElement("permanent_zipcode").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getAddress()!=null?ri.getAddress():"";
		rootEl.addElement("address").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getAddressCode()!=null?ri.getAddressCode():"";
		rootEl.addElement("address_code").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getAddressHouseNo()!=null?ri.getAddressHouseNo():"";
		rootEl.addElement("address_hose_no").addText(tempStr).addAttribute("displayName", tempStr);
		tempStr = ri.getAddressZipcode()!=null?ri.getAddressZipcode():"";
		rootEl.addElement("address_zipcode").addText(tempStr).addAttribute("displayName", tempStr);
		Element cardListElement = rootEl.addElement("card_list").addAttribute("displayName", tempStr);
		if(ri.getCardList()!=null){
			for(ResidentCard rc : ri.getCardList()){
				Element cardElement = cardListElement.addElement("card");
				tempStr = rc.getCardNo()!=null?rc.getCardNo():"";
				cardElement.addElement("no").addText(tempStr).addAttribute("displayName", tempStr);
				tempStr = rc.getCardType()!=null?rc.getCardType():"";
//				//补零
//				if(tempStr.trim()!="" && tempStr.trim().length()<2){
//					tempStr = "0"+tempStr;
//				}
				cardElement.addElement("type").addText(tempStr).addAttribute("displayName", typMap.get("cardType").containsKey(tempStr)?typMap.get("cardType").get(tempStr):"");
				tempStr = rc.getCardOrg()!=null?rc.getCardOrg():"";
				cardElement.addElement("org").addText(tempStr).addAttribute("displayName", tempStr);
				tempStr = rc.getCardSerialNo()!=null?rc.getCardSerialNo():"";
				cardElement.addElement("serial_no").addText(tempStr).addAttribute("displayName", tempStr);
			}
		}
		return doc;
	}
	
	/**
	 * 将dom4j的rootelement对象转换为ResidentInfo对象
	 * @return 
	 */
	public static ResidentInfo rootElementToResidentInfo(Element root, EmpiLog empiLog){
		ResidentInfo residentInfo = new ResidentInfo();
		residentInfo.setCardList(new ArrayList<ResidentCard>());
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element el = (Element) i.next();
			if(el.getName().equals("system_code")){
				residentInfo.setSystem_code(el.getText());
			}else if(el.getName().equals("dept_code")){
				residentInfo.setDeptCode(el.getText());
			}else if(el.getName().equals("name")){
				residentInfo.setName(el.getText());
			}else if(el.getName().equals("sex")){
				residentInfo.setSex(el.getTextTrim());
			}else if(el.getName().equals("birthday")){
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				if(el.getText()!=null && !el.getText().trim().equals("")){
					try{
						Date birthday = df.parse(el.getText().trim());
						residentInfo.setBirthday(birthday);
					}catch(Exception e){
						if(empiLog!=null){
							String str = empiLog.getErrorPosition()!=null? empiLog.getErrorPosition()+",birthday":"birthday";
							empiLog.setErrorPosition(str);
						}
						e.printStackTrace();
					}
				}
			}else if(el.getName().equals("age")){
				String age = el.getText();
				if(age!=null && !age.trim().equals("")){
					try{
						residentInfo.setAge(Short.parseShort(age));
					}catch(Exception e){
						if(empiLog!=null){
							String str = empiLog.getErrorPosition()!=null? empiLog.getErrorPosition()+",age":"age";
							empiLog.setErrorPosition(str);
						}
					}
				}
				
			}else if(el.getName().equals("photo")){
                residentInfo.setPhoto(el.getText().getBytes());
            }else if (el.getName().equals("star_level")){
                residentInfo.setStarLevel(el.getText());
            }else if(el.getName().equals("nationality")){
				residentInfo.setNationality(el.getText());
			}else if(el.getName().equals("nation")){
				residentInfo.setNation(el.getText());
			}else if(el.getName().equals("marital_status")){
				residentInfo.setMaritalStatus(el.getText());
			}else if(el.getName().equals("mobile_number")){
				residentInfo.setMobileNumber(el.getText());
			}else if(el.getName().equals("tel_number")){
				residentInfo.setTelNumber(el.getText());
			}else if(el.getName().equals("profession")){
				residentInfo.setProfession(el.getText());
			}else if(el.getName().equals("work_unit")){
				residentInfo.setWorkUnit(el.getText());
			}else if(el.getName().equals("contacts")){
				residentInfo.setContacts(el.getText());
			}else if(el.getName().equals("contacts_mobile")){
				residentInfo.setContactsMobile(el.getText());
			}else if(el.getName().equals("permanent_address")){
				residentInfo.setPermanentAddress(el.getText());
			}else if(el.getName().equals("permanent_code")){
				residentInfo.setPermanentCode(el.getText());
			}else if(el.getName().equals("permanent_house_no")){
				residentInfo.setPermanentHouseNo(el.getText());
			}else if(el.getName().equals("permanent_zipcode")){
				residentInfo.setPermanentZipcode(el.getText());
			}else if(el.getName().equals("address")){
				residentInfo.setAddress(el.getText());
			}else if(el.getName().equals("address_code")){
				residentInfo.setAddressCode(el.getText());
			}else if(el.getName().equals("address_house_no")){
				residentInfo.setAddressHouseNo(el.getText());
			}else if(el.getName().equals("address_zipcode")){
				residentInfo.setAddressZipcode(el.getText());
			}else if(el.getName().equals("card_list")){
				for(Iterator k = el.elementIterator(); k.hasNext();){
					Element cardListEl = (Element)k.next();
					ResidentCard rc = new ResidentCard();
					for (Iterator j = cardListEl.elementIterator(); j.hasNext();) {
						Element cardEl = (Element) j.next();
						if(cardEl.getName().equals("no")){
							rc.setCardNo(cardEl.getText());
						}else if(cardEl.getName().equals("type")){
							rc.setCardType(cardEl.getText());
						}else if(cardEl.getName().equals("org")){
							rc.setCardOrg(cardEl.getText());
						}else if(cardEl.getName().equals("serial_no")){
							rc.setCardSerialNo(cardEl.getText());
						}
					}
					residentInfo.getCardList().add(rc);
				}
				
			}else{
				//System.out.println(el.getName());
			}
		}
		return residentInfo;
	}
}

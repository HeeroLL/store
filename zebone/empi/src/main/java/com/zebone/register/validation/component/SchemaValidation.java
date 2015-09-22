package com.zebone.register.validation.component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaComplexType;
import org.apache.ws.commons.schema.XmlSchemaElement;
import org.apache.ws.commons.schema.XmlSchemaObject;
import org.apache.ws.commons.schema.XmlSchemaSequence;
import org.apache.ws.commons.schema.XmlSchemaSequenceMember;
import org.apache.ws.commons.schema.XmlSchemaType;
import org.dom4j.Document;
import org.dom4j.Element;

import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.enu.XsdError;
import com.zebone.register.validation.vo.ErrorMsg;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.register.validation.vo.XsdValidationPara;
import com.zebone.util.DateUtil;

/**
 * schema xsd校验组件
 * 
 * @author 陈阵
 * @date 2013-8-2 下午4:34:57
 */
public class SchemaValidation implements XsdValidation {
	
    private static final Log logger = LogFactory.getLog(SchemaValidation.class);

	private ValidationResult validationResult = new ValidationResult();

	/** 存储 xsd节点 map **/
	private Map<String, XmlSchemaElement> xmlSchemaElementMap = new LinkedHashMap<String, XmlSchemaElement>();
	
	/** 没有出现的分类节点 map **/
	private Map<String,String> categoryNodeNotAppearMap = new HashMap<String, String>();
	
	/** 分类节点重复map **/
	private Map<String,String> categoryNodeRepeatMap = new HashMap<String, String>();

	/** 检查重复性 **/
	private String checkRepeat;

	/** 检查可选性 **/
	private String checkSelect;

	public SchemaValidation() {

	}

	@Override
	public ValidationResult validation(XsdValidationPara xsdValidationPara) {
		ErrorMsg errorMsg = validationResult.getErrorMsg();
		/** 设置校验开始时间 **/
		errorMsg.setStartTime(DateUtil.getCurrentDate());
		/** 加载传输过来需要校验的数据流 **/
		Document dataDocument = xsdValidationPara.getDataDocument();
		/** 加载相对应的xsd数据流 **/
		XmlSchema schema = xsdValidationPara.getXmlSchema();
		this.checkRepeat = xsdValidationPara.getCheckRepeat();
		this.checkSelect = xsdValidationPara.getCheckSelect();
		loadXmlSchemal(schema);
		checkFromXsd(dataDocument);
		/** 设置校验结束时间 **/
		errorMsg.setEndTime(DateUtil.getCurrentDate());
		return validationResult;
	}

	/**
	 * 加载xsd结构
	 * @param schema xsd schema
	 * @author 陈阵
	 * @date 2013-8-6 上午10:21:46
	 */
	private void loadXmlSchemal(XmlSchema schema) {
		List<XmlSchemaObject> xmlSchemaObjectList = schema.getItems();
		for (XmlSchemaObject xmlSchemaObject : xmlSchemaObjectList) {
			XmlSchemaElement xmlSchemaElement = (XmlSchemaElement) xmlSchemaObject;
			initSchemaElementMap(xmlSchemaElement, "");
		}

	}

	/**
	 * 加载xmlschema节点到xmlSchemaElementMap
	 * @param xmlSchemaElement  xmlschema节点
	 * @param xpath
	 * @author 陈阵
	 * @date 2013-8-6 上午10:22:12
	 */
	private void initSchemaElementMap(XmlSchemaElement xmlSchemaElement,
			String xpath) {
		/** 获取xsd结点的名字 **/
		String xmlSchemaElementName = xmlSchemaElement.getName();
		xpath = xpath + "/" + xmlSchemaElementName;
		XmlSchemaType xmlSchemaType = xmlSchemaElement.getSchemaType();
		if (xmlSchemaType != null && xmlSchemaType instanceof XmlSchemaComplexType) {
			/** xsd中的混合内容 <xs:complexType>*********</xs:complexType> **/
			//System.out.println(xpath + "xxx"+xmlSchemaElement.getName() +"xxxx"+xmlSchemaElement.getDefaultValue());
			xmlSchemaElementMap.put(xpath, xmlSchemaElement);
			XmlSchemaComplexType xmlSchemaComplexType = (XmlSchemaComplexType) xmlSchemaType;
			XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence) xmlSchemaComplexType.getParticle();
			if(xmlSchemaSequence != null){
				List<XmlSchemaSequenceMember> xmlSchemaSequenceMemberList = xmlSchemaSequence.getItems();
				for (XmlSchemaSequenceMember xmlSchemaSequenceMember : xmlSchemaSequenceMemberList) {
					/** 取出混合内容中的节点 **/
					XmlSchemaElement sunXmlSchemaElement = (XmlSchemaElement) xmlSchemaSequenceMember;
					initSchemaElementMap(sunXmlSchemaElement, xpath);
				}
			}	
		}else{
			/** 简易的xsd元素 <xs:element name="xxx" type="yyy"/> **/
			//System.out.println(xpath + "xxx"+xmlSchemaElement.getName() +"xxxx"+xmlSchemaElement.getDefaultValue());
			xmlSchemaElementMap.put(xpath, xmlSchemaElement);
		}
	
	}

	/**
	 * 校验重复性和可选性
	 * 
	 * @param dataDocument
	 * @author 陈阵
	 * @date 2013-8-6 上午10:23:29
	 */
	public void checkFromXsd(Document dataDocument) {	
		if (StringUtils.isNotEmpty(checkRepeat) && "1".equals(checkRepeat)) {
			checkRepeat(dataDocument);
			checkFromDocumentData(dataDocument);
		}

		if (validationResult.isSucess() && StringUtils.isNotEmpty(checkSelect) && "1".equals(checkSelect)) {
			checkSelect(dataDocument);
		}
	}

	/**
	 * 检查重复性
	 * @param dataDocument 数据文档
	 * @author 陈阵
	 * @date 2013-8-6 上午10:34:05
	 */
	@SuppressWarnings("unchecked")
	private void checkRepeat(Document dataDocument) {
		/** 错误码 **/
		String errorCode = null;
		/** 错误消息 **/
		String errorMsg = null;
		for (Map.Entry<String, XmlSchemaElement> entry : xmlSchemaElementMap.entrySet()) {
			boolean errorFlag = false;
			/** 校验节点开始时间 **/
			String startTime = DateUtil.getCurrentDate();
			String xmlSchemaElementMapKey = entry.getKey();
			XmlSchemaElement xmlSchemaElement = entry.getValue();
			String xpath = xmlSchemaElementMapKey;
			long elementMinOccurs = xmlSchemaElement.getMinOccurs();
			long elementMaxOccurs = xmlSchemaElement.getMaxOccurs();
			

			/** 分类节点做重复性检查 **/
			if (xmlSchemaElementMapKey.indexOf("slot") == -1
					&& xmlSchemaElementMapKey.indexOf("value") == -1) {
				String selectNodePath = "/" + xmlSchemaElementMapKey;
				int occurs = dataDocument.selectNodes(selectNodePath).size();
				if ((occurs < elementMinOccurs) || (occurs > elementMaxOccurs)) {
					errorCode = XsdError.ELEMENT_REPEAT.getErrorCode();
					errorMsg = XsdError.ELEMENT_REPEAT.getErrorMsg() + ":"
							+ xmlSchemaElement.getName() + "["
							+ elementMinOccurs + "," + elementMaxOccurs + "]:"
							+ occurs;
					errorFlag = true;
					categoryNodeRepeatMap.put(xmlSchemaElementMapKey, "0");
					//System.out.println("####"+xmlSchemaElementMapKey);
				}else if(occurs == 0){
					categoryNodeNotAppearMap.put(xmlSchemaElementMapKey, "0");
				}
			} else if (xmlSchemaElementMapKey.indexOf("slot") != -1
					&& xmlSchemaElementMapKey.indexOf("value") == -1) {
				/** 校验slot **/
				String xpathSplit[] = xmlSchemaElementMapKey.split("#");
				String elementAttrNameValue = xpathSplit[1];
				String selectNodePath = "/" + xpathSplit[0];
				
				/** slot元素上级分类结点  **/
				String categoryNodeSelectPath = xpathSplit[0].substring(0,xpathSplit[0].lastIndexOf("/"));
				//System.out.println(selectNodePath + "xxx"+categoryNodeSelectPath+"xx"+categoryNodeNotAppearMap.get(categoryNodeSelectPath)+"xxx"+categoryNodeRepeatMap.get(categoryNodeSelectPath));
				if(categoryNodeNotAppearMap.get(categoryNodeSelectPath) == null && categoryNodeRepeatMap.get(categoryNodeSelectPath) == null){
					List<Element> categoryNodeElementList = dataDocument.selectNodes(categoryNodeSelectPath);
					/** slot结点  **/
					List<Element> slotElementList = dataDocument.selectNodes(selectNodePath);		
					if(categoryNodeElementList.size() > 1){
						slotElementList = categoryNodeElementList.get(0).elements();
					}
					int slotNum = checkSlotElementNum(elementAttrNameValue, slotElementList);
					if (slotNum == 0 && xmlSchemaElement.getDefaultValue() == null) {
						errorCode = XsdError.ELEMENT_REPEAT.getErrorCode();
						errorMsg = XsdError.ELEMENT_REPEAT.getErrorMsg() + ":"
								+ elementAttrNameValue + "[1]:0";
						errorFlag = true;
					}else if(slotNum > 1){
						errorCode = XsdError.ELEMENT_REPEAT.getErrorCode();
						errorMsg = XsdError.ELEMENT_REPEAT.getErrorMsg() + ":"
								+ elementAttrNameValue + "[1]:"+slotNum;
						errorFlag = true;
					}
				}	
			}

			if (errorFlag) {
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setDocXpath(xpath);
				/** 错误类型 **/
				errorMsgDetail.setErrorType(ErrorType.XSD.getType());
				/** 错误编码 **/
				errorMsgDetail.setErrorSubType(errorCode);
				/** 错误消息 **/
				errorMsgDetail.setErrorDesc(errorMsg);
				/** 设置时间区间 **/
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setSucess(false);
			}
		}
	}

	/**
	 * 检查可选性
	 * @param dataDocument 数据文档
	 * @author 陈阵
	 * @date 2013-8-6 上午10:33:30
	 */
	@SuppressWarnings("unchecked")
	private void checkSelect(Document dataDocument) {
		String errorCode = null;
		String errorMsg = null;
		String xpath = null;
		for (Map.Entry<String, XmlSchemaElement> entry : xmlSchemaElementMap.entrySet()) {
			boolean errorFlag = false;
			/** 校验节点开始时间 **/
			String startTime = DateUtil.getCurrentDate();
			String xmlSchemaElementMapKey = entry.getKey();
			/** slot/value 节点 **/
			if (xmlSchemaElementMapKey.indexOf("slot") != -1
					&& xmlSchemaElementMapKey.indexOf("value") != -1) {
				xpath = xmlSchemaElementMapKey;
				String xpathSplit[] = xmlSchemaElementMapKey.split("#");
				String lastElementName = xpathSplit[1].substring(xpathSplit[1].lastIndexOf("/"));
				String elementAttrNameValue = xpathSplit[1].substring(0,xpathSplit[1].lastIndexOf("/"));
				String selectNodePath = "/" + xpathSplit[0];
                
				String categoryNodePath = xpathSplit[0].substring(0,xpathSplit[0].lastIndexOf("/"));
				XmlSchemaElement soltXmlSchemaElement = xmlSchemaElementMap.get(xmlSchemaElementMapKey.substring(0,xmlSchemaElementMapKey.lastIndexOf("/")));
				String selectFlag = soltXmlSchemaElement.getDefaultValue();
				/** 如果selectFlag为null 必须做可选性校验  **/
				if (selectFlag == null && categoryNodeNotAppearMap.get(categoryNodePath) == null) {
					/** 获取slot元素下的value元素   **/
					List<Element> soltElementList = dataDocument.selectNodes(selectNodePath);
					Element slotElement = checkSlotElement(elementAttrNameValue, soltElementList);
					List<Element> valueElements = slotElement.elements();
					/** 没有value节点 校验错误  **/
					if (valueElements.isEmpty()){
						errorCode = XsdError.ELEMENT_SELECT.getErrorCode();
						errorMsg = XsdError.ELEMENT_SELECT.getErrorMsg()+ ":" + lastElementName + "必选";
						errorFlag = true;
					}else{
						if(StringUtils.isEmpty(valueElements.get(0).attributeValue("code"))){
							errorCode = XsdError.ELEMENT_SELECT.getErrorCode();
							errorMsg = XsdError.ELEMENT_SELECT.getErrorMsg()+ ":" + lastElementName + "必选";
							errorFlag = true;
						}
					}
				}
			}
			if (errorFlag) {
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setDocXpath(xpath);
				/** 错误类型 **/
				errorMsgDetail.setErrorType(ErrorType.XSD.getType());
				/** 错误编码 **/
				errorMsgDetail.setErrorSubType(errorCode);
				/** 错误消息 **/
				errorMsgDetail.setErrorDesc(errorMsg);

				/** 设置时间区间 **/
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setSucess(false);
			}

		}
	}

	/**
	 * 通过slot的name属性检查相对应的slot是否出现
	 * @param elementAttrNameValue name属性值
	 * @param soltElementList 所有的slot结点
	 * @return Element 节点
	 * @author 陈阵
	 * @date 2013-8-5 下午3:55:15
	 */
	private Element checkSlotElement(String elementAttrNameValue, List<Element> slotElementList) {
		for (Element slotElement : slotElementList) {
			if (elementAttrNameValue.equals(slotElement.attributeValue("name"))) {
				return slotElement;
			}
		}
		return null;
	}
	
	
    /**
     * 检查slot节点
     * @param elementAttrNameValue
     * @param slotElementList
     * @return
     * @author 陈阵 
     * @date 2013-9-3 下午3:00:29
     */
	private int checkSlotElementNum(String elementAttrNameValue, List<Element> slotElementList) {
		int num = 0;
		for (Element slotElement : slotElementList) {
			if (elementAttrNameValue.equals(slotElement.attributeValue("name"))) {
				num ++;
			}
		}
		return num;
	}


	/**
	 * 获取数据文件中并不存在xsd定义的节点
	 * @author 陈阵
	 * @date 2013-8-6 上午11:00:48
	 */
	private void checkFromDocumentData(Document dataDocument) {
		praseElement(dataDocument.getRootElement());
	}
    
	/**
	 * 递归进行校验
	 * @param element
	 * @author 陈阵 
	 * @date 2013-8-6 下午1:15:49
	 */
	@SuppressWarnings("unchecked")
	private void praseElement(Element element) {	
		List<Element> elementList = element.elements();
		/** 此节点还有子节点 **/
		if (!elementList.isEmpty()) {
			for(Element e:elementList){
				praseElement(e);
			}
		}
		String elementPath = element.getPath();
		if(elementPath.indexOf("/slot/value") != -1){
			String elementAttrNameValue = element.getParent().attributeValue("name");
			elementPath = elementPath.replace("slot", "slot#" + elementAttrNameValue);
		}else if(elementPath.indexOf("/slot") != -1){
			String elementAttrNameValue = element.attributeValue("name");
			elementPath = elementPath.replace("slot", "slot#" + elementAttrNameValue);
		}
		if(xmlSchemaElementMap.get(elementPath) == null){
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setStartTime(DateUtil.getCurrentDate());		
			errorMsgDetail.setDocXpath(elementPath);
			/** 错误类型 **/
			errorMsgDetail.setErrorType(ErrorType.XSD.getType());
			/** 错误编码 **/
			errorMsgDetail.setErrorSubType(XsdError.ELEMENT_REPEAT.getErrorCode());
			/** 错误消息 **/
			String errorMsg = XsdError.ELEMENT_REPEAT.getErrorMsg() +":"+ element.getName() +"未在xsd中定义";
			errorMsgDetail.setErrorDesc(errorMsg);

			/** 设置时间区间 **/
			errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}
	}

}

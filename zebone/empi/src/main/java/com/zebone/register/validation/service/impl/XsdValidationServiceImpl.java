package com.zebone.register.validation.service.impl;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.zebone.register.validation.component.SchemaValidation;
import com.zebone.register.validation.component.XsdValidation;
import com.zebone.register.validation.domain.DocConf;
import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.enu.MetaDataError;
import com.zebone.register.validation.enu.SystemError;
import com.zebone.register.validation.enu.XsdError;
import com.zebone.register.validation.repository.MetaDataRepository;
import com.zebone.register.validation.service.XsdValidationService;
import com.zebone.register.validation.vo.ErrorMsg;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.register.validation.vo.XsdValidationPara;
import com.zebone.util.DateUtil;


/**
 * XSD校验service实现类
 * @author 陈阵 
 * @date 2013-7-30 下午2:57:37
 */
@Service("xsdValidationService")
public class XsdValidationServiceImpl implements XsdValidationService {
	
	private static final Log logger = LogFactory.getLog(XsdValidationServiceImpl.class);
    
	/** 元数据 Repository **/
	@Resource
	private MetaDataRepository metaDataRepository;
	

	@Override
	public ValidationResult validation(String docTypeCode, String docVersion, Document xmlDataDocument,String checkRepeat, String checkSelect) {
		// TODO Auto-generated method stub
		ValidationResult validationResult = new ValidationResult();
		String startTime = DateUtil.getCurrentDate();
		if(StringUtils.isNotEmpty(docTypeCode) && StringUtils.isNotEmpty(docVersion) && xmlDataDocument != null){
			/** 获取文档配置 **/
		    DocConf docConf = metaDataRepository.getDocConfigByDocTypeCodeAndDocVersion(docTypeCode, docVersion);
		    /** 获取xsd **/
		    String docXsd = docConf.getDocXsd();
		    if(StringUtils.isNotEmpty(docXsd)){
			    String docId = docConf.getId();
			    
			    /** 解析xsd生成xmlschema **/	 
				XmlSchema xmlSchema = parseXsd(docId,docXsd);
				
				/** 封装xsd校验所需要的参数  **/
				XsdValidationPara xsdValidationPara = new XsdValidationPara();
				xsdValidationPara.setCheckRepeat(checkRepeat);
				xsdValidationPara.setCheckSelect(checkSelect);
				xsdValidationPara.setDataDocument(xmlDataDocument);
				xsdValidationPara.setXmlSchema(xmlSchema);
				
				XsdValidation xsdValidation = new SchemaValidation();
			    validationResult = xsdValidation.validation(xsdValidationPara);
		    }else{
		    	validationResult.setSucess(false);
			    String errorMsg = "([文档类型编码："+docTypeCode+",文档版本:"+docVersion +"])";
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				errorMsgDetail.setErrorType(ErrorType.METADATA.getType());
				errorMsgDetail.setErrorSubType(MetaDataError.DOC_DOCUMENT_XSD_NOT_FIND.getErrorCode());
				errorMsgDetail.setErrorDesc(MetaDataError.DOC_DOCUMENT_XSD_NOT_FIND.getErrorMsg() + errorMsg);	
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
		    }
		}else{
			validationResult.setSucess(false);
		    String errorMsg = "([文档类型编码："+docTypeCode+",文档版本:"+docVersion+",xmlDataDocument:" + xmlDataDocument +"])";
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
			errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
			errorMsgDetail.setErrorSubType(SystemError.PARAMETER_ERROR.getErrorCode());
			errorMsgDetail.setErrorDesc(SystemError.PARAMETER_ERROR.getErrorMsg() + errorMsg);	
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
		}

		String endTime = DateUtil.getCurrentDate();
		ErrorMsg errorMsg = validationResult.getErrorMsg();
		errorMsg.setStartTime(startTime);
		errorMsg.setEndTime(endTime);
		return validationResult;
	}

	@Override
	public ValidationResult validationDocument(String xmlData) {
		ValidationResult validationResult = new ValidationResult();
		ErrorMsg errorMsg = validationResult.getErrorMsg();
		String startTime = DateUtil.getCurrentDate();
		errorMsg.setStartTime(startTime);
		Document dataDocument = null;
		try {
			SAXReader saxReader = new SAXReader();
			/** 加载传输过来需要校验的数据流 **/
			dataDocument = saxReader.read(new ByteArrayInputStream(xmlData.getBytes()));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			validationResult.setSucess(false);
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setStartTime(startTime);		
			/** 错误类型 **/
			errorMsgDetail.setErrorType(ErrorType.XSD.getType());
			/** 错误子类型**/
			errorMsgDetail.setErrorSubType(XsdError.DOCUMENT.getErrorCode());
			/** 错误消息 **/
			errorMsgDetail.setErrorDesc(XsdError.DOCUMENT.getErrorMsg());
			/** 异常消息  **/
			errorMsgDetail.setErrorException(e.getMessage());
			/** 设置时间区间 **/
			errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
		}
		if(validationResult.isSucess()){
			validationResult.setDataDocument(dataDocument);
			Map<String,String> headerMap = getHeaderInfo(dataDocument);
			validationResult.setHeaderMap(headerMap);
			/** 获取文档配置 **/
			String docTypeCode = headerMap.get("docTypeCode");
			String docVersion = headerMap.get("docVersion");
            
			errorMsg.setDocVersion(docVersion);
			errorMsg.setDocTypeCode(docTypeCode);
			errorMsg.setDocCode(headerMap.get("docCode"));
			errorMsg.setDocOrgCode(headerMap.get("docOrgCode"));
			if(StringUtils.isNotEmpty(docTypeCode) && StringUtils.isNotEmpty(docVersion)){
			    DocConf docConf = metaDataRepository.getDocConfigByDocTypeCodeAndDocVersion(docTypeCode, docVersion);
			    if(docConf == null){
					validationResult.setSucess(false);
			    	String eMsg = "([文档类型编码："+docTypeCode+",文档版本："+docVersion+"])";
			    	ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
					errorMsgDetail.setStartTime(startTime);		
					/** 错误类型 **/
					errorMsgDetail.setErrorType(ErrorType.METADATA.getType());
					/** 错误子类型**/
					errorMsgDetail.setErrorSubType(MetaDataError.DOC_DOCUMENT_NOT_FIND.getErrorCode());
					/** 错误消息 **/
					errorMsgDetail.setErrorDesc(MetaDataError.DOC_DOCUMENT_NOT_FIND.getErrorMsg() + eMsg);
					/** 设置时间区间 **/
					errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
					validationResult.getErrorMsgDetial().add(errorMsgDetail);
			    }else{
			    	validationResult.setDocId(docConf.getId());
			    }
			}else{
				validationResult.setSucess(false);
		    	String eMsg = "([文档类型编码："+docTypeCode+",文档版本："+docVersion+"])";
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setStartTime(startTime);		
				/** 错误类型 **/
				errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
				/** 错误子类型**/
				errorMsgDetail.setErrorSubType(SystemError.PARAMETER_ERROR.getErrorCode());
				/** 错误消息 **/
				errorMsgDetail.setErrorDesc(SystemError.PARAMETER_ERROR.getErrorMsg() + eMsg);
	
				/** 设置时间区间 **/
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
			}
		}
		/** 设置校验结束时间 **/
		errorMsg.setEndTime(DateUtil.getCurrentDate());
		return validationResult;
	}
	
	/**
	 * 解析xsd生成xmlschema
	 * @param docXsd
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-13 上午9:27:57
	 */
	@Cacheable(value="xsd",key="'xsd_' + #docId")
	private XmlSchema parseXsd(String docId,String docXsd){
		  /** 解析xsd生成xmlschema **/
	    InputSource xsdIs = new InputSource(new ByteArrayInputStream(docXsd.getBytes()));
		XmlSchemaCollection schemaCol = new XmlSchemaCollection();
		XmlSchema xmlSchema = schemaCol.read(xsdIs);
		return xmlSchema;
	}
	
	
	/**
	 * 获取文档头信息
	 * 
	 * @param xmlDataDocument
	 * @return
	 * @author 陈阵
	 * @date 2013-8-18 上午8:28:58
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getHeaderInfo(Document xmlDataDocument) {
		Map<String, String> headerMap = new HashMap<String, String>();
		/**业务系统编码  **/
		String sysCode = null;
		/** 文档类型编码 **/
		String docTypeCode = null;
		/** 文档操作编码 **/
		String docOpeCode = null;
		/** 机构编码  **/
		String docOrgCode = null;
		/** 人员编码  **/
		String docUserCode = null;
		/** 文档版本   **/
		String docVersion = null;
		/** 文档编号  **/
		String docCode = null;
		/** 获取文档头信息 **/
		String eventXpath = "//ClinicalDocument/header/slot";
		List<Element> headerElements = xmlDataDocument.selectNodes(eventXpath);
		if(!headerElements.isEmpty()){
			for (Element headerElement : headerElements) {
				String headerElementAttNameValue = headerElement.attributeValue("name");
				 if ("业务系统编码".equals(headerElementAttNameValue)) {
					List<Element> ValueElements = headerElement.elements();
					if (ValueElements.size() == 1) {
						sysCode = ValueElements.get(0).attributeValue("code");
					}
				} else if ("文档类型编码".equals(headerElementAttNameValue)) {
					List<Element> ValueElements = headerElement.elements();
					if (ValueElements.size() == 1) {
						docTypeCode = ValueElements.get(0).attributeValue("code");
					}
				} else if ("文档操作编码".equals(headerElementAttNameValue)) {
					List<Element> ValueElements = headerElement.elements();
					if (ValueElements.size() == 1) {
						docOpeCode = ValueElements.get(0).attributeValue("code");
					}
				} else if ("机构编码".equals(headerElementAttNameValue)) {
					List<Element> ValueElements = headerElement.elements();
					if (ValueElements.size() == 1) {
						docOrgCode = ValueElements.get(0).attributeValue("code");
					}
				} else if ("操作人员编码".equals(headerElementAttNameValue)) {
					List<Element> ValueElements = headerElement.elements();
					if (ValueElements.size() == 1) {
						docUserCode = ValueElements.get(0).attributeValue("code");
					}
                } else if ("文档版本".equals(headerElementAttNameValue)) {
					List<Element> ValueElements = headerElement.elements();
					if (ValueElements.size() == 1) {
						docVersion = ValueElements.get(0).attributeValue("code");
					}
                } else if ("文档编号".equals(headerElementAttNameValue)) {
					List<Element> ValueElements = headerElement.elements();
					if (ValueElements.size() == 1) {
						docCode = ValueElements.get(0).attributeValue("code");
					}
                }
            }
        }
		headerMap.put("sysCode", sysCode);
		headerMap.put("docTypeCode", docTypeCode);
		headerMap.put("docOpeCode", docOpeCode);
		headerMap.put("docOrgCode", docOrgCode);
		headerMap.put("docUserCode", docUserCode);
		headerMap.put("docVersion", docVersion);
		headerMap.put("docCode", docCode);
		return headerMap;

	}
	
}

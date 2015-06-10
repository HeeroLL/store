package com.zebone.dnode.engine.validation.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.validation.domain.CheckConf;
import com.zebone.dnode.engine.validation.domain.CheckConfig;
import com.zebone.dnode.engine.validation.domain.CheckResult;
import com.zebone.dnode.engine.validation.dto.ErrorMsg;
import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.enu.ErrorType;
import com.zebone.dnode.engine.validation.service.BusinessValidationService;
import com.zebone.dnode.engine.validation.service.CheckConfService;
import com.zebone.dnode.engine.validation.service.CheckDataService;
import com.zebone.dnode.engine.validation.service.CheckService;
import com.zebone.dnode.engine.validation.service.DataFormatValidationService;
import com.zebone.dnode.engine.validation.service.FieldTypeValidationService;
import com.zebone.dnode.engine.validation.service.OnlyValidationService;
import com.zebone.dnode.engine.validation.service.XsdValidationService;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 校验入口service实现类
 * 
 * @author 陈阵
 * @date 2013-7-30 下午2:56:25
 */
@Service("checkService")
public class CheckServiceImpl implements CheckService {

	private static final Log logger = LogFactory.getLog(CheckServiceImpl.class);

	/**
	 * 文档校验开关服务
	 */
	@Resource
	private CheckConfService checkConfService;
	
	/** 文档校验service **/
	@Resource
	private CheckDataService checkDataService;

	/** xsd校验service **/
	@Resource
	private XsdValidationService xsdValidationService;

	/** 值域校验service **/
	@Resource
	private FieldTypeValidationService fieldTypeValidationService;

	/** 业务校验service **/
	@Resource
	private BusinessValidationService businessValidationService;

	/** 数据格式校验service **/
	@Resource
	private DataFormatValidationService dataFormatValidationService;

	/** 唯一性校验 **/
	@Resource
	private OnlyValidationService onlyValidationService;
	
	
	//从数据库中获取并配置当前文档的校验开关
	//根据文档类型编码来判断是否存在数据库中
	//如果文档类型不存在数据库中，则执行全部校验
	public void setCheckConfig(CheckConfig checkConfig, String docTypeCode) {
		//获取开关配置列表
		List<CheckConf> list = checkConfService.getCheckConfList();
		
		//遍历list获得当前文档
		for(CheckConf cc : list){
			if(cc.getDocTypeCode().trim().equals(docTypeCode.trim())){
				if(cc.getIsRepeat()!=null)
				checkConfig.setIsRepeat(cc.getIsRepeat());
				if(cc.getIsSelect()!=null)
				checkConfig.setIsSelect(cc.getIsSelect());
				if(cc.getIsBusiFormat()!=null)
				checkConfig.setIsBusinessFormat(cc.getIsBusiFormat());
				if(cc.getIsValue()!=null)
				checkConfig.setIsValue(cc.getIsValue());
				if(cc.getIsDataFormat()!=null)
				checkConfig.setIsDataFormat(cc.getIsDataFormat());
				if(cc.getIsOnly()!=null)
				checkConfig.setIsOnly(cc.getIsOnly());
				break;
			}
		}
		
	}




	@Override
	public void check(CheckConfig checkConfig, String xmlData) {
		//开始时间
		long start = System.currentTimeMillis();
		 
		// TODO Auto-generated method stub
		String startDateTime = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		String startTime = DateUtil.getCurrentDate();
		boolean failFlag = false;
		ValidationResult validationResult = null;
		Document xmlDataDocument = null;
		/** 文档版本   **/
		String docVersion = null;
		/** 文档类型编码 **/
		String docTypeCode = null;
		/** 机构编码  **/
		String docOrgCode = null;
		/** 文档编码 **/
		String docCode = null;
		/**上传类型  **/
		String putType = null;
		/** 文档主键 **/
		String docId = checkConfig.getDocId();

		/** 检查xml数据是否符合xml标准 **/
		validationResult = xsdValidationService.validationDocument(xmlData);
		if (!validationResult.isSucess()) {
			failFlag = true;
			ErrorMsg errorMsg = validationResult.getErrorMsg();
			errorMsg.setDocXml(xmlData);
			checkDataService.saveValidationResult(validationResult, null);
		} else {
			xmlDataDocument = validationResult.getDataDocument();
			Map<String, String> headerMap = validationResult.getHeaderMap();
			docVersion = headerMap.get("docVersion");
			docTypeCode = headerMap.get("docTypeCode");
			docOrgCode = headerMap.get("docOrgCode");
			docCode = headerMap.get("docCode");
			putType = headerMap.get("putType");
			docId = validationResult.getDocId();
			setCheckConfig(checkConfig, docTypeCode);
			
		}
		
        String info = "上传文档信息([文档版本：" + docVersion + ",文档类型编码：" + docTypeCode + ",机构编码：" + docOrgCode + ",文档编码："+ docCode+"])";    

		/** xsd校验 **/
		if (!failFlag && (checkConfig.getIsRepeat().equals("1") || checkConfig.getIsSelect().equals("1"))) {
			try {
				validationResult = xsdValidationService.validation(docTypeCode,docVersion, xmlDataDocument, checkConfig.getIsRepeat(),checkConfig.getIsSelect());
				if (!validationResult.isSucess()) {
					failFlag = true;
					ErrorMsg errorMsg = validationResult.getErrorMsg();
					errorMsg.setDocCode(docCode);
					errorMsg.setDocOrgCode(docOrgCode);
					errorMsg.setDocTypeCode(docTypeCode);
					errorMsg.setDocXml(xmlData);
					errorMsg.setDocVersion(docVersion);
					validationResult.setDataDocument(xmlDataDocument);
					checkDataService.saveValidationResult(validationResult, null);
				}
			} catch (Exception e) {
				String errorInfo = info + "######xsd校验错误信息：";
				logger.error(errorInfo + e.getMessage(), e);
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorSubType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorDesc(errorInfo + e.getMessage());
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setDataDocument(xmlDataDocument);
				checkDataService.saveValidationResult(validationResult, null);
			}
		}

		/** 值域校验 **/
		if (!failFlag && checkConfig.getIsValue().equals("1")) {
			try {
				validationResult = fieldTypeValidationService.validation(docId,xmlDataDocument);
				if (!validationResult.isSucess()) {
					failFlag = true;
					ErrorMsg errorMsg = validationResult.getErrorMsg();
					errorMsg.setDocCode(docCode);
					errorMsg.setDocOrgCode(docOrgCode);
					errorMsg.setDocTypeCode(docTypeCode);
					errorMsg.setDocXml(xmlData);
					errorMsg.setDocVersion(docVersion);
					validationResult.setDataDocument(xmlDataDocument);
					checkDataService.saveValidationResult(validationResult, null);
				}
			} catch (Exception e) {
				String errorInfo = info + "######值域校验错误信息：";
				logger.error(errorInfo + e.getMessage(), e);
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorSubType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorDesc(errorInfo + e.getMessage());
				validationResult.setDataDocument(xmlDataDocument);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				checkDataService.saveValidationResult(validationResult, null);
			}
		}

		/** 业务校验 **/
		if (!failFlag && checkConfig.getIsBusinessFormat().equals("1")) {
			try {
				validationResult = businessValidationService.validation(docId,
						xmlDataDocument);
				if (!validationResult.isSucess()) {
					failFlag = true;
					ErrorMsg errorMsg = validationResult.getErrorMsg();
					errorMsg.setDocCode(docCode);
					errorMsg.setDocOrgCode(docOrgCode);
					errorMsg.setDocTypeCode(docTypeCode);
					errorMsg.setDocXml(xmlData);
					errorMsg.setDocVersion(docVersion);
					validationResult.setDataDocument(xmlDataDocument);
					checkDataService.saveValidationResult(validationResult, null);
				}
			} catch (Exception e) {
				String errorInfo = info + "######业务校验错误信息：";
				logger.error(errorInfo + e.getMessage(), e);
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorSubType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorDesc(errorInfo + e.getMessage());
				validationResult.setDataDocument(xmlDataDocument);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				checkDataService.saveValidationResult(validationResult, null);
			}
		}

		/** 数据格式校验 **/
		if (!failFlag && checkConfig.getIsDataFormat().equals("1")) {
			try {
				validationResult = dataFormatValidationService.validation(docId, xmlDataDocument);
				if (!validationResult.isSucess()) {
					failFlag = true;
					ErrorMsg errorMsg = validationResult.getErrorMsg();
					errorMsg.setDocCode(docCode);
					errorMsg.setDocOrgCode(docOrgCode);
					errorMsg.setDocTypeCode(docTypeCode);
					errorMsg.setDocXml(xmlData);
					errorMsg.setDocVersion(docVersion);
					validationResult.setDataDocument(xmlDataDocument);
					checkDataService.saveValidationResult(validationResult, null);
				}
			} catch (Exception e) {
				String errorInfo = info + "######数据格式校验错误信息：";
				logger.error(errorInfo + e.getMessage(), e);
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorSubType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorDesc(errorInfo + e.getMessage());
				validationResult.setDataDocument(xmlDataDocument);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				checkDataService.saveValidationResult(validationResult, null);
			}
		}

		/** 唯一性校验 **/
		if (!failFlag && checkConfig.getIsOnly().equals("1") && "1".equals(putType)) {
			try {
				validationResult = onlyValidationService.validation(docId,xmlDataDocument,docOrgCode);
				if (!validationResult.isSucess()) {
					failFlag = true;
					ErrorMsg errorMsg = validationResult.getErrorMsg();
					errorMsg.setDocCode(docCode);
					errorMsg.setDocOrgCode(docOrgCode);
					errorMsg.setDocTypeCode(docTypeCode);
					errorMsg.setDocXml(xmlData);
					errorMsg.setDocVersion(docVersion);
					validationResult.setDataDocument(xmlDataDocument);
					checkDataService.saveValidationResult(validationResult, null);
				}
			}catch (Exception e) {
				// TODO: handle exception
				String errorInfo = info + "######唯一性校验错误信息：";
				logger.error(errorInfo + e.getMessage(), e);
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
				errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorSubType(ErrorType.SYSTEM.getType());
				errorMsgDetail.setErrorDesc(errorInfo + e.getMessage());
				validationResult.setDataDocument(xmlDataDocument);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				checkDataService.saveValidationResult(validationResult, null);
			}
		}

		/** 没有错误记录到校验结果表 **/
		if (!failFlag) {
			CheckResult checkResult = new CheckResult();
			checkResult.setId(UUIDUtil.getUuid());
			checkResult.setCheckFlag("1");
			checkResult.setStorageFlag("0");
			checkResult.setDocCode(docCode);
			checkResult.setDocTypeCode(docTypeCode);
			checkResult.setDocOrgCode(docOrgCode);
			checkResult.setDocXml(new String(xmlData));
			checkResult.setDocVersion(docVersion);
			
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setDocCode(docCode);
			errorMsg.setDocOrgCode(docOrgCode);
			errorMsg.setDocTypeCode(docTypeCode);
			validationResult.setErrorMsg(errorMsg);
			validationResult.setDataDocument(xmlDataDocument);
			//结束时间
			long end = System.currentTimeMillis();
			checkDataService.saveValidationResult(validationResult,end-start);			
			checkDataService.saveCheckResult(checkResult);	
		}
		String endDateTime = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		//logger.info(info + " 上传是否成功：" + failFlag + " 上传时间："+ startDateTime + "----" + endDateTime);
	}
}

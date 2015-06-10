package com.zebone.register.validation.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.springframework.stereotype.Service;

import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.auditlog.service.AuditlogCacheService;
import com.zebone.auditlog.vo.AuditLog;
import com.zebone.register.validation.domain.CheckConf;
import com.zebone.register.validation.domain.CheckConfig;
import com.zebone.register.validation.enu.OpeType;
import com.zebone.register.validation.enu.ResultType;
import com.zebone.register.validation.service.CheckConfService;
import com.zebone.register.validation.service.CheckService;
import com.zebone.register.validation.service.DataFormatValidationService;
import com.zebone.register.validation.service.FieldTypeValidationService;
import com.zebone.register.validation.service.JurisdictionValidationService;
import com.zebone.register.validation.service.OnlyValidationService;
import com.zebone.register.validation.service.XsdValidationService;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;

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
	
//	/** 文档校验service **/
//	@Resource
//	private CheckDataService checkDataService;

	/** xsd校验service **/
	@Resource
	private XsdValidationService xsdValidationService;

	/** 值域校验service **/
	@Resource
	private FieldTypeValidationService fieldTypeValidationService;

//	/** 业务校验service **/
//	@Resource
//	private BusinessValidationService businessValidationService;

	/** 数据格式校验service **/
	@Resource
	private DataFormatValidationService dataFormatValidationService;

	/** 唯一性校验 **/
	@Resource
	private OnlyValidationService onlyValidationService;
	
	/** 权限校验 **/
	@Resource
	private JurisdictionValidationService jurisdictionValidationService;
	
	/**
	 * 日志服务
	 */
	@Resource
	private AuditlogCacheService auditlogService;
	
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
				if(cc.getIsJurisd()!=null)
				checkConfig.setIsOnly(cc.getIsJurisd());
				break;
			}
		}
		
	}




	@Override
	public String check(CheckConfig checkConfig, String xmlData,String ip) {
		AuditLog auditLog = new AuditLog();
		//开始时间		 
		boolean failFlag = false;
		ValidationResult validationResult = null;
		Document xmlDataDocument = null;
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
		/** 文档主键 **/
		String docId = checkConfig.getDocId();

		/** 检查xml数据是否符合xml标准 **/
		validationResult = xsdValidationService.validationDocument(xmlData);
		if (!validationResult.isSucess()) {
			failFlag = true;
			List<ErrorMsgDetail> list = validationResult.getErrorMsgDetial();
			if(list!=null&&list.size()>0){
				auditLog.setDescription(list.get(0).getErrorDesc());
			}
		} else {
			xmlDataDocument = validationResult.getDataDocument();
			Map<String, String> headerMap = validationResult.getHeaderMap();
			docVersion = headerMap.get("docVersion");
			docTypeCode = headerMap.get("docTypeCode");
			docOrgCode = headerMap.get("docOrgCode");
			docCode = headerMap.get("docCode");
			docUserCode = headerMap.get("docUserCode");
			sysCode = headerMap.get("sysCode");
			docOpeCode = headerMap.get("docOpeCode");
			docId = validationResult.getDocId();
			setCheckConfig(checkConfig, docTypeCode);
			
		}
		String startDateTime = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        String info = "上传文档信息([文档版本：" + docVersion + ",文档类型编码：" + docTypeCode + ",机构编码：" + docOrgCode + ",文档编码："+ docCode+"])";    

        auditLog.setSourceIp(ip);
        auditLog.setOrgCode(docOrgCode);
        auditLog.setPersonAccount(docUserCode);
        auditLog.setCreateTime(new Date());
        auditLog.setCreateDate(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
        //TODO需要修改
        auditLog.setEventType("注册模块");
        //操作类型
        if(docOpeCode.equals(OpeType.DATA_INSERT.getErrorCode())){
        	auditLog.setOptTypeId(OpeType.DATA_INSERT.getErrorCode());
        	auditLog.setOptType(OpeType.DATA_INSERT.getErrorMsg());
        }else if(docOpeCode.equals(OpeType.DATA_UPDATE.getErrorCode())){
        	auditLog.setOptTypeId(OpeType.DATA_UPDATE.getErrorCode());
        	auditLog.setOptType(OpeType.DATA_UPDATE.getErrorMsg());
        }else if(docOpeCode.equals(OpeType.DATA_DELETE.getErrorCode())){
        	auditLog.setOptTypeId(OpeType.DATA_DELETE.getErrorCode());
        	auditLog.setOptType(OpeType.DATA_DELETE.getErrorMsg());
        }
        
        String result = null;
        
		/** xsd校验 **/
		if (!failFlag && (checkConfig.getIsRepeat().equals("1") || checkConfig.getIsSelect().equals("1"))) {
			try {
				validationResult = xsdValidationService.validation(docTypeCode,docVersion, xmlDataDocument, checkConfig.getIsRepeat(),checkConfig.getIsSelect());
				if (!validationResult.isSucess()) {
					failFlag = true;
					List<ErrorMsgDetail> list = validationResult.getErrorMsgDetial();
					if(list!=null&&list.size()>0){
						for(ErrorMsgDetail obj : list){
							auditLog.setDescription(obj.getErrorDesc());
							auditlogService.saveLogToCache(auditLog);
							result = ResultType.XML_ERROR.getErrorCode()+"|"+ResultType.XML_ERROR.getErrorMsg()+"|"+obj.getErrorDesc();
							return result;
						}
					}
				}
			} catch (Exception e) {
				String errorInfo = info + "######xsd校验错误信息：" + e.getMessage();
				auditLog.setDescription(errorInfo);
				auditlogService.saveLogToCache(auditLog);
				result = ResultType.XML_ERROR.getErrorCode()+"|"+ResultType.XML_ERROR.getErrorMsg()+"|"+errorInfo;
				return result;
			}
		}
		
		/** 权限校验 **/
		if (!failFlag && checkConfig.getIsJurisd().equals("1")) {
			validationResult = jurisdictionValidationService.validation(sysCode,docOrgCode);
			if (!validationResult.isSucess()) {
				String errorInfo = info + "######权限校验错误信息：没有上传权限";
				auditLog.setDescription(errorInfo);
				auditlogService.saveLogToCache(auditLog);
				result = ResultType.VALUE_ERROR.getErrorCode()+"|"+ResultType.VALUE_ERROR.getErrorMsg()+"|"+errorInfo;
				return result;
			}
		}
		

		/** 值域校验 **/
		if (!failFlag && checkConfig.getIsValue().equals("1")) {
			try {
				validationResult = fieldTypeValidationService.validation(docId,xmlDataDocument);
				if (!validationResult.isSucess()) {
					failFlag = true;
					List<ErrorMsgDetail> list = validationResult.getErrorMsgDetial();
					if(list!=null&&list.size()>0){
						for(ErrorMsgDetail obj : list){
							auditLog.setDescription(obj.getErrorDesc());
							auditlogService.saveLogToCache(auditLog);
							result = ResultType.VALUE_ERROR.getErrorCode()+"|"+ResultType.VALUE_ERROR.getErrorMsg()+"|"+obj.getErrorDesc();
							return result;
						}
					}
				}
			} catch (Exception e) {
				String errorInfo = info + "######值域校验错误信息：" + e.getMessage();
				auditLog.setDescription(errorInfo);
				auditlogService.saveLogToCache(auditLog);
				result = ResultType.VALUE_ERROR.getErrorCode()+"|"+ResultType.VALUE_ERROR.getErrorMsg()+"|"+errorInfo;
				return result;
			}
		}

//		/** 业务校验 **/
//		if (!failFlag && checkConfig.getIsBusinessFormat().equals("1")) {
//			try {
//				validationResult = businessValidationService.validation(docId,
//						xmlDataDocument);
//				if (!validationResult.isSucess()) {
//					failFlag = true;
//					ErrorMsg errorMsg = validationResult.getErrorMsg();
//					errorMsg.setDocCode(docCode);
//					errorMsg.setDocOrgCode(docOrgCode);
//					errorMsg.setDocTypeCode(docTypeCode);
//					errorMsg.setDocXml(xmlData);
//					errorMsg.setDocVersion(docVersion);
//					validationResult.setDataDocument(xmlDataDocument);
//					checkDataService.saveValidationResult(validationResult, null);
//				}
//			} catch (Exception e) {
//				String errorInfo = info + "######业务校验错误信息：";
//				logger.error(errorInfo + e.getMessage(), e);
//				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
//				errorMsgDetail.setStartTime(startTime);
//				errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
//				errorMsgDetail.setErrorType(ErrorType.SYSTEM.getType());
//				errorMsgDetail.setErrorSubType(ErrorType.SYSTEM.getType());
//				errorMsgDetail.setErrorDesc(errorInfo + e.getMessage());
//				validationResult.setDataDocument(xmlDataDocument);
//				validationResult.getErrorMsgDetial().add(errorMsgDetail);
//				checkDataService.saveValidationResult(validationResult, null);
//			}
//		}

		/** 数据格式校验 **/
		if (!failFlag && checkConfig.getIsDataFormat().equals("1")) {
			try {
				validationResult = dataFormatValidationService.validation(docId, xmlDataDocument);
				if (!validationResult.isSucess()) {
					failFlag = true;
					List<ErrorMsgDetail> list = validationResult.getErrorMsgDetial();
					if(list!=null&&list.size()>0){
						for(ErrorMsgDetail obj : list){
							auditLog.setDescription(obj.getErrorDesc());
							auditlogService.saveLogToCache(auditLog);
							result = ResultType.VALUE_ERROR.getErrorCode()+"|"+ResultType.VALUE_ERROR.getErrorMsg()+"|"+obj.getErrorDesc();
							return result;
						}
					}
				}
			} catch (Exception e) {
				String errorInfo = info + "######数据格式校验错误信息：" + e.getMessage();
				auditLog.setDescription(errorInfo);
				auditlogService.saveLogToCache(auditLog);
				result = ResultType.VALUE_ERROR.getErrorCode()+"|"+ResultType.VALUE_ERROR.getErrorMsg()+"|"+errorInfo;
				return result;
			}
		}

		/** 唯一性校验 **/
		if (!failFlag && checkConfig.getIsOnly().equals("1") && "1".equals(docOpeCode)) {
			try {
				validationResult = onlyValidationService.validation(docId,xmlDataDocument,docOrgCode);
				if (!validationResult.isSucess()) {
					failFlag = true;
					List<ErrorMsgDetail> list = validationResult.getErrorMsgDetial();
					if(list!=null&&list.size()>0){
						for(ErrorMsgDetail obj : list){
							auditLog.setDescription(obj.getErrorDesc());
							auditlogService.saveLogToCache(auditLog);
							result = ResultType.VALUE_ERROR.getErrorCode()+"|"+ResultType.VALUE_ERROR.getErrorMsg()+"|"+obj.getErrorDesc();
							return result;
						}
					}
				}
			}catch (Exception e) {
				String errorInfo = info + "######唯一性校验错误信息：" + e.getMessage();
				auditLog.setDescription(errorInfo);
				auditlogService.saveLogToCache(auditLog);
				result = ResultType.VALUE_ERROR.getErrorCode()+"|"+ResultType.VALUE_ERROR.getErrorMsg()+"|"+errorInfo;
				return result;
			}
		}
		String endDateTime = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		logger.info(info + " 上传是否成功：" + failFlag + " 上传时间："+ startDateTime + "----" + endDateTime);
		return null;
	}
}

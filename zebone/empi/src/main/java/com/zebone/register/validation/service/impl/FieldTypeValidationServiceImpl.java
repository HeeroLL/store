package com.zebone.register.validation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.zebone.register.validation.component.DateFieldTypeValidation;
import com.zebone.register.validation.component.DicFieldTypeValidation;
import com.zebone.register.validation.component.FieldTypeValidation;
import com.zebone.register.validation.component.NumberFieldTypeValidation;
import com.zebone.register.validation.dao.DataCenterMapper;
import com.zebone.register.validation.dao.MasterDataMapper;
import com.zebone.register.validation.domain.FieldConf;
import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.enu.MetaDataError;
import com.zebone.register.validation.repository.MasterDataRepository;
import com.zebone.register.validation.repository.MetaDataRepository;
import com.zebone.register.validation.service.FieldTypeValidationService;
import com.zebone.register.validation.vo.ErrorMsg;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.FieldTypeValidationPara;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;

/**
 * 值域类型检查service实现类
 * @author 陈阵 
 * @date 2013-7-31 下午4:50:37
 */
@Service("fieldTypeValidationService")
public class FieldTypeValidationServiceImpl implements FieldTypeValidationService{
	
	private static final Log logger =  LogFactory.getLog(FieldTypeValidationServiceImpl.class);
	
	@Resource
	private MasterDataMapper masterDataMapper;
	
	@Resource
	private MetaDataRepository metaDataRepository ;
	
	@Resource
	private MasterDataRepository masterDataRepository;
	
	@Resource
	private DataCenterMapper dataCenterMapper;

	@SuppressWarnings("unchecked")
	public ValidationResult validation(String docId, Document xmlDataDocument) {
		// TODO Auto-generated method stub
		ValidationResult validationResult = new ValidationResult();
		String startTime = DateUtil.getCurrentDate();
	
		List<Element> valueElementList = xmlDataDocument.selectNodes("//value");
		if(!valueElementList.isEmpty()){
			for (Element valueElement : valueElementList) {
				/** value元素的值 **/
				Attribute valueCodeAttr = valueElement.attribute("code");
				String valueCodeAttrValue = valueCodeAttr.getValue();
				/** 你元素对应元数据的标识符号 **/
				Element parentElement = valueElement.getParent();
				/** 结点的code属性 **/
				Attribute parentElementCodeAttr = parentElement.attribute("code");
				String parentElementCodeAttrValue = parentElementCodeAttr.getValue();
				/** 父亲结点的name值 用于标识xpath **/
				String parentElementNameValue = parentElement.attribute("name").getValue();
				String xpath = parentElement.getPath() + "/"+ parentElementNameValue;
	             
				FieldConf fieldConf = metaDataRepository.getFieldConfByFieldId(parentElementCodeAttrValue);
				
				if(fieldConf != null){
					FieldTypeValidationPara fieldTypeValidationPara = new FieldTypeValidationPara();
					fieldTypeValidationPara.setXpath(xpath);
					/** 值域校验类型 **/
					String fieldType = fieldConf.getFieldType();
					if ("0".equals(fieldType)) {

					} else if ("1".equals(fieldType)) {
						/** 日期  **/
						fieldTypeValidationPara.setFieldTypeValue(valueCodeAttrValue);
						FieldTypeValidation dateFieldTypeValidation = new DateFieldTypeValidation();
						dateFieldTypeValidation.validation(fieldTypeValidationPara);
					} else if ("2".equals(fieldType)) {

					} else if ("3".equals(fieldType)) {
						/** 数字   **/
						fieldTypeValidationPara.setFieldTypeValue(valueCodeAttrValue);
						FieldTypeValidation dateFieldTypeValidation = new NumberFieldTypeValidation();
						dateFieldTypeValidation.validation(fieldTypeValidationPara);
					} else if ("4".equals(fieldType)) {
						/** 数据字典校验 **/
						String fieldValue = fieldConf.getFieldValue();
						if(StringUtils.isEmpty(fieldValue)){
							String errorMsg = "([元数据标识:"+parentElementCodeAttrValue+"])";
							validationResult.setSucess(false);			
							ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
							errorMsgDetail.setStartTime(startTime);
							errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
							errorMsgDetail.setErrorType(ErrorType.VALUE_FIELD.getType());
							errorMsgDetail.setErrorSubType(MetaDataError.FIELD_CONFIG_FIELDVALUE_NOT_FIND.getErrorCode());
							errorMsgDetail.setErrorDesc(MetaDataError.FIELD_CONFIG_FIELDVALUE_NOT_FIND.getErrorCode()+ errorMsg);
							errorMsgDetail.setDocXpath(xpath);		
							validationResult.getErrorMsgDetial().add(errorMsgDetail);
							return validationResult;
						}
						fieldTypeValidationPara.setFieldTypeValue(valueCodeAttrValue);
						fieldTypeValidationPara.setDicTypeId(fieldConf.getFieldValue());
						FieldTypeValidation dicFieldTypeValidation = new DicFieldTypeValidation(masterDataRepository);
						validationResult = dicFieldTypeValidation.validation(fieldTypeValidationPara);
					} else if ("5".equals(fieldType)) {
						/** 主数据校验 **/
						/**
						String fieldValue = fieldConf.getFieldValue();
						if(StringUtils.isEmpty(fieldValue)){
							String errorMsg = "([元数据标识:"+parentElementCodeAttrValue+"])";
							validationResult.setSucess(false);			
							ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
							errorMsgDetail.setStartTime(startTime);
							errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
							errorMsgDetail.setErrorType(ErrorType.VALUE_FIELD.getType());
							errorMsgDetail.setErrorSubType(MetaDataError.FIELD_CONFIG_FIELDVALUE_NOT_FIND.getErrorCode());
							errorMsgDetail.setErrorDesc(MetaDataError.FIELD_CONFIG_FIELDVALUE_NOT_FIND.getErrorCode()+ errorMsg);
							errorMsgDetail.setDocXpath(xpath);		
							validationResult.getErrorMsgDetial().add(errorMsgDetail);
							return validationResult;
						}
						fieldTypeValidationPara.setMasterDataId(fieldConf.getFieldValue());
						fieldTypeValidationPara.setFieldTypeValue(valueCodeAttrValue);
						FieldTypeValidation masterDataFieldTypeValidation = new MasterDataValidation(masterDataMapper,dataCenterMapper);
						validationResult = masterDataFieldTypeValidation.validation(fieldTypeValidationPara);
						**/
					}
				}else{
					String errorMsg = "([数据源标识："+parentElementCodeAttrValue+"])";
					validationResult.setSucess(false);			
					ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
					errorMsgDetail.setStartTime(startTime);
					errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
					errorMsgDetail.setErrorType(ErrorType.VALUE_FIELD.getType());
					errorMsgDetail.setErrorSubType(MetaDataError.FIELD_CONF_NOT_FIND.getErrorCode());
					errorMsgDetail.setErrorDesc(MetaDataError.FIELD_CONF_NOT_FIND.getErrorCode()+ errorMsg);
					errorMsgDetail.setDocXpath(xpath);		
					validationResult.getErrorMsgDetial().add(errorMsgDetail);
					break;
				}
				if(!validationResult.isSucess()){
					break;
				}
			}
		}
		
		String endTime = DateUtil.getCurrentDate();
		ErrorMsg errorMsg = validationResult.getErrorMsg();
		errorMsg.setStartTime(startTime);
		errorMsg.setEndTime(endTime);
		return validationResult;
	}
	


}

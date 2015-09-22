package com.zebone.dnode.engine.validation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.validation.component.BusinessValidation;
import com.zebone.dnode.engine.validation.component.CellphoneBusinessValidation;
import com.zebone.dnode.engine.validation.component.EmailBusinessValidation;
import com.zebone.dnode.engine.validation.component.IdcardBusinessValidation;
import com.zebone.dnode.engine.validation.component.PhoneBusinessValidation;
import com.zebone.dnode.engine.validation.domain.FieldConf;
import com.zebone.dnode.engine.validation.dto.BusinessValidationPara;
import com.zebone.dnode.engine.validation.dto.ErrorMsg;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.repository.MetaDataRepository;
import com.zebone.dnode.engine.validation.service.BusinessValidationService;
import com.zebone.util.DateUtil;


/**
 * 业务校验service接口实现类
 * @author 陈阵 
 * @date 2013-8-8 下午1:32:58
 */
@Service("businessValidationService")
public class BusinessValidationServiceImpl implements BusinessValidationService {
	
	private static final Log logger = LogFactory.getLog(BusinessValidationServiceImpl.class);
	
	@Resource
	private MetaDataRepository metaDataRepository ;
	

	@SuppressWarnings("unchecked")
	@Override
	public ValidationResult validation(String docId, Document xmlDataDocument) {
		// TODO Auto-generated method stub
		ValidationResult validationResult = new ValidationResult();
		String startTime = DateUtil.getCurrentDate();
		List<Element> valueElementList = xmlDataDocument.selectNodes("//value");
		for (Element valueElement : valueElementList) {
			/** value元素的值 **/
			Attribute valueCodeAttr = valueElement.attribute("code");
			String valueCodeAttrValue = valueCodeAttr.getValue();
			/** 你元素对应元数据的标识符号 **/
			Element parentElement = valueElement.getParent();
			/** 结点的code属性 **/
			String parentElementCodeAttrValue = parentElement.attributeValue("code");
			/** 父亲结点的name值 用于标识xpath **/
			String parentElementNameValue = parentElement.attribute("name").getValue();
			String xpath = parentElement.getPath() + "/"+ parentElementNameValue;

			FieldConf fieldConf = metaDataRepository.getFieldConfByFieldId(parentElementCodeAttrValue);

			BusinessValidationPara businessValidationPara = new BusinessValidationPara();
			businessValidationPara.setBusinessValidationValue(valueCodeAttrValue);
			businessValidationPara.setXpath(xpath);
			/** 业务校验 **/
			String fieldRuleFormat = fieldConf.getFieldRuleFormat();
			BusinessValidation businessValidation = null;
			if ("1".equals(fieldRuleFormat)) {
				businessValidation = new EmailBusinessValidation();
			} else if ("2".equals(fieldRuleFormat)) {
				businessValidation = new PhoneBusinessValidation();
			} else if ("3".equals(fieldRuleFormat)) {
				businessValidation = new IdcardBusinessValidation();
			} else if ("4".equals(fieldRuleFormat)) {
				businessValidation = new CellphoneBusinessValidation();
			}
			if(businessValidation != null){
				validationResult = businessValidation.validation(businessValidationPara);
			}
		}

		String endTime = DateUtil.getCurrentDate();
		ErrorMsg errorMsg = validationResult.getErrorMsg();
		errorMsg.setStartTime(startTime);
		errorMsg.setEndTime(endTime);
		return validationResult;
	}

}

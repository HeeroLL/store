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

import com.zebone.register.validation.component.DataFormatValidation;
import com.zebone.register.validation.domain.FieldConf;
import com.zebone.register.validation.repository.MetaDataRepository;
import com.zebone.register.validation.service.DataFormatValidationService;
import com.zebone.register.validation.util.DataFormatClassUtil;
import com.zebone.register.validation.vo.DataFormatValidationPara;
import com.zebone.register.validation.vo.ErrorMsg;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;

/**
 * 数据格式校验service实现类
 * @author 陈阵
 * @date 2013-8-8 下午1:32:58
 */
@Service("dataFormatValidationService")
public class DataFormatValidationServiceImpl implements DataFormatValidationService {

	private static final Log logger =  LogFactory.getLog(DataFormatValidationServiceImpl.class);

	@Resource
	private MetaDataRepository metaDataRepository;


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
			Attribute parentElementCodeAttr = parentElement.attribute("code");
			String parentElementCodeAttrValue = parentElementCodeAttr.getValue();
			/** 父亲结点的name值 用于标识xpath **/
			String parentElementNameValue = parentElement.attribute("name").getValue();
			String xpath = parentElement.getPath() + "/"+ parentElementNameValue;

			FieldConf fieldConf = metaDataRepository.getFieldConfByFieldId(parentElementCodeAttrValue);
            
			String fieldFormat = fieldConf.getFieldFormat();
            
			DataFormatValidationPara dataFormatValidationPara = new DataFormatValidationPara();
			dataFormatValidationPara.setDataFormatValidationValue(valueCodeAttrValue);
			dataFormatValidationPara.setXpath(xpath);
			dataFormatValidationPara.setExpression(fieldFormat);
            if(StringUtils.isEmpty(fieldFormat)){
            	continue;
            }
			DataFormatValidation dataFormatValidation = DataFormatClassUtil.getDataFormatValidation(fieldFormat);
			if(dataFormatValidation != null){
				validationResult = dataFormatValidation.validation(dataFormatValidationPara);
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

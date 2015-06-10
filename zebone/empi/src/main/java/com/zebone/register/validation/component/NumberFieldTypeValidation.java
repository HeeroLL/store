package com.zebone.register.validation.component;

import org.apache.commons.lang3.math.NumberUtils;

import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.enu.ValueFieldError;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.FieldTypeValidationPara;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;


/**
 * 数字验证(值域验证)
 * @author 陈阵 
 * @date 2013-7-31 下午2:20:25
 */
public class NumberFieldTypeValidation implements FieldTypeValidation {
	
	private ValidationResult validationResult = new ValidationResult();

	
	public NumberFieldTypeValidation(){
	}
	
	
	@Override
	public ValidationResult validation(FieldTypeValidationPara fieldTypeValidationPara) {
		// TODO Auto-generated method stub
		String startTime = DateUtil.getCurrentDate();
		String fieldTypeValue = fieldTypeValidationPara.getFieldTypeValue();
		if(!NumberUtils.isNumber(fieldTypeValue)){
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(fieldTypeValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.VALUE_FIELD.getType());
			errorMsgDetail.setErrorSubType(ValueFieldError.NUBMER_FORMAT.getErrorCode());
			errorMsgDetail.setErrorDesc(ValueFieldError.NUBMER_FORMAT.getErrorMsg());
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}

		return validationResult;
	}

}

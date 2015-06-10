package com.zebone.dnode.engine.validation.component;

import org.apache.commons.lang3.math.NumberUtils;

import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.FieldTypeValidationPara;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.enu.ErrorType;
import com.zebone.dnode.engine.validation.enu.ValueFieldError;
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

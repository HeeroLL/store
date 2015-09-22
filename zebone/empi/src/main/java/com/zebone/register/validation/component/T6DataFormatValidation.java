package com.zebone.register.validation.component;

import java.text.SimpleDateFormat;

import com.zebone.register.validation.enu.DataFormatError;
import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.vo.DataFormatValidationPara;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;

/**
 * hhmmss
 * @author 陈阵 
 * @date 2013-8-10 上午9:02:07
 */
public class T6DataFormatValidation implements DataFormatValidation {
	
	private ValidationResult  validationResult  = new ValidationResult();

	@Override
	public ValidationResult validation(
			DataFormatValidationPara dataFormatValidationPara) {
		// TODO Auto-generated method stub
		String startTime = DateUtil.getCurrentDate();
		String dataFormatValidationValue = dataFormatValidationPara.getDataFormatValidationValue();
		String expression = dataFormatValidationPara.getExpression();
		
		if("T6".equals(expression)){
			try{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hhmmss");
				simpleDateFormat.parse(dataFormatValidationValue);
			}catch (Exception e) {
				// TODO: handle exception
				String errorMsg = "([格式："+expression+",校验值："+dataFormatValidationValue+")]";
				String endTime = DateUtil.getCurrentDate();
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(endTime);
				errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
				errorMsgDetail.setErrorSubType(DataFormatError.T6.getErrorCode());
				errorMsgDetail.setErrorDesc(DataFormatError.T6.getErrorMsg() + errorMsg);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setSucess(false);
				return validationResult;
			}
		}else{
			String errorMsg = "([格式："+expression+",校验值："+dataFormatValidationValue+")]";
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
			errorMsgDetail.setErrorSubType(DataFormatError.T6.getErrorCode());
			errorMsgDetail.setErrorDesc(DataFormatError.T6.getErrorMsg() + errorMsg);
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}
		
		return validationResult;
	}
	
}

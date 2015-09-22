package com.zebone.dnode.engine.validation.component;

import com.zebone.dnode.engine.validation.dto.DataFormatValidationPara;
import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.enu.DataFormatError;
import com.zebone.dnode.engine.validation.enu.ErrorType;
import com.zebone.util.DateUtil;

/**
 * T/F
 * @author 陈阵 
 * @date 2013-8-10 上午9:02:07
 */
public class TFDataFormatValidation implements DataFormatValidation {
	
	private ValidationResult validationResult  = new ValidationResult();

	@Override
	public ValidationResult validation(
			DataFormatValidationPara dataFormatValidationPara) {
		// TODO Auto-generated method stub
		boolean expressionFormatSucessFlag = false;
		String startTime = DateUtil.getCurrentDate();
		String dataFormatValidationValue = dataFormatValidationPara.getDataFormatValidationValue();
		String expression = dataFormatValidationPara.getExpression();
		if("T/F".equals(expression)){
			expressionFormatSucessFlag = true;
		}
		if(expressionFormatSucessFlag){
			boolean checkSucess = false;
			if("T".equals(dataFormatValidationValue) || "F".equals(dataFormatValidationValue)){
				checkSucess = true;
			}
			if(!checkSucess){
				String errorMsg = "([格式："+expression+",校验值："+dataFormatValidationValue+")]";
				String endTime = DateUtil.getCurrentDate();
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(endTime);
				errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
				errorMsgDetail.setErrorSubType(DataFormatError.TF.getErrorCode());
				errorMsgDetail.setErrorDesc(DataFormatError.TF.getErrorMsg() + errorMsg);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setSucess(false);
			}
		}else{
			String errorMsg = "([格式："+expression+",校验值："+dataFormatValidationValue+")]";
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
			errorMsgDetail.setErrorSubType(DataFormatError.TF.getErrorCode());
			errorMsgDetail.setErrorDesc(DataFormatError.TF.getErrorMsg() + errorMsg);
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}
		
		return validationResult;
	}
	

}

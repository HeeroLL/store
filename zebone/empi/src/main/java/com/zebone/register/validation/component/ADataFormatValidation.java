package com.zebone.register.validation.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.zebone.register.validation.enu.DataFormatError;
import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.vo.DataFormatValidationPara;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;

/**
 * 字母字符+中文
 * 
 * @author 陈阵
 * @date 2013-8-10 上午9:02:07
 */
public class ADataFormatValidation implements DataFormatValidation {

	private ValidationResult validationResult = new ValidationResult();

	@Override
	public ValidationResult validation(
			DataFormatValidationPara dataFormatValidationPara) {
		// TODO Auto-generated method stub
		String startTime = DateUtil.getCurrentDate();
		String dataFormatValidationValue = StringUtils.defaultIfBlank(dataFormatValidationPara.getDataFormatValidationValue(), "") ;
		String expression = dataFormatValidationPara.getExpression();
		
		Pattern pattern1 = Pattern.compile("^A(\\d*)(\\.{0}|\\.{2})(\\d+)$");
		Matcher matcher1 = pattern1.matcher(expression);
		if (matcher1.find()) {
			boolean fixFlag = false;
			boolean checkSucess = true;
			String minLength = matcher1.group(1);
			String twoDot = matcher1.group(2);
			String maxLength = matcher1.group(3);
			int iMinLength = 0;
			int iMaxLength = 0;
	
			if(StringUtils.isEmpty(twoDot)){
				fixFlag = true;
				maxLength = minLength + maxLength;
			}else{
				if(StringUtils.isNotEmpty(minLength)){
					iMinLength = Integer.valueOf(minLength);
				}
			}
			iMaxLength = Integer.valueOf(maxLength);
			int len = dataFormatValidationValue.length();
			if(fixFlag){
				if(len != iMaxLength){
					checkSucess = false;
				}
			}else{
				if(len < iMinLength || len > iMaxLength){
					checkSucess = false;
				}
			}
			/** 检查是否有数字 **/
			Pattern pattern11 = Pattern.compile("\\d+");
			Matcher matcher11 = pattern11.matcher(dataFormatValidationValue);
			if(matcher11.find()){
				checkSucess = false;
			}
			
			if (!checkSucess) {
				String errorMsg = "([格式：" + expression + ",校验值："
						+ dataFormatValidationValue + ")]";
				String endTime = DateUtil.getCurrentDate();
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(endTime);
				errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
				errorMsgDetail
						.setErrorSubType(DataFormatError.A.getErrorCode());
				errorMsgDetail.setErrorDesc(DataFormatError.A.getErrorMsg()
						+ errorMsg);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setSucess(false);
			}		
		}else{
			String errorMsg = "([格式：" + expression + ",校验值："
					+ dataFormatValidationValue + ")]";
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
			errorMsgDetail.setErrorSubType(DataFormatError.A.getErrorCode());
			errorMsgDetail.setErrorDesc(DataFormatError.A.getErrorMsg()
					+ errorMsg);
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);	
		}	
	    return validationResult;
	}
	
}

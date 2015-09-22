package com.zebone.dnode.engine.validation.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.zebone.dnode.engine.validation.dto.DataFormatValidationPara;
import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.enu.DataFormatError;
import com.zebone.dnode.engine.validation.enu.ErrorType;
import com.zebone.util.DateUtil;

/**
 * 数字字符
 * @author 陈阵 
 * @date 2013-8-10 上午9:02:07
 */
public class NDataFormatValidation implements DataFormatValidation {
	
	private ValidationResult validationResult = new ValidationResult();
    

	@Override
	public ValidationResult validation(
			DataFormatValidationPara dataFormatValidationPara) {
		// TODO Auto-generated method stub
		/** 可变长标志位     N..4,1   N..4 **/
        boolean variableLengthFlag = false;
		String startTime = DateUtil.getCurrentDate();
		String dataFormatValidationValue = dataFormatValidationPara.getDataFormatValidationValue();
		String expression = dataFormatValidationPara.getExpression();
		String regx = null;
		
		Pattern pattern1 = Pattern.compile("^N(\\d*)(\\.{0}|\\.{2})(\\d+)\\,{0,1}(\\d*)$");
		Matcher matcher1 = pattern1.matcher(expression);
		if(matcher1.find()){
			boolean hasDot= false;
			boolean hasMin = false;
			boolean minNull = false;
			String minLength = matcher1.group(1);
			String twoDot = matcher1.group(2);
			String maxLength = matcher1.group(3);
			String dotLength = matcher1.group(4);
			if(StringUtils.isNotEmpty(dotLength)){
				hasDot = true;
			}
			if(StringUtils.isNotEmpty(twoDot)){
				hasMin = true;
				variableLengthFlag = true;
			}else{
				maxLength = minLength + maxLength;
			}
			
			if(StringUtils.isEmpty(minLength)){
				minNull = true;
			}
			if(hasDot){
				if(hasMin){
					 if(minNull){
						 int tMaxLength = Integer.valueOf(maxLength) - Integer.valueOf(dotLength) - 1;
						 regx = "[0-9]{1,"+tMaxLength+"}\\.{1}[0-9]{" + dotLength +"}";
					 }else{
						 int tMinLength = Integer.valueOf(minLength) - Integer.valueOf(dotLength) - 1;
						 int tMaxLength = Integer.valueOf(maxLength) - Integer.valueOf(dotLength) - 1;
						 regx = "[0-9]{"+tMinLength+","+tMaxLength+"}\\.{1}[0-9]{" + dotLength +"}";
					 }
				}else{
					int tMaxLength = Integer.valueOf(maxLength) - Integer.valueOf(dotLength) - 1;
					regx = "[0-9]{"+tMaxLength+"}\\.{1}[0-9]{" + dotLength +"}";
				}
			}else{
				if(hasMin){
					 if(minNull){
						 regx = "[0-9]{1,"+maxLength+"}";
					 }else{
						 regx = "[0-9]{"+minLength+","+maxLength+"}"; 
					 }
				}else{
					regx = "[0-9]{"+maxLength+"}";
				}
			}
		}
		if(regx == null){
			String errorMsg = "([格式："+expression+",校验值："+dataFormatValidationValue+")]";
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
			errorMsgDetail.setErrorSubType(DataFormatError.N.getErrorCode());
			errorMsgDetail.setErrorDesc(DataFormatError.N.getErrorMsg() + errorMsg);
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}else{
			if(variableLengthFlag && StringUtils.isBlank(dataFormatValidationValue)){
				
			}else{
				Pattern pattern4 = Pattern.compile("^"+regx+"$");
				Matcher matcher4 = pattern4.matcher(dataFormatValidationValue);
				if (!matcher4.find()) {
					String errorMsg = "([格式：" + expression + ",校验值："
							+ dataFormatValidationValue + ")]";
					String endTime = DateUtil.getCurrentDate();
					ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
					errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
					errorMsgDetail.setStartTime(startTime);
					errorMsgDetail.setEndTime(endTime);
					errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
					errorMsgDetail
							.setErrorSubType(DataFormatError.N.getErrorCode());
					errorMsgDetail.setErrorDesc(DataFormatError.N.getErrorMsg()
							+ errorMsg);
					validationResult.getErrorMsgDetial().add(errorMsgDetail);
					validationResult.setSucess(false);
				}		
			}
		}
		return validationResult;
	}
}

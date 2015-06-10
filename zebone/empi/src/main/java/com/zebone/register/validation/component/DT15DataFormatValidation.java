package com.zebone.register.validation.component;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zebone.register.validation.enu.DataFormatError;
import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.vo.DataFormatValidationPara;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;

/**
 * YYYYMMDDThhmmss
 * 
 * @author 陈阵
 * @date 2013-8-10 上午9:02:07
 */
public class DT15DataFormatValidation implements DataFormatValidation {

	private ValidationResult validationResult = new ValidationResult();

	@Override
	public ValidationResult validation(
			DataFormatValidationPara dataFormatValidationPara) {
		// TODO Auto-generated method stub
		String startTime = DateUtil.getCurrentDate();
		String dataFormatValidationValue = dataFormatValidationPara
				.getDataFormatValidationValue();
		String expression = dataFormatValidationPara.getExpression();

		Pattern pattern1 = Pattern.compile("^\\d{8}T\\d{6}$");
		Matcher matcher1 = pattern1.matcher(dataFormatValidationValue);
		if (matcher1.find()) {
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyMMDDhhmmss");
				simpleDateFormat.parse(dataFormatValidationValue.replace("T",
						""));
			} catch (Exception e) {
				// TODO: handle exception
				String errorMsg = "([格式：" + expression + ",校验值："
						+ dataFormatValidationValue + ")]";
				String endTime = DateUtil.getCurrentDate();
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
				errorMsgDetail.setStartTime(startTime);
				errorMsgDetail.setEndTime(endTime);
				errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
				errorMsgDetail.setErrorSubType(DataFormatError.DT15
						.getErrorCode());
				errorMsgDetail.setErrorDesc(DataFormatError.DT15.getErrorMsg()
						+ errorMsg);
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setSucess(false);
				return validationResult;
			}
		} else {
			String errorMsg = "([格式：" + expression + ",校验值："
					+ dataFormatValidationValue + ")]";
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(dataFormatValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.DATA_FORMAT.getType());
			errorMsgDetail.setErrorSubType(DataFormatError.DT15.getErrorCode());
			errorMsgDetail.setErrorDesc(DataFormatError.DT15.getErrorMsg()
					+ errorMsg);
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}

		return validationResult;
	}

}

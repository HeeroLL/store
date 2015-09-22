package com.zebone.dnode.engine.validation.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zebone.dnode.engine.validation.dto.BusinessValidationPara;
import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.enu.BusinessError;
import com.zebone.dnode.engine.validation.enu.ErrorType;
import com.zebone.util.DateUtil;

/**
 * 手机校验实现类
 * @author 陈阵 
 * @date 2013-8-8 上午10:13:51
 */
public class CellphoneBusinessValidation implements BusinessValidation {

	private ValidationResult validationResult = new ValidationResult();

	@Override
	public ValidationResult validation(BusinessValidationPara businessValidationPara) {
		// TODO Auto-generated method stub
		String startTime = DateUtil.getCurrentDate();
		String businessValidationValue = businessValidationPara.getBusinessValidationValue();
		if (!isCellPhone(businessValidationValue)) {
			String errorMsg = "("+businessValidationValue+")";
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(businessValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.BUSINESS.getType());
			errorMsgDetail.setErrorSubType(BusinessError.CELLPHONE.getErrorCode());
			errorMsgDetail.setErrorDesc(BusinessError.CELLPHONE.getErrorMsg() + errorMsg);
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}

		return validationResult;
	}
    
    
	/**
	 * 判断是否为手机
	 * @param cellPhone
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-8 上午10:17:59
	 */
	private boolean isCellPhone(String cellPhone) {
		boolean isCellPhone = false;
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(cellPhone);
		boolean matches = m.matches();
		if (matches) {
			isCellPhone = true;
		}
		return isCellPhone;
	}

}

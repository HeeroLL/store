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
 * 身份证校验实现类
 * 
 * @author 陈阵
 * @date 2013-8-8 上午10:13:51
 */
public class IdcardBusinessValidation implements BusinessValidation {

	private ValidationResult validationResult = new ValidationResult();

	@Override
	public ValidationResult validation(
			BusinessValidationPara businessValidationPara) {
		// TODO Auto-generated method stub
		String startTime = DateUtil.getCurrentDate();
		String businessValidationValue = businessValidationPara
				.getBusinessValidationValue();
		if (!isIdcard(businessValidationValue)) {
			String errorMsg = "(" + businessValidationValue + ")";
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(businessValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.BUSINESS.getType());
			errorMsgDetail.setErrorSubType(BusinessError.IDCARD.getErrorCode());
			errorMsgDetail.setErrorDesc(BusinessError.IDCARD.getErrorMsg()+ errorMsg);
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}

		return validationResult;
	}

     /*
	 * 判断身份证
	 * @param email
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-8 上午10:03:09
	 */
	private boolean isIdcard(String email) {
		boolean isIdcard = false;
		Pattern p = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
		Matcher m = p.matcher(email);
		boolean matches = m.matches();
		if (matches) {
			isIdcard = true;
		}
		return isIdcard;
	}
}

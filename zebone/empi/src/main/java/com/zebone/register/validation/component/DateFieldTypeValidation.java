package com.zebone.register.validation.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.enu.ValueFieldError;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.FieldTypeValidationPara;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;


/**
 * 日期验证(值域验证)
 * @author 陈阵 
 * @date 2013-7-31 下午2:20:25
 */
public class DateFieldTypeValidation implements FieldTypeValidation {
	
	private ValidationResult validationResult = new ValidationResult();

	private List<String> dateFormatList = new ArrayList<String>();
	
	public DateFieldTypeValidation(){
		initDateFormat();
	}
	
	private void initDateFormat(){
		String dataformat1 = "yyyyMMdd";
	    String dataformat2 = "HHmmss";
	    String dataformat3 = "yyyyMMddHHmmss";
	    String dataformat4 = "yyyyMMddHHmm";
	    dateFormatList.add(dataformat1);
	    dateFormatList.add(dataformat2);
	    dateFormatList.add(dataformat3);
	    dateFormatList.add(dataformat4);
	}

	@SuppressWarnings("unused")
	@Override
	public ValidationResult validation(FieldTypeValidationPara fieldTypeValidationPara) {
		// TODO Auto-generated method stub
		boolean checkDateFlag = false;
		String startTime = DateUtil.getCurrentDate();
		for(String dateFormat:dateFormatList){
			try{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			}catch (Exception e) {
				// TODO: handle exception
				checkDateFlag = true;
				break;
			}
		}
		if(checkDateFlag){
			String endTime = DateUtil.getCurrentDate();
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setDocXpath(fieldTypeValidationPara.getXpath());
			errorMsgDetail.setStartTime(startTime);
			errorMsgDetail.setEndTime(endTime);
			errorMsgDetail.setErrorType(ErrorType.VALUE_FIELD.getType());
			errorMsgDetail.setErrorSubType(ValueFieldError.DATE_FORMAT.getErrorCode());
			errorMsgDetail.setErrorDesc(ValueFieldError.DATE_FORMAT.getErrorMsg());
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);
		}

		return validationResult;
	}

}

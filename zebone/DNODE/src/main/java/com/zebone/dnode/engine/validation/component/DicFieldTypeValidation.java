package com.zebone.dnode.engine.validation.component;

import java.util.List;

import com.zebone.dnode.engine.validation.domain.DicType;
import com.zebone.dnode.engine.validation.domain.DicValue;
import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.FieldTypeValidationPara;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.enu.ErrorType;
import com.zebone.dnode.engine.validation.enu.ValueFieldError;
import com.zebone.dnode.engine.validation.repository.MasterDataRepository;


/**
 * 字典类型验证(值域)
 * @author 陈阵 
 * @date 2013-7-31 下午2:34:29
 */
public class DicFieldTypeValidation implements FieldTypeValidation {
    
	private MasterDataRepository masterDataRepository;
	
	private ValidationResult validationResult = new ValidationResult();

	
	public DicFieldTypeValidation(MasterDataRepository masterDataRepository) {
		this.masterDataRepository = masterDataRepository;
	}

	@Override
	public ValidationResult validation(FieldTypeValidationPara fieldTypeValidationPara) {
		// TODO Auto-generated method stub
		/** 要检验的值 **/
		String validationValue = fieldTypeValidationPara.getFieldTypeValue();
		String dicTypeId = fieldTypeValidationPara.getDicTypeId();
		List<DicValue> dicValueList = masterDataRepository.getDicByDicTypeId(dicTypeId);
		if(!dicValueList.isEmpty()){
			if(!containsValue(validationValue,dicValueList)){
				DicType dicType  =  masterDataRepository.getDicTypeByTypeId(dicTypeId);
				String typeName = dicType.getTypeName();
				String errorMsg =  "[(字典类型名字:"+typeName+","+dicValueList+",字典编码:"+validationValue+")]";
				ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
				errorMsgDetail.setErrorType(ErrorType.VALUE_FIELD.getType());
				errorMsgDetail.setErrorSubType(ValueFieldError.DICTYPE_NOT_FIND.getErrorCode());
				errorMsgDetail.setErrorDesc(ValueFieldError.DICTYPE_NOT_FIND.getErrorMsg() + errorMsg);
				errorMsgDetail.setDocXpath(fieldTypeValidationPara.getXpath());
				validationResult.getErrorMsgDetial().add(errorMsgDetail);
				validationResult.setSucess(false);
			}
		}else{
			String errorMsg = "[(字典类型dicTypeId:"+dicTypeId+")]";
			ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
			errorMsgDetail.setErrorType(ErrorType.VALUE_FIELD.getType());
			errorMsgDetail.setErrorSubType(ValueFieldError.DIC_NOT_FIND.getErrorCode());
			errorMsgDetail.setErrorDesc(ValueFieldError.DIC_NOT_FIND.getErrorMsg() + errorMsg);
			errorMsgDetail.setDocXpath(fieldTypeValidationPara.getXpath());
			validationResult.getErrorMsgDetial().add(errorMsgDetail);
			validationResult.setSucess(false);

		}
		return validationResult;
	}
	
	/**
	 * 检查字典是否存在该值
	 * @param validationValue
	 * @param dicValueList
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-7 下午1:42:26
	 */
	private boolean containsValue(String validationValue,List<DicValue> dicValueList){
		for(DicValue dicValue : dicValueList){
		    String dicCode = dicValue.getDicCode();
		    if(validationValue.equals(dicCode)){
		    	return true;
		    }
		}
		return false;
	}

}

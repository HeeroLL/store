package com.zebone.dnode.engine.validation.component;


import com.zebone.dnode.engine.validation.domain.MasterData;
import com.zebone.dnode.engine.validation.dto.ErrorMsg;
import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.FieldTypeValidationPara;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.enu.ErrorType;
import com.zebone.dnode.engine.validation.enu.ValueFieldError;
import com.zebone.dnode.engine.validation.mapper.DataCenterMapper;
import com.zebone.dnode.engine.validation.mapper.MasterDataMapper;
import com.zebone.util.DateUtil;


/**
 * 主数据验证(值域)
 * @author 陈阵 
 * @date 2013-8-1 上午10:41:12
 */
public class MasterDataValidation implements FieldTypeValidation {
	
	
	/** 主数据mapper **/
	private MasterDataMapper masterDataMapper;
	
	/** 数据中心mapper **/
	private DataCenterMapper dataCenterMapper;

	private ValidationResult validationResult = new ValidationResult();

	
	public MasterDataValidation(MasterDataMapper masterDataMapper, DataCenterMapper dataCenterMapper){
		this.masterDataMapper = masterDataMapper;
		this.dataCenterMapper = dataCenterMapper;
	}


	@Override
	public ValidationResult validation(FieldTypeValidationPara fieldTypeValidationPara) {
		// TODO Auto-generated method stub
		String startTime = DateUtil.getCurrentDate();
		String xPath = fieldTypeValidationPara.getXpath();
		/** 主数据表中的主键 **/
		String masterDataId = fieldTypeValidationPara.getMasterDataId();
		/** 检查的值  **/
		String fieldTypeValue = fieldTypeValidationPara.getFieldTypeValue();
		MasterData masterData = masterDataMapper.getTableFieldById(masterDataId);
		if(masterData == null){
			String errorMsg = "([P_MASTER_DATA ID_:"+masterDataId+"])";
			ErrorMsgDetail errorMsgDetial = new ErrorMsgDetail();
			errorMsgDetial.setErrorType(ErrorType.VALUE_FIELD.getType());
			errorMsgDetial.setErrorSubType(ValueFieldError.MASTERDATA_NOT_FIND.getErrorCode());
			errorMsgDetial.setErrorDesc(ValueFieldError.MASTERDATA_NOT_FIND.getErrorMsg()+errorMsg);
			errorMsgDetial.setStartTime(startTime);
			errorMsgDetial.setEndTime(DateUtil.getCurrentDate());
			errorMsgDetial.setDocXpath(xPath);
			validationResult.getErrorMsgDetial().add(errorMsgDetial);
			validationResult.setSucess(false);
		}else{
			int result = masterDataMapper.getMasterCheck(masterData.getTableName(), masterData.getFieldName(), fieldTypeValue);
			if(result == 0){
				String errorMsg = "([表:"+masterData.getTableName()+",列:"+masterData.getFieldName()+",值:"+ fieldTypeValue+"])";
				ErrorMsgDetail errorMsgDetial = new ErrorMsgDetail();
				errorMsgDetial.setErrorType(ErrorType.VALUE_FIELD.getType());
				errorMsgDetial.setErrorSubType(ValueFieldError.MASTERDATA_NONEXISTENCE.getErrorCode());
				errorMsgDetial.setErrorDesc(ValueFieldError.MASTERDATA_NONEXISTENCE.getErrorMsg() + errorMsg);
				errorMsgDetial.setStartTime(startTime);
				errorMsgDetial.setEndTime(DateUtil.getCurrentDate());
				errorMsgDetial.setDocXpath(xPath);
				validationResult.getErrorMsgDetial().add(errorMsgDetial);
				validationResult.setSucess(false);
			}
		}
		String endTime = DateUtil.getCurrentDate();
		ErrorMsg errorMsg = validationResult.getErrorMsg();
		errorMsg.setStartTime(startTime);
		errorMsg.setEndTime(endTime);
		return validationResult;
	}

}

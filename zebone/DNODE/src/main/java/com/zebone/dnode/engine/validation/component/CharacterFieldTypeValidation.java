package com.zebone.dnode.engine.validation.component;


import com.zebone.dnode.engine.validation.dto.FieldTypeValidationPara;
import com.zebone.dnode.engine.validation.dto.ValidationResult;





/**
 *  字符验证(值域验证)
 * @author 陈阵 
 * @date 2013-7-31 下午2:25:49
 */
public class CharacterFieldTypeValidation implements FieldTypeValidation {
    
	private ValidationResult validationResult = new ValidationResult();

	public CharacterFieldTypeValidation(){
	}
	
	
	@Override
	public ValidationResult validation(FieldTypeValidationPara fieldTypeValidationPara) {
		// TODO Auto-generated method stub
		return validationResult;
	}

}

package com.zebone.dnode.engine.validation.component;

import com.zebone.dnode.engine.validation.dto.BusinessValidationPara;
import com.zebone.dnode.engine.validation.dto.ValidationResult;


/**
 * 业务校验接口
 * @author 陈阵 
 * @date 2013-8-8 上午9:44:46
 */
public interface BusinessValidation {
	
	/**
	 * 业务校验接口
	 * @param businessValidationPara
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-8 上午10:13:26
	 */
	ValidationResult validation(BusinessValidationPara businessValidationPara);

}

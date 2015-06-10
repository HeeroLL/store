package com.zebone.register.validation.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.register.validation.dao.PSysregisterMapper;
import com.zebone.register.validation.service.JurisdictionValidationService;
import com.zebone.register.validation.vo.ValidationResult;

@Service
public class JurisdictionValidationServiceImpl implements JurisdictionValidationService {

	@Resource
	private PSysregisterMapper pSysregisterMapper;
	
	/**
	 * 校验是否有权限上传文档
	 */
	@Override
	public ValidationResult validation(String sysCode,String docOrgCode) {
		int result = pSysregisterMapper.getJurisdiction(sysCode,docOrgCode);
		ValidationResult validationResult = new ValidationResult();
		if(result>0){
			validationResult.setSucess(true);
		}else{
			validationResult.setSucess(false);
		}
		return validationResult;
	}

}

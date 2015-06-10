package com.zebone.register.validation.service;

import org.dom4j.Document;

import com.zebone.register.validation.vo.ValidationResult;

public interface JurisdictionValidationService {

	/**
	 * 
	 * @param sysCode
	 * @param docOrgCode
     * @param docOrgCode 文档来源机构
	 * @return
	 * @author 林彬
	 * @date 2013-8-20 上午8:17:18
	 */
	public ValidationResult validation(String sysCode, String docOrgCode);
}


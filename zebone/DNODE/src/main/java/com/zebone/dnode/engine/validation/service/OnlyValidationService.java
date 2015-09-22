package com.zebone.dnode.engine.validation.service;

import org.dom4j.Document;

import com.zebone.dnode.engine.validation.dto.ValidationResult;


/**
 * 唯一性校验
 * @author 陈阵 
 * @date 2013-8-20 上午8:16:37
 */
public interface OnlyValidationService {
	
	/**
	 * 
	 * @param docId
	 * @param xmlDataDocument
     * @param docOrgCode 文档来源机构
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-20 上午8:17:18
	 */
	public ValidationResult validation(String docId, Document xmlDataDocument, String docOrgCode);
}

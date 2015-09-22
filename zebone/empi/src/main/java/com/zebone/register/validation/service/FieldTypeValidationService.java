package com.zebone.register.validation.service;

import org.dom4j.Document;

import com.zebone.register.validation.vo.ValidationResult;



/**
 * 值域类型检查service
 * @author 陈阵 
 * @date 2013-7-31 上午10:48:37
 */
public interface FieldTypeValidationService {
	
	
	/**
	 * 值域验证
	 * @param docId  文档主键
	 * @param xmlDataDocument	校验的文档数据document
	 * @author 陈阵 
	 * @date 2013-7-31 上午10:56:39
	 */
	public ValidationResult validation(String docId, Document xmlDataDocument);

}

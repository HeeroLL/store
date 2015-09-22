package com.zebone.dnode.engine.validation.service;

import org.dom4j.Document;

import com.zebone.dnode.engine.validation.dto.ValidationResult;


/**
 * 业务校验service接口
 * @author 陈阵 
 * @date 2013-8-8 下午1:26:31
 */
public interface BusinessValidationService {
	
	
	/**
	 * @param docId  文档主键
	 * @param xmlDataDocument	校验的文档数据document
	 * @author 陈阵 
	 * @date 2013-7-31 上午10:56:39
	 */
	public ValidationResult validation(String docId, Document xmlDataDocument);
}

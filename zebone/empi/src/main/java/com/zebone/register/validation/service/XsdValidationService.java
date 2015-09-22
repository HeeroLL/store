package com.zebone.register.validation.service;


import org.dom4j.Document;

import com.zebone.register.validation.vo.ValidationResult;




/**
 * XSD校验service
 * @author 陈阵 
 * @date 2013-7-29 上午10:06:29
 */
public interface XsdValidationService {
	
    /**
     * @param docId  文档主键
     * @param xmlData  校验的文档数据document
     * @param checkRepeat 重复性
     * @param checkSelect 可选性
     * @return ValidationResult 校验结果
     * @author 陈阵 
     * @date 2013-7-30 上午9:18:27
     */
	public ValidationResult validation(String docId,String docVersion,Document xmlDataDocument,String checkRepeat, String checkSelect);
	
	
	/**
	 * 验证xml的合法性
	 * @param xmlData
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-12 下午4:25:53
	 */
	public ValidationResult validationDocument(String xmlData);
	
}

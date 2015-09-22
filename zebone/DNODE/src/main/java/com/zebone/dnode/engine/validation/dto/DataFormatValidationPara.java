package com.zebone.dnode.engine.validation.dto;


/**
 * 数据格式校验参数
 * @author 陈阵 
 * @date 2013-8-8 上午9:45:32
 */
public class DataFormatValidationPara {
	
	/** 业务校验值  **/
	private String dataFormatValidationValue;
	
	/** xpath **/
	private String xpath;
	
	/** 表达式  **/
	private String expression;

	public String getDataFormatValidationValue() {
		return dataFormatValidationValue;
	}

	public void setDataFormatValidationValue(String dataFormatValidationValue) {
		this.dataFormatValidationValue = dataFormatValidationValue;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	
}

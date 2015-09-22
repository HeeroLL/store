package com.zebone.dnode.engine.validation.domain;

import org.apache.ibatis.type.Alias;

/**
 * 校验配置
 * @author 陈阵 
 * @date 2013-8-12 上午11:07:23
 */
@Alias("checkConfig")
public class CheckConfig {
	
	/** 文档类型ID **/
	private String docId;
	
	/** 可选性开关 **/
	private String isSelect = "1";
	
	/** 重复性  **/
	private String isRepeat = "1";
	
	/** 数据格式开关  **/
	private String isDataFormat = "1";
	
	/** 业务格式  **/
	private String isBusinessFormat = "1";
	
	/** 维一性开关  **/
	private String isOnly = "1";
	
	/** 值域检查开关  **/
	private String isValue = "1";

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getIsDataFormat() {
		return isDataFormat;
	}

	public void setIsDataFormat(String isDataFormat) {
		this.isDataFormat = isDataFormat;
	}

	public String getIsBusinessFormat() {
		return isBusinessFormat;
	}

	public void setIsBusinessFormat(String isBusinessFormat) {
		this.isBusinessFormat = isBusinessFormat;
	}

	public String getIsOnly() {
		return isOnly;
	}

	public void setIsOnly(String isOnly) {
		this.isOnly = isOnly;
	}

	public String getIsValue() {
		return isValue;
	}

	public void setIsValue(String isValue) {
		this.isValue = isValue;
	}
	

}

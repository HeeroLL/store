package com.zebone.dnode.engine.validation.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 文档数据映射
 * @author 陈阵 
 * @date 2013-7-31 上午9:21:37
 */

@Alias("docMapping")
public class DocMapping implements Serializable{

	private static final long serialVersionUID = -4225528548581760766L;
    
	/** 对应的数据元主键id **/
    private String fieldId;
    
    /** 对应的列id **/
    private String cloumnId;
    
    /** 维一性 **/
    private String isOnly;
    
	public DocMapping() {
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getCloumnId() {
		return cloumnId;
	}

	public void setCloumnId(String cloumnId) {
		this.cloumnId = cloumnId;
	}

	public String getIsOnly() {
		return isOnly;
	}

	public void setIsOnly(String isOnly) {
		this.isOnly = isOnly;
	}
	
	
	
}

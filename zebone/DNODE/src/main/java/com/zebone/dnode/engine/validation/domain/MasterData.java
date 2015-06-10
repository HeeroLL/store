package com.zebone.dnode.engine.validation.domain;

import org.apache.ibatis.type.Alias;


/**
 * 主数据
 * @author 陈阵 
 * @date 2013-8-1 上午9:58:01
 */
@Alias("masterData")
public class MasterData {
	
	/** 主数据对应的表名   **/
	private String tableName;
    
	/** 主数据对应的列名 **/
	private String fieldName = "MD_CODE";

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	

}

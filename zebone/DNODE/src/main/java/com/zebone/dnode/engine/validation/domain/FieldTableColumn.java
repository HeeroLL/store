package com.zebone.dnode.engine.validation.domain;

import org.apache.ibatis.type.Alias;


/**
 * 文档数据映射对应的表、列
 * @author 陈阵 
 * @date 2013-8-27 下午1:23:23
 */
@Alias("fieldTableColumn")
public class FieldTableColumn {
	
	private String tableName;
	
	private String columnName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	
}

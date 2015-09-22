package com.zebone.dnode.etl.adapter.vo;

/**
 * 节点字段对应类
 */

public class NodeColumnInfo {
	/**字段标识*/
	private String columnId;
	/**字段名*/
	private String columnName;
	/**表标识*/
	private String tableId;
	/**文档路径*/
	private String path;
	/**元数据标识*/
	private String fieldId;
	/**查询的数据内容*/
	private String code;
	/**查询的数据需展示的内容*/
	private String displayName;
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}

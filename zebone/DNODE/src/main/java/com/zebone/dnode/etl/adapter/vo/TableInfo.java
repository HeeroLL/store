package com.zebone.dnode.etl.adapter.vo;

/**
 *某张表的信息内容
 */
public class TableInfo {
	/**表名称*/
	private String tableName;
	/**表标识*/
	private String tableId;
	/**主表标识*/
	private String tId;
	/**主表名称*/
	private String tName;
	/**外键字段*/
	private String colmn;
	/**外键字段标识*/
	private String columnId;
	/**主表关联字段标识*/
	private String colId;
	/**主表关联字段*/
	private String col;
	public String getColmn() {
		return colmn;
	}
	public void setColmn(String colmn) {
		this.colmn = colmn;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColId() {
		return colId;
	}
	public void setColId(String colId) {
		this.colId = colId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getTId() {
		return tId;
	}
	public void setTId(String id) {
		tId = id;
	}
	public String getTName() {
		return tName;
	}
	public void setTName(String name) {
		tName = name;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
}

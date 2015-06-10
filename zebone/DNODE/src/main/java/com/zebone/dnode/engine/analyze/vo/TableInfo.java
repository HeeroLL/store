package com.zebone.dnode.engine.analyze.vo;

import java.util.Date;

/**
 * 业务表信息
 * @author songjunjie
 * @version 2013-8-16 上午11:16:43
 */
public class TableInfo {
	private String  id;
	private String  tableName;
	private String  tableDesc;
	private String  tableContent;
	private String  tableType;
	private Date  createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	public String getTableContent() {
		return tableContent;
	}
	public void setTableContent(String tableContent) {
		this.tableContent = tableContent;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

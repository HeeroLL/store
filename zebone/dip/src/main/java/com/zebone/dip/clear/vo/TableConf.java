package com.zebone.dip.clear.vo;
/**
 * 表配置管理
 */
public class TableConf {
	/**主键ID*/
	private String id;
	/**表名*/
	private String tableName;
	/**表描述*/
	private String tableDesc;
	/**表类型（源数据层，报表层等）*/
	private String tableType;
	/**表应用数据源ID*/
	private String dsId;
	/**是否已删除过的*/
	private String isDeleted;

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted == null ? null : isDeleted.trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName == null ? null : tableName.trim();
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc == null ? null : tableDesc.trim();
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType == null ? null : tableType.trim();
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId == null ? null : dsId.trim();
	}
}
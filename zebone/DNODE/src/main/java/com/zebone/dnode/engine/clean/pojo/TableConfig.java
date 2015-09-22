package com.zebone.dnode.engine.clean.pojo;

/**
 * 表配置管理
 * P_TABLE_CONF
 * @author cz
 *
 */
public class TableConfig {
	
	private String id;
	
	/** 清洗的表名  **/
	private String tableName;
		
	/** 清洗的表备注 **/
	private String tableDesc;
	
	/** 表类型（源数据层，报表层等）**/
	private String tableType;
		
	/** 对应的数据源  **/
	private DsObj dsObj;

	public TableConfig() {
		super();
	}

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

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public DsObj getDsObj() {
		return dsObj;
	}

	public void setDsObj(DsObj dsObj) {
		this.dsObj = dsObj;
	}
	
	
	
}

package com.zebone.dnode.engine.clean.pojo;

/**
 * 清洗运行错误日志表
 * @author cz
 *
 */
public class ClearLog {
   
	private String id;
	
	/** 表名称 **/
	private String tableName;
	
	/** 列清洗类型（自定义清洗0，字典配置1，数据格式转化2，业务校验3） **/
	private String clearType;
	
	/** 列名称 **/
	private String cloumnName;
	
	
	/** 异常码  **/
	private String errCode;
	
	/** 异常描述 **/
	private String errDesc;
	
	
	/** 清洗表主键ID **/
	private String clearId;

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

	public String getClearType() {
		return clearType;
	}

	public void setClearType(String clearType) {
		this.clearType = clearType;
	}

	public String getCloumnName() {
		return cloumnName;
	}

	public void setCloumnName(String cloumnName) {
		this.cloumnName = cloumnName;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}
	
	
	


}

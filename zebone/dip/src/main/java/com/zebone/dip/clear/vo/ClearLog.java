package com.zebone.dip.clear.vo;
/**
 * 清洗运行错误日志表
 */
public class ClearLog {
	/**主键ID*/
	private String id;
	/**表名称*/
	private String tableName;
	/**列清洗类型（自定义清洗0，字典配置1，数据格式转化2，业务校验3）*/
	private String claerType;
	/**列名称*/
	private String cloumnName;
	/**异常码*/
	private String errCode;
	/**异常描述*/
	private String errDesc;
	/**清洗表主键ID*/
	private String clearId;

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

	public String getClaerType() {
		return claerType;
	}

	public void setClaerType(String claerType) {
		this.claerType = claerType == null ? null : claerType.trim();
	}

	public String getCloumnName() {
		return cloumnName;
	}

	public void setCloumnName(String cloumnName) {
		this.cloumnName = cloumnName == null ? null : cloumnName.trim();
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode == null ? null : errCode.trim();
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc == null ? null : errDesc.trim();
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId == null ? null : clearId.trim();
	}
}
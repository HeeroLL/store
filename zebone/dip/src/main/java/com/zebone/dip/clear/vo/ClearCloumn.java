package com.zebone.dip.clear.vo;
/**
 * 清洗列配置项
 */
public class ClearCloumn {
	/**主键ID*/
	private String id;
	/**清洗配置主表ID*/
	private String clearId;
	/**列名称*/
	private String cloumnName;
	/**列清洗类型（自定义清洗0，字典配置1，数据格式转化2，业务校验3）*/
	private String clearType;
	/**配置内容（类名，关联主键ID,数据格式类型，业务校验类型）*/
	private String clearContent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId == null ? null : clearId.trim();
	}

	public String getCloumnName() {
		return cloumnName;
	}

	public void setCloumnName(String cloumnName) {
		this.cloumnName = cloumnName == null ? null : cloumnName.trim();
	}

	public String getClearType() {
		return clearType;
	}

	public void setClearType(String clearType) {
		this.clearType = clearType == null ? null : clearType.trim();
	}

	public String getClearContent() {
		return clearContent;
	}

	public void setClearContent(String clearContent) {
		this.clearContent = clearContent == null ? null : clearContent.trim();
	}
}
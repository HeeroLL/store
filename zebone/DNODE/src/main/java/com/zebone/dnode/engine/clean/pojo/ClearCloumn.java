package com.zebone.dnode.engine.clean.pojo;


/**
 * 清洗列配置项
 * P_CLEAR_CLOUMN
 * @author cz
 *
 */
public class ClearCloumn {
   
	private String id;
	
	private String clearId;
	
	/** 列名称 **/
	private String cloumnName; 
	
	/** 列清洗类型（自定义清洗0，字典配置1，数据格式转化2，业务校验3）**/
	private String clearType;
	
	/** 配置内容（类名，关联主键ID,数据格式类型，业务校验类型）**/
	private String clearContent;

	public ClearCloumn() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public String getCloumnName() {
		return cloumnName;
	}

	public void setCloumnName(String cloumnName) {
		this.cloumnName = cloumnName;
	}

	public String getClearType() {
		return clearType;
	}

	public void setClearType(String clearType) {
		this.clearType = clearType;
	}

	public String getClearContent() {
		return clearContent;
	}

	public void setClearContent(String clearContent) {
		this.clearContent = clearContent;
	}
	
	
	
}

package com.zebone.btp.app.dict.pojo;

import java.io.Serializable;

public class DictionaryType implements Serializable{
	
	/** 字典类型id */
	private String typeId;

	/** 字典类型名称 */
	private String typeName;

	/** 字典类型编码 */
	private String typeCode;

	/** 字典类型说明 */
	private String remark;

	/** 删除标志（0：未删除 1：删除） */
	private Integer isDeleted;

	/** 父类型id */
	private String parentId;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}

package com.zebone.btp.mdm.vo;

import java.io.Serializable;

/**
 * 增加字段对应的对象
 * @author ouyangxin
 *
 * CreateDate: 2012-12-20
 */
public class MdtProperty implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 字段名*/
	private String field;
	/** 类型*/
	private String fieldType;
	/** 字段是否可见*/
	private String visible;
	/** 字段释义*/
	private String comment;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * toString
	 */
	public String toString() {
		final String tab = "";
		String retValue = "";
		retValue = "MdtProperty ( " + super.toString() + tab + "field = "
				+ this.field + tab + "fieldType = " + this.fieldType + tab
				+ "visible = " + this.visible + tab + "comment = "
				+ this.comment + tab + " )";

		return retValue;
	}

}

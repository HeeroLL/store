package com.zebone.btp.mdm.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 主数据类型
 * 
 * @author ouyangxin
 */
public class MainDataTypeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主数据类型ID */
	private String mdtId;
	/** 主数据类型CODE */
	private String mdtCode;
	/** 主数据类型名称 */
	private String mdtName;
	/** 主数据类型种类 */
	private String mdtType;
	/** 主数据类型对应表名 */
	private String mdtTablename;
	/** 表字段名以逗号分隔的字符串 */
	private String mdtFields;
	/** 表字段描述以逗号分隔的字符串 */
	private String mdtComments;
	/** 表字段类型以逗号分隔的字符串 */
	private String mdtFieldsType;
	/** 字段是否显示 */
	private String mdtVisible;
	/** 删除标志 */
	private String mdtDel;
	/** 字段对应的属性值 */
	private String mdtCorres;
	/** 父类型id */
	private String mdtParentId;
	/** 接受页面字段传值 */
	private String[] field;
	private String[] fieldType;
	private String[] comment;
	private String[] visible;
	/** 构造创表语句 */
	private String sqlCreate;
	/** 构造创表注解语句 */
	private String sqlComments;
	/** 创表语句結果 */
	private String sqlresult;
	/** 字段对应对象集合 */
	private List<MdtProperty> fieldList;

	public String getMdtId() {
		return mdtId;
	}

	public void setMdtId(String mdtId) {
		this.mdtId = mdtId;
	}

	public String getMdtCode() {
		return mdtCode;
	}

	public void setMdtCode(String mdtCode) {
		this.mdtCode = mdtCode;
	}

	public String getMdtName() {
		return mdtName;
	}

	public void setMdtName(String mdtName) {
		this.mdtName = mdtName;
	}

	public String getMdtType() {
		return mdtType;
	}

	public void setMdtType(String mdtType) {
		this.mdtType = mdtType;
	}

	public String getMdtTablename() {
		return mdtTablename;
	}

	public void setMdtTablename(String mdtTablename) {
		this.mdtTablename = mdtTablename;
	}

	public String getMdtFields() {
		return mdtFields;
	}

	public void setMdtFields(String mdtFields) {
		this.mdtFields = mdtFields;
	}

	public String getMdtComments() {
		return mdtComments;
	}

	public void setMdtComments(String mdtComments) {
		this.mdtComments = mdtComments;
	}

	public String getMdtFieldsType() {
		return mdtFieldsType;
	}

	public void setMdtFieldsType(String mdtFieldsType) {
		this.mdtFieldsType = mdtFieldsType;
	}

	public String getMdtVisible() {
		return mdtVisible;
	}

	public void setMdtVisible(String mdtVisible) {
		this.mdtVisible = mdtVisible;
	}

	public String[] getField() {
		return field;
	}

	public void setField(String[] field) {
		this.field = field;
	}

	public String[] getFieldType() {
		return fieldType;
	}

	public void setFieldType(String[] fieldType) {
		this.fieldType = fieldType;
	}

	public String[] getComment() {
		return comment;
	}

	public void setComment(String[] comment) {
		this.comment = comment;
	}

	public String[] getVisible() {
		return visible;
	}

	public void setVisible(String[] visible) {
		this.visible = visible;
	}

	public String getSqlCreate() {
		return sqlCreate;
	}

	public void setSqlCreate(String sqlCreate) {
		this.sqlCreate = sqlCreate;
	}

	public String getSqlComments() {
		return sqlComments;
	}

	public void setSqlComments(String sqlComments) {
		this.sqlComments = sqlComments;
	}

	public String getSqlresult() {
		return sqlresult;
	}

	public void setSqlresult(String sqlresult) {
		this.sqlresult = sqlresult;
	}

	public String getMdtDel() {
		return mdtDel;
	}

	public void setMdtDel(String mdtDel) {
		this.mdtDel = mdtDel;
	}

	public String getMdtCorres() {
		return mdtCorres;
	}

	public void setMdtCorres(String mdtCorres) {
		this.mdtCorres = mdtCorres;
	}

	public List<MdtProperty> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<MdtProperty> fieldList) {
		this.fieldList = fieldList;
	}

	public String getMdtParentId() {
		return mdtParentId;
	}

	public void setMdtParentId(String mdtParentId) {
		this.mdtParentId = mdtParentId;
	}

	/**
	 * toString
	 */
	public String toString() {
		final String tab = "";
		String retValue = "";
		retValue = "MainDataTypeVO ( " + super.toString() + tab + "mdtId = "
				+ this.mdtId + tab + "mdtCode = " + this.mdtCode + tab
				+ "mdtName = " + this.mdtName + tab + "mdtType = "
				+ this.mdtType + tab + "mdtTablename = " + this.mdtTablename
				+ tab + "mdtFields = " + this.mdtFields + tab
				+ "mdtComments = " + this.mdtComments + tab
				+ "mdtFieldsType = " + this.mdtFieldsType + tab
				+ "mdtVisible = " + this.mdtVisible + tab + "mdtDel = "
				+ this.mdtDel + tab + "mdtCorres = " + this.mdtCorres + tab
				+ "mdtParentId = " + this.mdtParentId + tab + "field = "
				+ this.field + tab + "fieldType = " + this.fieldType + tab
				+ "comment = " + this.comment + tab + "visible = "
				+ this.visible + tab + "sqlCreate = " + this.sqlCreate + tab
				+ "sqlComments = " + this.sqlComments + tab + "sqlresult = "
				+ this.sqlresult + tab + "fieldList = " + this.fieldList + tab
				+ " )";

		return retValue;
	}

}

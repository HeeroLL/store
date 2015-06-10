package com.zebone.btp.app.dict.pojo;

import java.io.Serializable;

public class Dictionary implements Serializable{
	/** 字典id */
	private String dictId;
	
	/** 字典名称 */	
	private String dictName;

	/** 字典编码 */
	private String dictCode;

	/** 字典拼音 */
	private String namePinyin;

	/** 字典简拼 */
	private String nameJianpin;

	/** 字典说明 */
	private String remark;

	/** 字典类型id */
	private String dicttypeId;

	/** 是否删除（0：未删除  1：删除） */
	private Integer isDeleted;
	
	/** 排序号 */
	private Integer orderNo;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}

	public String getNameJianpin() {
		return nameJianpin;
	}

	public void setNameJianpin(String nameJianpin) {
		this.nameJianpin = nameJianpin;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDicttypeId() {
		return dicttypeId;
	}

	public void setDicttypeId(String dicttypeId) {
		this.dicttypeId = dicttypeId;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}	

}

package com.zebone.dip.dictionary.vo;
/**
 * 标准数据字典信息
 */
public class PDictionary {
	/**id标识*/
	private String dictId;
	/**字典名字*/
	private String dictName;
	/**字典的编码*/
	private String dictCode;
	/**名字拼音*/
	private String namePinyin;
	/**名字简拼*/
	private String nameJianpin;
	/**说明*/
	private String remark;
	/**字典类型ID*/
	private String dicttypeId;
	/**是否删除*/
	private String isDeleted;
	/**排序号*/
	private Short orderNo;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId == null ? null : dictId.trim();
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName == null ? null : dictName.trim();
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode == null ? null : dictCode.trim();
	}

	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin == null ? null : namePinyin.trim();
	}

	public String getNameJianpin() {
		return nameJianpin;
	}

	public void setNameJianpin(String nameJianpin) {
		this.nameJianpin = nameJianpin == null ? null : nameJianpin.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getDicttypeId() {
		return dicttypeId;
	}

	public void setDicttypeId(String dicttypeId) {
		this.dicttypeId = dicttypeId == null ? null : dicttypeId.trim();
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted == null ? null : isDeleted.trim();
	}

	public Short getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Short orderNo) {
		this.orderNo = orderNo;
	}
}
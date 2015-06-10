package com.zebone.dip.dictionary.vo;
/**
 * 数据字典信息
 */
public class PDictValue {
	/**id标识*/
	private String valueId;
	/**字典名字*/
	private String dictName;
	/**字典的编码*/
	private String dictCode;
	/**说明*/
	private String remark;
	/**字典类型ID*/
	private String dicttypeId;
	/**标准数据字典匹配项ID*/
	private String dictId;
	/**标准数据字典匹配编码*/
	private String dictBaseCode;
	/**是否删除*/
	private String isDeleted;

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId == null ? null : valueId.trim();
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

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId == null ? null : dictId.trim();
	}

	public String getDictBaseCode() {
		return dictBaseCode;
	}

	public void setDictBaseCode(String dictBaseCode) {
		this.dictBaseCode = dictBaseCode == null ? null : dictBaseCode.trim();
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted == null ? null : isDeleted.trim();
	}
}
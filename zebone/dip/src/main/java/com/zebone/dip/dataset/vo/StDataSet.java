package com.zebone.dip.dataset.vo;
/**
 * 标准数据集管理 类
 */
public class StDataSet {
	/**ID*/
	private String id;
	/**数据元标识符*/
	private String pCode;
	/**数据元名称*/
	private String pName;
	/**定义*/
	private String pDesc;
	/**数据类型*/
	private String pType;
	/**表示格式*/
	private String pFormat;
	/**数据元允许值*/
	private String pValue;
	/**值类型（枚举型 D1，GB/T D2，CV型 D3，主数据编码 M，机构代码等 J）*/
	private String pValueType;
	/**删除标识*/
	private String delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode == null ? null : pCode.trim();
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName == null ? null : pName.trim();
	}

	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc == null ? null : pDesc.trim();
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType == null ? null : pType.trim();
	}

	public String getpFormat() {
		return pFormat;
	}

	public void setpFormat(String pFormat) {
		this.pFormat = pFormat == null ? null : pFormat.trim();
	}

	public String getpValue() {
		return pValue;
	}

	public void setpValue(String pValue) {
		this.pValue = pValue == null ? null : pValue.trim();
	}

	public String getpValueType() {
		return pValueType;
	}

	public void setpValueType(String pValueType) {
		this.pValueType = pValueType == null ? null : pValueType.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}
}
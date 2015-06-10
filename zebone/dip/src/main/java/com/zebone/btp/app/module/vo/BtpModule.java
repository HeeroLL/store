package com.zebone.btp.app.module.vo;

/**
 *模块管理信息 基类
 *@author 蔡祥龙
 *2012-11-22
 */
public class BtpModule implements java.io.Serializable {

	/**模块ID*/
	private String moduleId;
	/**上级模块ID*/
	private String parentModuleId;
	/**名称*/
	private String moduleName;
	/**名称拼音*/
	private String moduleNameSpell;
	/**大图标图标*/
	private String maxicon;
	/**小图标图标*/
	private String minicon;
	/**模块排序号*/
	private Integer orderNo;
	/**模块类型代码*/
	private Integer typeCode;
	/**层级码*/
	private String levelCode;
	/**URL地址*/
	private String url;
	/**备注*/
	private String remark;
	/**删除标志*/
	private Integer isDeleted;
	/**是否为系统*/
	private Integer isSys;

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getParentModuleId() {
		return this.parentModuleId;
	}

	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleNameSpell() {
		return this.moduleNameSpell;
	}

	public void setModuleNameSpell(String moduleNameSpell) {
		this.moduleNameSpell = moduleNameSpell;
	}

	public String getMaxicon() {
		return this.maxicon;
	}

	public void setMaxicon(String maxicon) {
		this.maxicon = maxicon;
	}

	public String getMinicon() {
		return this.minicon;
	}

	public void setMinicon(String minicon) {
		this.minicon = minicon;
	}

	public String getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsSys() {
		return isSys;
	}

	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}

}
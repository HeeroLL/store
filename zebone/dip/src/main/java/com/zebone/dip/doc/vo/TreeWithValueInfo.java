/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Jun 29, 2013		文档上传所需对象
 */
package com.zebone.dip.doc.vo;

import java.io.Serializable;

public class TreeWithValueInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**元数据标识*/
	private String fieldId;
	/**树节点名称*/
	private String name;
	/**可选性*/
	private String isSelect;
	/**重复性*/
	private String repeat;
	/**是否是元数据*/
	private String isFeild;
	/**元数据编码*/
	private String fieldCode;
	/**xml路径*/
	private String xpath;
	/**树节点标识*/
	private String id;
	/**树父节点标识*/
	private String pid;
	/**唯一性*/
	private String isOnly;
	private String code;
	private String displayName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getIsOnly() {
		return isOnly;
	}
	public void setIsOnly(String isOnly) {
		this.isOnly = isOnly;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXpath() {
		return xpath;
	}
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	public String getFieldCode() {
		return fieldCode==null?"":fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode==null?"":fieldCode;
	}
	public String getFieldId() {
		return fieldId==null?"":fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId==null?"":fieldId;
	}
	public String getName() {
		return name==null?"":name;
	}
	public void setName(String name) {
		this.name = name==null?"":name;
	}
	public String getIsSelect() {
		return isSelect==null?"":isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect==null?"":isSelect;
	}
	public String getRepeat() {
		return repeat==null?"":repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat==null?"":repeat;
	}
	public String getIsFeild() {
		return isFeild==null?"":isFeild;
	}
	public void setIsFeild(String isFeild) {
		this.isFeild = isFeild==null?"":isFeild;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}

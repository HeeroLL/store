package com.zebone.dnode.etl.adapter.vo;

/**
 * 数据对照基本信息
 * @author: caixl
 * @date： 日期：Dec 26, 2013
 * @version 1.0
 */

public class DictMap {
	/**机构字典标识*/
	private String orgDictId;
	/**机构字典编码*/
	private String dictCode;
	/**标准字典标识*/
	private String dictId;
	public String getOrgDictId() {
		return orgDictId;
	}
	public void setOrgDictId(String orgDictId) {
		this.orgDictId = orgDictId;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
}

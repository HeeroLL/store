package com.zebone.dip.tool.vo;

import java.util.List;

/**
 * 
 * @author YinCM
 * @since
 */
public class DictionaryTable {
	//字典信息
	private DictionaryType dictionaryType;
	//字典所包含的的字典字段
	private List<DictionaryField> dictionaryFieldList;
	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}
	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}
	public List<DictionaryField> getDictionaryFieldList() {
		return dictionaryFieldList;
	}
	public void setDictionaryFieldList(List<DictionaryField> dictionaryFieldList) {
		this.dictionaryFieldList = dictionaryFieldList;
	}
	
}

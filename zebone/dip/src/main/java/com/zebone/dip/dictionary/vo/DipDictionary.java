package com.zebone.dip.dictionary.vo;
/**
 * 字典VO
 * @author YinCm
 * @version 2013-7-18 上午10:10:20
 */
public class DipDictionary {
	private String dict_id;
	private String dict_name;
	private String dict_code;
	private String name_pinyin;
	private String name_jianpin;
	private String remark;
	private String dicttype_id;
	private int order_no;
	public String getDict_id() {
		return dict_id;
	}
	public void setDict_id(String dict_id) {
		this.dict_id = dict_id;
	}
	public String getDict_name() {
		return dict_name;
	}
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	public String getDict_code() {
		return dict_code;
	}
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
	public String getName_pinyin() {
		return name_pinyin;
	}
	public void setName_pinyin(String name_pinyin) {
		this.name_pinyin = name_pinyin;
	}
	public String getName_jianpin() {
		return name_jianpin;
	}
	public void setName_jianpin(String name_jianpin) {
		this.name_jianpin = name_jianpin;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDicttype_id() {
		return dicttype_id;
	}
	public void setDicttype_id(String dicttype_id) {
		this.dicttype_id = dicttype_id;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
}

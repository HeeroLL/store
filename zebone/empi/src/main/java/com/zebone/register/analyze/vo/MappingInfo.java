package com.zebone.register.analyze.vo;

/**
 * 映射信息。保存表字段与文档节点的对应关系
 * 
 * @author songjunjie
 * @version 2013-8-6 下午05:01:13
 */
public class MappingInfo {
	/**
	 * 字段名字
	 */
	private String fieldName;
	/**
	 * 字段类型
	 */
	private String fieldtype;
	/**
	 * 是否为主键
	 */
	private String iskey;
	/**
	 * 生成策略
	 */
	private String policy;
	/**
	 * xpath路径
	 */
	private String xpath;
	/**
	 * 组号
	 */
	private String groupNo;
	/**
	 * 函数
	 */
	private String func;
	
	/**
	 * 在映射关系中是否制定为外键
	 */
	private String isfk;

	/**
	 * 字段名字
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * 字段名字
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * 字段类型
	 * 
	 * @return
	 */
	public String getFieldtype() {
		return fieldtype;
	}

	/**
	 * 字段类型
	 * 
	 * @return
	 */
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}

	/**
	 * 是否为主键
	 * 
	 * @return
	 */
	public String getIskey() {
		return iskey;
	}

	/**
	 * 是否为主键
	 * 
	 * @return
	 */
	public void setIskey(String iskey) {
		this.iskey = iskey;
	}

	/**
	 * 生成策略
	 * 
	 * @return
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * 生成策略
	 * 
	 * @return
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	/**
	 * xpath表达式
	 * 
	 * @return
	 */
	public String getXpath() {
		return xpath;
	}

	/**
	 * xpath表达式
	 * 
	 * @return
	 */
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	/**
	 * 组号
	 * 
	 * @return
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 组号
	 * 
	 * @return
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	/**
	 * 函数
	 * 
	 * @return
	 */
	public String getFunc() {
		return func;
	}

	/**
	 * 函数
	 * 
	 * @return
	 */
	public void setFunc(String func) {
		this.func = func;
	}

	public String getIsfk() {
		return isfk;
	}

	public void setIsfk(String isfk) {
		this.isfk = isfk;
	}
	
}

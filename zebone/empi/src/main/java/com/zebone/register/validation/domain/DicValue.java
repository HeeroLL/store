package com.zebone.register.validation.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


/**
 * 标准数据字典信息
 * @author 陈阵 
 * @date 2013-7-31 下午2:41:26
 */
@Alias("dicValue")
public class DicValue implements Serializable{

	private static final long serialVersionUID = -567740121345065540L;
	
	/** 字典名称 **/
	private String dicName;
	
	/** 字典编码 **/
	private String dicCode;


	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	@Override
	public String toString() {
		return "[字典名称=" + dicName + ", 字典编码=" + dicCode + "]";
	}
	
	
}

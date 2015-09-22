package com.zebone.register.validation.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


/**
 * 标准数据字典类型信息
 * @author 陈阵 
 * @date 2013-7-31 下午2:38:12
 */
@Alias("dicType")
public class DicType implements Serializable {

	private static final long serialVersionUID = 2834600525771075595L;
	
	/** 数据字典类型主键 **/
	private String typeId;
	
	/** 类型名字 **/
	private String typeName;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	

}

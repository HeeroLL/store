package com.zebone.register.validation.repository;

import java.util.List;

import com.zebone.register.validation.domain.DicType;
import com.zebone.register.validation.domain.DicValue;



/**
 * 主数据Repository
 * @author 陈阵 
 * @date 2013-7-31 下午3:26:27
 */
public interface MasterDataRepository {
	
	/**
	 * 根据字典类型id获取字典信息
	 * @param dicTypeId
	 * @return
	 * @author 陈阵 
	 * @date 2013-7-31 下午3:44:07
	 */
	public List<DicValue> getDicByDicTypeId(String dicTypeId);
	

	/**
	 * 根据字典类型id获取字典类型信息
	 * @param typeId
	 * @return
	 * @author 陈阵 
	 * @date 2013-7-31 下午3:43:52
	 */
	public DicType getDicTypeByTypeId(String typeId);

}

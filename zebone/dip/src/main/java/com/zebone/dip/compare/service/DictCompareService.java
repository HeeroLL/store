package com.zebone.dip.compare.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.compare.vo.DictInfo;
import com.zebone.dip.compare.vo.DictTypeOrg;

/**
 * 数据字典对照业务接口层
 * @author: caixl
 * @date： 日期：Jan 2, 2014
 * @version 1.0
 */

public interface DictCompareService {

	/**
	 * 获取机构下的字典类型的信息
	 * @param levelCode
	 * @return 
	 * List<DictTypeOrg>
	 */
	List<DictTypeOrg> getListByOrg(String levelCode);

	/**
	 * 获取机构下的字典类型信息
	 * @param levelCode 机构
	 * @param typeName 类型名称
	 * @return 
	 * List<DictTypeOrg>
	 */
	List<DictTypeOrg> getDictTypeByName(String levelCode, String typeName);

	/**
	 * 获取字典对照相关信息列表
	 * @param page
	 * @param dictInfo
	 * @return 
	 * Pagination<DictInfo>
	 */
	Pagination<DictInfo> getDictPage(Pagination<DictInfo> page,DictInfo dictInfo);

	/**
	 * 获取值域信息跟名称或类型
	 * @param dictInfo
	 * @return 
	 * List<DictInfo>
	 */
	List<DictInfo> getDictListByName(DictInfo dictInfo);

	/**
	 * 获取字典的详细对照信息
	 * @param orgDictId 字典类型标识
	 * @return 
	 * DictInfo
	 */
	DictInfo getDictInfoByOrgDictId(String orgDictId);

	/**
	 * 自动匹配标准字典类型信息
	 * @param dictInfo
	 * @return 
	 * List<DictInfo>
	 */
	List<DictInfo> getDictListByNameAndTypeId(DictInfo dictInfo);

	/**
	 * 自动匹配字典数据
	 * @param name
	 * @return 
	 * List<DictTypeOrg>
	 */
	List<DictTypeOrg> getListByName(String name);

	/**
	 * 保存对照信息
	 * @param dictInfo
	 * @return 
	 * int
	 */
	int saveCompareInfo(DictInfo dictInfo);

	/**
	 * 删除对照字典数据
	 * @param id 
	 * void
	 */
	void removeCompare(String id);

}

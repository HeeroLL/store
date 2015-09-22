package com.zebone.dip.compare.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.compare.vo.DictInfo;
import com.zebone.dip.compare.vo.DictTypeOrg;
@Mapper
public interface DictTypeOrgMapper {

    int deleteByPrimaryKey(String typeId);
    int insert(DictTypeOrg record);
    int insertSelective(DictTypeOrg record);
    DictTypeOrg selectByPrimaryKey(String typeId);
    int updateByPrimaryKeySelective(DictTypeOrg record);
    int updateByPrimaryKey(DictTypeOrg record);
	/**
	 * 根据条件查询数据字典类型信息
	 * @param levelCode 机构编码
	 * @param typeName 类型名称
	 * @return 
	 * List<DictTypeOrg>
	 */
	List<DictTypeOrg> getListByCodeAndName(@Param("orgCode")String levelCode, @Param("typeName")String typeName);
	/**
	 * 获取字典对照信息列表
	 * @param rowBounds
	 * @param dictInfo
	 * @return 
	 * List<DictInfo>
	 */
	List<DictInfo> getDictList(RowBounds rowBounds, DictInfo dictInfo);
	/**
	 * 获取字典对照信息总数
	 * @param dictInfo
	 * @return 
	 * int
	 */
	int getTotalCount(DictInfo dictInfo);
	/**
	 * 获取值域信息跟据名称或类型
	 * @param dictInfo
	 * @return 
	 * List<DictInfo>
	 */
	List<DictInfo> getDictByName(DictInfo dictInfo);
	
	/**
	 * 查询是否已经存在重复的字典
	 * @param dictInfo
	 * @return
	 */
	int getCountByDictInfo(DictInfo dictInfo);
	/**
	 * 获取字典对照详细信息
	 * @param orgDictId
	 * @return 
	 * DictInfo
	 */
	DictInfo getDictByOrgDictId(@Param("orgDictId")String orgDictId);
	/**
	 * 
	 * @param orgDictTypeId
	 * @return 
	 * List<String>
	 */
	List<String> getIdsByOrgTypeId(String orgDictTypeId);
	/**
	 * 获取标准字典类型信息
	 * @param name
	 * @return 
	 * List<DictTypeOrg>
	 */
	List<DictTypeOrg> getListByName(@Param("name")String name);
	/**
	 * 获取标准字典信息
	 * @param dictInfo
	 * @return 
	 * List<DictInfo>
	 */
	List<DictInfo> getDictInfoByName(DictInfo dictInfo);
	/**
	 * 更新对照信息
	 * @param dictInfo 
	 * void
	 */
	void updateCompareInfo(DictInfo dictInfo);
	/**
	 * 获取机构字典是否存在
	 * @param dictInfo
	 * @return 
	 * int
	 */
	int getCountByInfo(DictInfo dictInfo);
	/**
	 * 保存机构字典信息
	 * @param dictInfo 
	 * void
	 */
	void saveDictInfo(DictInfo dictInfo);
	/**
	 * 保存对照信息
	 * @param dictInfo 
	 * void
	 */
	void saveCompareInfo(DictInfo dictInfo);
	/**
	 * 获取机构类型是否存在
	 * @param dictInfo
	 * @return 
	 * String
	 */
	String getCountTypeByInfo(DictInfo dictInfo);
	/**
	 * 保存机构字典类型信息
	 * @param dictInfo 
	 * void
	 */
	void saveDictType(DictInfo dictInfo);
	/**
	 * 删除机构字典
	 * @param dictId 
	 * void
	 */
	void deleteOrgDictById(@Param("dictId")String dictId);
	/**
	 * 删除字典对照关系
	 * @param id 
	 * void
	 */
	void deleteCompareById(@Param("id")String id);
}
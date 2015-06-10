package com.zebone.dip.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.dictionary.vo.DipDictionaryType;

/**
 * 
 * @author YinCm
 * @since 2013-7-16
 */
@Mapper
public interface DipDictionaryTypeMapper {
	
	/**
	 * 添加字典类型
	 * @param dt
	 */
	public void addDipDictionaryType(DipDictionaryType dt);
	
	/**
	 * 删除字典类型
	 * @param ids
	 */
	public void deleteDipDictionaryTypeByIds(String[] ids);
	
	/**
	 * 某一父节点下子节点的数目
	 * @param id
	 * @return
	 */
	public int getDipDictionaryTypeByParentIdCount(String id);
	
	/**
	 * 通过字典类型的字段，模糊查找字典类型
	 * @param rb
	 * @param dictType
	 * @return
	 */
 	public List<DipDictionaryType> searchDipDictionaryType(RowBounds rb, DipDictionaryType dictType);
	
 	/**
 	 * 通过字典类型的字段，模糊查找字典类型对应的数量，用于分页
 	 * @param dictType
 	 * @return
 	 */
 	public int searchDipDictionaryTypeCount(DipDictionaryType dictType);
 	
 	/**
 	 * 查找所有未删除的字典类型
 	 * @return
 	 */
	public List<DipDictionaryType> getAllDipDictionaryType();
	
	/**
	 * 更新字典类型
	 * @param dictType
	 */
	public void updateDipDictionaryType(DipDictionaryType dictType);
	
	/**
	 * 更新字典类型
	 * @param id
	 * @return
	 */
	public DipDictionaryType getDipDictionaryTypeById(String id);
	
	/**
	 * 查找对应的type_id，其未删除的子节点的数目
	 * @param ids
	 * @return
	 */
	public int checkDicTypeChildrenBeforeDelete(String[] ids);
	
	/**
	 * 通过字典类型的parent_id和type_name查询数量
	 * @param dictType
	 * @return
	 */
	public int countTypeByParentIdAndName(DipDictionaryType dictType);
	
	/**
	 * 按照parent_id和type_name来获取查询类型编码
	 * @param dictType
	 * @return
	 */
	public List<String> getTypeCodeListByParentIdAndTypeName(DipDictionaryType dictType);

	/**
	 * @author caixl
	 * @date Aug 1, 2013
	 * @description TODO 匹配数据字典类型
	 * @param name
	 * @return List<DictionaryType>
	 */
	public List<DictionaryType> getDictInfoByName(@Param("name")String name);

	/**
	 * 查询一级字典类型
	 * @author caixl
	 * @date Dec 5, 2013
	 * @return 
	 * List<DipDictionaryType>
	 */
	public List<DipDictionaryType> getLevel1DictType();

    /**
     * 获取匹配的字典信息
     * @param name
     * @return List<DipDictionaryType>
     */
    public List<DipDictionaryType> getMatchDictInfo(@Param("name")String name);

    /**
     * 获取匹配的主数据信息
     * @param name
     * @return List<DipDictionaryType>
     */
    public List<DipDictionaryType> getMatchMDInfo(@Param("name")String name);
}

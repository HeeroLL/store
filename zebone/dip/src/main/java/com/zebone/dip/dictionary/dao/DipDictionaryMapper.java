package com.zebone.dip.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.md.vo.NameCode;

/**
 * 
 * @author YinCm
 * @since 2013-7-16
 */
@Mapper
public interface DipDictionaryMapper {
	
	/**
	 * 添加字典
	 * @param dic
	 * @return
	 */
	public void addDipDictionary(DipDictionary dic);
	
	/**
	 * 按照字典字段模糊查询字典（带分页参数）
	 * @param rb
	 * @param dic
	 * @return
	 */
	public List<DipDictionary> searchDipDictionary(RowBounds rb, DipDictionary dic);
	
	/**
	 * 按照字典字段模糊查询字典，获得总数
	 * @param dd
	 * @return
	 */
	public int searchDipDictionaryCount(DipDictionary dd);
	
	/**
	 * 通过id字符串数组删除字典
	 * @param ids
	 */
	public void deleteDipDictionaryByIds(String[] ids);
	
	/**
	 * 按dict_id查找字典
	 * @param id
	 * @return
	 */
	public DipDictionary findDipDictionaryById(String id);
	
	/**
	 * 更新字典信息
	 * @param dic
	 */
	public void updateDipDictionary(DipDictionary dic);
	
	/**
	 * 删除前检查，删除项中是否含有子节点，返回所包含的子节点数目
	 * @param id
	 * @return
	 */
	public int checkDicTypeChildrenDicBeforeDelete(String[] id);

	/**
	 * 通过type_code获取字典名称列表
	 * @param typeCode
	 * @return
	 */
    public List<NameCode> getDictNameListByType (String typeCode);
    
    /**
     * 通过字典名称模糊查询查询字典
     * @param dict_name
     * @return
     */
    public List<DipDictionary> getDipDictionaryByDictNameLikes(String dict_name);

	/**
	 * 根据类型编码获取字典信息
	 * @param code
	 * @return 
	 * List<NameCode>
	 */
	public List<NameCode> getDictByCode(@Param("typeCode")String code);
	
	/**
	 * 按字典类别和编码查找字典
	 * @param id
	 * @return
	 */
	public NameCode findDipDictionaryByTypeAndId(@Param("type")String type,@Param("code")String code);

    /**
     * 查找字典类型下的字典值
     * @param id 字典id
     * @return
     */
    public List<DipDictionary> getDipDictionaryByParentId(String id);
}

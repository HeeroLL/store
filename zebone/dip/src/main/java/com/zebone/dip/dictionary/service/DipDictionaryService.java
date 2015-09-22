package com.zebone.dip.dictionary.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.dictionary.vo.DipDictionaryType;
import com.zebone.dip.md.vo.NameCode;
/**
 * 字典Service
 * @author YinCm
 * @version 2013-7-18 上午10:10:20
 */
public interface DipDictionaryService {
	
	//Dictionary Type
	/**
	 * 添加字典类型
	 * @param dt
	 * @return
	 */
	public boolean addDipDictionaryType(DipDictionaryType dt);
	
	/**
	 * 删除字典类型
	 * @param ids 字符串数组
	 * @return
	 */
	public boolean deleteDipDictionaryTypeByIds(String[] ids);
	
	/**
	 * 通过字典类型的字段，模糊查找字典类型
	 * @param ddt
	 * @param page
	 * @return
	 */
	public Pagination<DipDictionaryType> searchDipDictionaryType(DipDictionaryType ddt, Pagination<DipDictionaryType> page);
	
	/**
	 * 查找所有未删除的字典类型
	 * @return
	 */
	public List<DipDictionaryType> getAllDipDictionaryType();
	
	/**
	 * 更新字典类型
	 * @param dictType
	 * @return
	 */
	public boolean updateDipDictionaryType(DipDictionaryType dictType);
	
	/**
	 * 通过type_id获取字典类型 
	 * @param id
	 * @return
	 */
	public DipDictionaryType getDipDictionaryTypeById(String id);
	
	/**
	 * 查找某一parent_id下未删除的子节点数目
	 * @param id
	 * @return
	 */
	public int countDipDictionaryByParentId(String id);
	
	/**
	 * 通过字典类型的parent_id和type_name查询数量
	 * @param ddt
	 * @return
	 */
	public int countTypeByParentIdAndName(DipDictionaryType ddt);
	
	/**
	 * 添加字典
	 * @param dic
	 * @return
	 */
	public boolean addDipDictionary(DipDictionary dic);
	
	/**
	 * 按照字典字段查询字典（带分页参数）
	 * @param dic
	 * @param page
	 * @return
	 */
	public Pagination<DipDictionary> searchDipDictionary(DipDictionary dic, Pagination<DipDictionary> page);
	
	/**
	 * 按dict_id查找字典
	 * @param id
	 * @return
	 */
	public DipDictionary findDipDictionaryById(String id);
	
	/**
	 * 通过id字符串数组删除字典
	 * @param ids
	 * @return
	 */
	public boolean deleteDipDictionaryByIds(String[] ids);
	
	/**
	 * 更新字典信息
	 * @param dic
	 * @return
	 */
	public boolean updateDipDictionary(DipDictionary dic);

 
	/**
	 * 通过type_code获取字典名称列表
	 * @param typeCode
	 * @return
	 */
    public List<NameCode> getDictNameListByCode(String typeCode);
 
    
    /**
     * 判断字典类型是否有未删除的子节点
     * @return 如果有子节点，返回false，否则返回true
     */
    public boolean checkDicTypeChildrenBeforeDelete(String[] ids);
	
    
    /**
	 * 按照parent_id和type_name来获取查询类型编码
	 * @param dictType
	 * @return
	 */
	public List<String> getTypeCodeListByParentIdAndTypeName(DipDictionaryType dictType);

	/**
	 * 查询一级字典类型
	 * @author caixl
	 * @date Dec 5, 2013
	 * @return 
	 * List<DipDictionaryType>
	 */
	public List<DipDictionaryType> getLevel1DictType();

	/**
	 * 根据标准编码获取获取某字典
	 * @param code
	 * @return 
	 * List<NameCode>
	 */
	public List<NameCode> getTypeByCode(String code);
	
	/**
	 * 按字典类别和编码查找字典
	 * @param id
	 * @return
	 */
	public NameCode findDipDictionaryByTypeAndId(String type,String code);
}

package com.zebone.empi.dao;

import java.util.List;
import java.util.Map;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.Dictionary;

/**
 * 
 * @author YinCm
 * @since 2013-7-16
 */
@Mapper
public interface DictionaryMapper {
	
	/**
	 * 查找字典类型下的字典值
	 * @param id 字典id
	 * @return
	 */
	public List<Dictionary> getDictionaryByTypeId(String id);

    /**
     * 判断该字典值是否存在
     * @param oMap
     * @return
     */
    public int countDictInfo (Map oMap);

    /**
     * 根据机构编码获取机构名称
     * @param orgCode
     * @return
     */
    public String getOrgName(String orgCode);
}

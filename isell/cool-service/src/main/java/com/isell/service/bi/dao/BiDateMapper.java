package com.isell.service.bi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.bi.vo.BiDate;

/**
 * 日期表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09]
 */
@Mapper
public interface BiDateMapper{   
    /**
     * 根据主键查询
     */
    public BiDate getBiDateById(@Param("id")Integer id); 
    
    /**
     * 根据日期查询
     */
    public BiDate getBiDateByDate(@Param("date")String date);

    /**
     * 查询出所有记录
     */
    public List<BiDate> findAllBiDate();    
    
    /**
     * 保存
     */
    public int saveBiDate(BiDate biDate);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateBiDate(BiDate biDate);
    
    /**
     * 根据主键删除
     */
    public int deleteBiDate(@Param("id")Integer id);
}


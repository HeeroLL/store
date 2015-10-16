package com.isell.service.fare.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.fare.vo.CoonFareTemp;

import org.apache.ibatis.annotations.Param;

/**
 * 快递mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoonFareTempMapper{   
    /**
     * 根据主键查询
     */
    public CoonFareTemp getCoonFareTempById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonFareTemp> findAllCoonFareTemp();    
    
    /**
     * 保存
     */
    public int saveCoonFareTemp(CoonFareTemp coonFareTemp);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonFareTemp(CoonFareTemp coonFareTemp);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonFareTemp(@Param("id")String id);
}


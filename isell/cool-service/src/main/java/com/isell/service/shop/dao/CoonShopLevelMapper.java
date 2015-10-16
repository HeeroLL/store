package com.isell.service.shop.dao;

import java.math.BigDecimal;
import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopLevel;

import org.apache.ibatis.annotations.Param;

/**
 * 酷店等级表Mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoonShopLevelMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopLevel getCoonShopLevelById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopLevel> findAllCoonShopLevel();    
    
    /**
     * 保存
     */
    public int saveCoonShopLevel(CoonShopLevel coonShopLevel);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopLevel(CoonShopLevel coonShopLevel);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopLevel(@Param("id")String id);
    
    /**
     * 查询店铺对应的比例p_rate
     * 
     * @param userId
     * @return p_rate
     */
    public BigDecimal getLevelPrate(@Param("userId")String userId);
}


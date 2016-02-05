package com.isell.service.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.po.CoonShopCartInfo;
import com.isell.service.order.vo.CoonShopcart;

/**
 * 购物车表Mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-01]
 */
@Mapper
public interface CoonShopcartMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopcart getCoonShopcartById(@Param("id")String id); 
    
    /**
     * 根据条件查询
     */
    public CoonShopcart getCoonShopcart(CoonShopcart coonShopcart); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopcart> findAllCoonShopcart();    
    
    /**
     * 根据查询条件查询购物车列表信息
     */
    public List<CoonShopCartInfo> findCoonShopCartInfoList(CoonShopcart coonShopcart);  
    
    /**
     * 保存 
     */
    public int saveCoonShopcart(CoonShopcart coonShopcart);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopcart(CoonShopcart coonShopcart);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopcart(@Param("id")String id);
}


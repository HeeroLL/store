package com.isell.service.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.vo.CoolStockDepot;

/**
 * TODO
 * @author 
 */
@Mapper
public interface CoolStockDepotMapper{   
    /**
     * 根据主键查询
     */
    public CoolStockDepot getCoolStockDepotById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolStockDepot> findAllCoolStockDepot();    
    
    /**
     * 保存
     */
    public int saveCoolStockDepot(CoolStockDepot coolStockDepot);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolStockDepot(CoolStockDepot coolStockDepot);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolStockDepot(@Param("id")Integer id);
    
    /**
     * 
     */
    public CoolStockDepot getCoolStockDepotProvinceById(@Param("id")Integer id); 
}


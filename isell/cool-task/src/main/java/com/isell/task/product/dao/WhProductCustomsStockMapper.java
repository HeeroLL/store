package com.isell.task.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.task.product.vo.WhProductCustomsStock;

import org.apache.ibatis.annotations.Param;

/**
 * 保税仓库存类
 * 
 * @author 
 */
@Mapper
public interface WhProductCustomsStockMapper{   
    /**
     * 根据主键查询
     */
    public WhProductCustomsStock getWhProductCustomsStockById(@Param("id")Integer id); 
    
    /**
     * 根据主键查询
     */
    public WhProductCustomsStock getWhProductCustomsStockByCode(@Param("code")String id); 

    /**
     * 查询出所有记录
     */
    public List<WhProductCustomsStock> findAllWhProductCustomsStock();    
    
    /**
     * 保存
     */
    public int saveWhProductCustomsStock(WhProductCustomsStock whProductCustomsStock);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateWhProductCustomsStock(WhProductCustomsStock whProductCustomsStock);
    
    /**
     * 根据主键删除
     */
    public int deleteWhProductCustomsStock(@Param("id")Integer id);
}


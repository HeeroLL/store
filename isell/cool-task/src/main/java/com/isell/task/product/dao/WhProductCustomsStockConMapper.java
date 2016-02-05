package com.isell.task.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.task.product.vo.WhProductCustomsStockCon;

import org.apache.ibatis.annotations.Param;

/**
 * 保税仓库存配置类
 * @author 
 */
 @Mapper
public interface WhProductCustomsStockConMapper{   
    /**
     * 根据主键查询
     */
    public WhProductCustomsStockCon getWhProductCustomsStockConById(@Param("id")Integer id); 
    
    /**
     * 根据条件查询
     */
    public WhProductCustomsStockCon getWhProductCustomsStock(WhProductCustomsStockCon whProductCustomsStockCon);

    /**
     * 查询出所有记录
     */
    public List<WhProductCustomsStockCon> findAllWhProductCustomsStockCon();    
    
    /**
     * 保存
     */
    public int saveWhProductCustomsStockCon(WhProductCustomsStockCon whProductCustomsStockCon);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateWhProductCustomsStockCon(WhProductCustomsStockCon whProductCustomsStockCon);
    
    /**
     * 根据主键删除
     */
    public int deleteWhProductCustomsStockCon(@Param("id")Integer id);
}


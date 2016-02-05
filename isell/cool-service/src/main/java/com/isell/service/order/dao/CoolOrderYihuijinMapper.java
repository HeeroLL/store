package com.isell.service.order.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.vo.CoolOrderYihuijin;

import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * @author 
 */
@Mapper
public interface CoolOrderYihuijinMapper{   
    /**
     * 根据主键查询
     */
    public CoolOrderYihuijin getCoolOrderYihuijinById(@Param("id")Integer id); 

    /**
     * 根据订单单号查询
     */
    public CoolOrderYihuijin getCoolOrderYihuijinByOrderNo(@Param("OrderNo")String OrderNo); 
    
    /**
     * 查询出所有记录
     */
    public List<CoolOrderYihuijin> findAllCoolOrderYihuijin();    
    
    /**
     * 保存
     */
    public int saveCoolOrderYihuijin(CoolOrderYihuijin coolOrderYihuijin);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolOrderYihuijin(CoolOrderYihuijin coolOrderYihuijin);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolOrderYihuijin(@Param("id")Integer id);
}


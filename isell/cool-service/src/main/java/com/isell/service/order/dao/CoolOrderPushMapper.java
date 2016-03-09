package com.isell.service.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderPush;

/**
 * 订单推送表mapper
 * 
 * @author lilin
 * @version [版本号, 2015年12月25日]
 */
@Mapper
public interface CoolOrderPushMapper{   
    /**
     * 根据主键查询
     */
    public CoolOrderPush getCoolOrderPushById(@Param("id")Integer id); 
    
    /**
     * 根据订单号查询
     */
    public CoolOrderPush getCoolOrderPushByOrderNo(@Param("orderNo")String orderNo); 

    /**
     * 查询拼多多待推送订单
     */
    public List<CoolOrder> getPinduoduoOrderList();

    /**
     * 查询出所有记录
     */
    public List<CoolOrderPush> findAllCoolOrderPush();    
    
    /**
     * 保存
     */
    public int saveCoolOrderPush(CoolOrderPush coolOrderPush);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolOrderPush(CoolOrderPush coolOrderPush);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolOrderPush(@Param("id")Integer id);
    
    /**
     * 根据订单号删除
     */
    public List<CoolOrder> deleteCoolOrderPushByOrderNo(@Param("orderNo")String id);

    
}


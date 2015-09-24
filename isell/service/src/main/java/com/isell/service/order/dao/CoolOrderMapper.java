package com.isell.service.order.dao;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.vo.CoolOrder;

/**
 * 订单查询mapper
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Mapper
public interface CoolOrderMapper {
    /**
     * 根据主键查询
     * 
     * @param id 订单主键
     * @return 订单信息
     */
    CoolOrder getCoolOrderById(@Param("id") Integer id);
    
    /**
     * 根据订单id查询
     * 
     * @param id 订单主键
     * @return 订单信息
     */
    CoolOrder getCoolOrderByOrderNo(String orderNo);
    
    /**
     * 保存
     */
    int saveCoolOrder(CoolOrder coolOrder);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     * 
     * @param coolOrder 订单信息
     * @return 成功更新的条数
     */
    int updateCoolOrder(CoolOrder coolOrder);
    
    /**
     * 根据主键删除
     * 
     * @param id 订单id
     * @return 成功删除的条数
     */
    int deleteCoolOrder(@Param("id") Integer id);
}

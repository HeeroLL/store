package com.isell.service.order.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.po.CoolProductSales;
import com.isell.service.order.vo.CoolOrderItem;

/**
 * 订单详情Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Mapper
public interface CoolOrderItemMapper {
    /**
     * 根据主键查询
     *
     * @param id 订单详情id
     * @return 订单详情信息
     */
    CoolOrderItem getCoolOrderItemById(@Param("id") Integer id);
    
    /**
     * 根据订单编号查询订单详情
     *
     * @param orderNo 订单编号
     * @return 订单详情列表
     */
    List<CoolOrderItem> findCoolOrderItemByOrderNo(String orderNo);
    
    /**
     * 根据订单主键查询订单详情
     *
     * @param orderId 订单主键
     * @return 订单详情列表
     */
    List<CoolOrderItem> findCoolOrderItemByOrderId(Integer orderId);
    
    /**
     * 保存订单详情
     *
     * @param coolOrderItem 订单详情信息
     * @return 成功保存的条数
     */
    int saveCoolOrderItem(CoolOrderItem coolOrderItem);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     *
     * @param coolOrderItem 订单详情信息
     * @return 成功修改的条数
     */
    int updateCoolOrderItem(CoolOrderItem coolOrderItem);
    
    /**
     * 根据主键删除
     *
     * @param id 主键id
     * @return 成功删除的条数
     */
    int deleteCoolOrderItem(@Param("id") Integer id);
    
    /**
     * 根据订单号获取最大佣金
     *
     * @param orderNo 订单号
     * @return 最大佣金
     */
    BigDecimal getMaxProfit(@Param("orderNo") String orderNo);
    
    /**
	 * 统计商品销量
	 * 
	 * @param param
	 */
    List<CoolProductSales> getSumCoolProductSales(CoolOrderSelect orderSelect);

}

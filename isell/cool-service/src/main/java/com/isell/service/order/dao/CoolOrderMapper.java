package com.isell.service.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.common.StatisticsPo;
import com.isell.core.mybatis.Mapper;
import com.isell.service.order.po.CoolOrderExport;
import com.isell.service.order.po.CoolOrderSelect;
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
     * 根据订单编号查询
     * 
     * @param orderNo 订单编号
     * @return 订单信息
     */
    CoolOrder getCoolOrderByOrderNo(String orderNo);
    
    /**
     * 根据物流单号查询
     * 
     * @param psCode 物流单号
     * @return 订单信息
     */
    CoolOrder getCoolOrderByPsCode(String psCode);
    
    /**
     * 根据条件查询订单列表
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getCoolOrderList(CoolOrder param);
    
    /**
     * 根据条件分页查询订单列表
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getCoolOrderPageList(RowBounds rowBounds, CoolOrderSelect orderSelect);
    
    /**
     * 根据条件分页查询订单数量
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    int getCoolOrderPageListCount(CoolOrderSelect orderSelect);
    
    /**
     * 根据条件查询导出订单列表
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrderExport> getCoolOrderListExport(CoolOrderSelect orderSelect);
    
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
    
    /**
     * 统计订单数
     * 
     * @param selectType 查询条件
     * @return
     */
    List<StatisticsPo> getSumCoolOrderNumber(@Param("selectType") String selectType, @Param("time") String time);
    
    /**
     * 统计销售额
     * 
     * @param selectType 查询条件
     * @return
     */
    List<StatisticsPo> getSumCoolOrderSales(@Param("selectType") String selectType, @Param("time") String time);
    
    /**
     * 统计店铺销售额排名
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getSumCoonShopSales(CoolOrderSelect orderSelect);
}

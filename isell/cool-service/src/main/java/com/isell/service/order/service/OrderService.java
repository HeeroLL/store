package com.isell.service.order.service;

import java.util.List;

import com.isell.service.order.vo.CoolOrder;

/**
 * 订单服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
public interface OrderService {
    /**
     * 根据主键获取订单
     *
     * @param id id
     * @return 订单信息
     */
    CoolOrder getCoolOrderById(Integer id);
    
    /**
     * 根据订单编号获取订单
     *
     * @param orderNo 订单编号
     * @return 订单信息
     */
    CoolOrder getCoolOrderByOrderNo(String orderNo);
    
    /**
     * 根据主键获取订单及详情
     *
     * @param id id
     * @return 订单信息及详情
     */
    CoolOrder getCoolOrderDetailById(Integer id);
    
    /**
     * 根据订单编号获取订单及详情
     *
     * @param orderNo 订单编号
     * @return 订单信息及详情
     */
    CoolOrder getCoolOrderDetailByOrderNo(String orderNo);
    
    /**
     * 根据物流单号查询订单详情
     * 
     * @param psCode 物流单号
     * @return 订单信息及详情
     */
    CoolOrder getCoolOrderDetailByPsCode(String psCode);
    
    /**
     * 根据条件查询订单列表
     *
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getCoolOrderList(CoolOrder param);
    
    /**
     * 取消订单
     *
     * @param ids 订单主键集合
     */
    void cancelOrder(Integer... ids);
}

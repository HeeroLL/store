package com.isell.service.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;

/**
 * 订单服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    /**
     * 订单查询mapper
     */
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    /**
     * 订单详情Mapper
     */
    @Resource
    private CoolOrderItemMapper coolOrderItemMapper;
    
    @Override
    public CoolOrder getCoolOrderById(Integer id) {
        return coolOrderMapper.getCoolOrderById(id);
    }
    
    @Override
    public CoolOrder getCoolOrderByOrderNo(String orderNo) {
        return coolOrderMapper.getCoolOrderByOrderNo(orderNo);
    }
    
    @Override
    public CoolOrder getCoolOrderDetailById(Integer id) {
        CoolOrder order = getCoolOrderById(id);
        if (order != null) {
            order.setItemList(coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo()));
        }
        return order;
    }
    
    @Override
    public CoolOrder getCoolOrderDetailByOrderNo(String orderNo) {
        CoolOrder order = getCoolOrderByOrderNo(orderNo);
        if (order != null) {
            order.setItemList(coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo()));
        }
        return order;
    }
    
}

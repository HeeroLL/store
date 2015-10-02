package com.isell.service.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;

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
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
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
    
    @Override
    public CoolOrder getCoolOrderDetailByPsCode(String psCode) {
        CoolOrder order = coolOrderMapper.getCoolOrderByPsCode(psCode);
        if (order != null) {
            order.setItemList(coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo()));
        }
        return order;
    }
    
    @Override
    public void cancelOrder(Integer... ids) {
        if (ids == null) {
            throw new RuntimeException("exception.order.null");
        }
        CoolOrder param = new CoolOrder();
        param.setState(CoolOrder.ORDER_STATE_99);
        for (int id : ids) {
            param.setId(id);
            // 更新订单状态
            coolOrderMapper.updateCoolOrder(param);
            // 减销量加库存
            List<CoolOrderItem> itemList = coolOrderItemMapper.findCoolOrderItemByOrderId(id);
            if (itemList != null) {
                for (CoolOrderItem item : itemList) {
                    jdbcTemplate.update("update cool_product_gg set stock=stock+?,sales=sales-? where id=?",
                        item.getCount(),
                        item.getCount(),
                        item.getGid());
                    jdbcTemplate.update("update cool_product set sales=sales-? where id=?",
                        item.getCount(),
                        item.getgId());
                }
            }
        }
    }

    @Override
    public List<CoolOrder> getCoolOrderList(CoolOrder param) {
        return coolOrderMapper.getCoolOrderList(param);
    }
    
}

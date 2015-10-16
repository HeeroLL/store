package com.isell.task.order.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.task.order.service.TaskOrderService;

/**
 * 订单服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年8月31日]
 */
@Service("taskOrderService")
public class TaskOrderServiceImpl implements TaskOrderService {
    /**
     * 默认订单自动取消秒数
     */
    @Value("${order.cancel.timeout}")
    private int cancelOrderTimeoutSeconds;
    
    /**
     * 默认订单自动确认时间天数
     */
    @Value("${order.confirm.timeout}")
    private int orderConfirmTimeoutDays;
    
    /**
     * 订单服务接口
     */
    @Resource
    private OrderService orderService;
    
    @Override
    public void cancelOrder() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, cancelOrderTimeoutSeconds * -1);
        CoolOrder param = new CoolOrder();
        param.setState(CoolOrder.ORDER_STATE_0);
        param.setCreatetime(cal.getTime());
        List<CoolOrder> orderList = orderService.getCoolOrderList(param);
        if (orderList != null) {
            Integer[] ids = new Integer[orderList.size()];
            int index = 0;
            for (CoolOrder order : orderList) {
                ids[index++] = order.getId();
            }
            orderService.cancelOrder(ids);
        }
    }

    @Override
    public void signOrder() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, orderConfirmTimeoutDays * -1);
        CoolOrder param = new CoolOrder();
        param.setState(CoolOrder.ORDER_STATE_2);
        param.setUpdatetime(cal.getTime());
        List<CoolOrder> orderList = orderService.getCoolOrderList(param);
        if (orderList != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (CoolOrder order : orderList) {
                map.put("id", order.getId());
                orderService.updateCoolOrderRec(map);
            }
        }
    }
}

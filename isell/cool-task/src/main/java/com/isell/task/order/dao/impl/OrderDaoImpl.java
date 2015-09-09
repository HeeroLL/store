package com.isell.task.order.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.isell.task.order.dao.OrderDao;

/**
 * 订单数据接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年8月31日]
 */
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
    
    /**
     * 默认超时秒数
     */
    @Value("${order.timeout}")
    private int timeoutsecond;
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private NamedParameterJdbcTemplate coolJdbcTemplate;
    
    @Override
    public void cancelOrder() {
        // 批量将超时付款的订单设置为取消
        String sql = "update cool_order set state=99 where state=0 and createtime<:time";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, timeoutsecond * -1);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("time", cal.getTime());
        
        coolJdbcTemplate.update(sql, paramMap);
    }
}

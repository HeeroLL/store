package com.isell.task.logistic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.isell.core.util.JaxbUtil;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.task.logistic.service.LogisticTaskService;
import com.isell.ws.ningbo.bean.OrderSearchResult;
import com.isell.ws.ningbo.service.YoubeiService;
import com.isell.ws.ningbo.ws.logistic.LogisticsWebService;
import com.isell.ws.ningbo.ws.logistic.LogisticsWebServiceSoap;

/**
 * 物流服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年11月3日]
 */
@Service("logisticTaskService")
public class LogisticTaskServiceImpl implements LogisticTaskService {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(LogisticTaskServiceImpl.class);
    
    /**
     * 订单服务接口
     */
    @Resource
    private OrderService orderService;
    
    @Override
    public void updateLogistic() {
        // 获取所有通知海关发货的订单
        CoolOrder param = new CoolOrder();
        param.setState(CoolOrder.ORDER_STATE_11);
        param.setFhfs(CoolOrder.FHFS_11); // 目前只查发送给宁波优贝的订单
        List<CoolOrder> orderList = orderService.getCoolOrderList(param);
        LogisticsWebService service = new LogisticsWebService();
        LogisticsWebServiceSoap soap = service.getLogisticsWebServiceSoap12();
        for (CoolOrder order : orderList) {
            String result =
                soap.getKJB2CLogisticsInfo(YoubeiService.ERPKEY,
                    YoubeiService.ERPSECRET,
                    YoubeiService.SHOPID,
                    order.getOrderNo());
            try {
                OrderSearchResult resultInfo = JaxbUtil.converyToJavaBean(result, OrderSearchResult.class);
                if ("T".equals(resultInfo.getResult()) && resultInfo.getBody() != null
                    && resultInfo.getBody().getLogName() != null && resultInfo.getBody().getLogNo() != null) {
                    // 更新物流信息
                    order.setPsfs(resultInfo.getBody().getLogName());
                    order.setPsCode(resultInfo.getBody().getLogNo());
                    orderService.updateOrder(order);
                }
            } catch (RuntimeException e) {
                log.error(e);
            }
            
        }
    }
    
}

package com.isell.task.order.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.dao.CoolOrderPushMapper;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderPush;
import com.isell.task.order.service.TaskOrderService;
import com.isell.task.order.service.bean.PinduoduoResult;

/**
 * 订单服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年8月31日]
 */
@Service("taskOrderService")
public class TaskOrderServiceImpl implements TaskOrderService {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(TaskOrderServiceImpl.class);
    
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
    
    @Value("${comission.back.timeout}")
    private int comissionBackTimeoutDays;
    
    /**
     * 服务接口
     */
    @Value("${service_domain}")
    private String serviceDomain;
    
    /**
     * 订单服务接口
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 订单推送表mapper
     */
    @Resource
    private CoolOrderPushMapper coolOrderPushMapper;
    
    @Override
    public void cancelOrder() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, cancelOrderTimeoutSeconds * -1);
        CoolOrder param = new CoolOrder();
        param.setState(CoolOrder.ORDER_STATE_0);
        param.setCreatetime(cal.getTime());
        param.setArrears(0); // 未欠费订单 
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
    
    @Override
    public void backCommission() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, comissionBackTimeoutDays * -1);
        orderService.updateRunAccount(cal.getTime());
    }
    
    @Override
    public void getKJB2CLogisticsInfo() {
        CoolOrder param = new CoolOrder();
        param.setState(CoolOrder.ORDER_STATE_11);
        param.setFhfs(CoolOrder.FHFS_11);
        List<CoolOrder> orderList = orderService.getCoolOrderList(param);
        if (orderList != null) {
            for (CoolOrder order : orderList) {
                try {
                    orderService.updateCoolOrderNbyb(order.getOrderNo());
                } catch (Exception e) {
                    log.error(e);
                }
                
            }
        }
    }
    
    /**
     * 拼多多订单推送
     */
    @Override
    public void pushPinduoduoOrder() {
        List<CoolOrder> orderList = coolOrderPushMapper.getPinduoduoOrderList();
        if (CollectionUtils.isNotEmpty(orderList)) {
            for (CoolOrder order : orderList) {
                try {
                    Map<String, String> paramMap = new HashMap<String, String>();
                    paramMap.put("mType", "mSndGoods");
                    paramMap.put("OrderNO", order.getOrderOldno()); // 外部订单号
                    paramMap.put("SndStyle", order.getPsfs()); // 发货方式（中文 例：申通 圆通等）
                    paramMap.put("BillID", order.getPsCode()); // 货运单号
                    String result =
                        HttpUtils.httpPost(serviceDomain + "/pinduoduo/orderGoodsInfo",
                            JsonUtil.writeValueAsString(paramMap));
                    JsonData jsonResult = JsonUtil.readValue(result, JsonData.class);
                    String xml = jsonResult.getData().toString();
                    PinduoduoResult pr = JaxbUtil.converyToJavaBean(xml, PinduoduoResult.class);
                    if (pr.getResult() == 1) { // 成功 直接删除
                        coolOrderPushMapper.deleteCoolOrderPushByOrderNo(order.getOrderNo());
                    } else {
                        CoolOrderPush push = coolOrderPushMapper.getCoolOrderPushByOrderNo(order.getOrderNo());
                        if (push != null) {
                            push.setError(pr.getCause());
                            push.setCreatetime(new Date());
                            coolOrderPushMapper.updateCoolOrderPush(push);
                        }
                    }
                } catch (Exception e) {
                    log.error(e);
                }
            }
        }
        
    }
}

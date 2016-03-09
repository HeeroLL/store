package com.isell.task.order.service.impl;

import java.util.ArrayList;
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

import com.isell.core.util.DateUtil;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.dao.CoolOrderPushMapper;
import com.isell.service.order.dao.CoolShopPushMapper;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderPush;
import com.isell.task.order.service.TaskOrderService;
import com.isell.task.order.service.bean.KalemaoResult;
import com.isell.task.order.service.bean.PinduoduoOrderResult;
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
    
    /**
     * 外部商品推送
     */
    @Resource
    private CoolShopPushMapper coolShopPushMapper;
    
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
    
    /**
     * 拼多多订单下载
     */
    @Override
    public void downloadPinduoduoOrder() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mType", "mOrderSearch");
        paramMap.put("OrderStatus", "1"); // 订单状态，状态有3种。1，表示已付款（含货到付款）；0，表示未付款；-1表示问题单 只能填1！
        String result =
            HttpUtils.httpPost(serviceDomain + "/pinduoduo/orderGoodsInfo", JsonUtil.writeValueAsString(paramMap));
        JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
        if (jsonData.getSuccess()) {
            PinduoduoOrderResult orderResult =
                JaxbUtil.converyToJavaBean((String)jsonData.getData(), PinduoduoOrderResult.class);
            int count = orderResult.getOrderCount();
            if (count > 0) {
                List<String> strList = orderResult.getOrderList();
                for (String orderNo : strList) {// 外部订单号
                    Map<String, String> pMap = new HashMap<String, String>();
                    pMap.put("mType", "mGetOrder");
                    pMap.put("OrderNO", orderNo); // 外部订单号
                    String res =
                        HttpUtils.httpPost(serviceDomain + "/pinduoduo/getOrder", JsonUtil.writeValueAsString(pMap));
                    JsonData jData = JsonUtil.readValue(res, JsonData.class);
                    if (!jData.getSuccess()) {
                        log.debug("拼多多订单下载失败 " + DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date()) + ": "
                            + orderNo + " " + jData.getMsg());
                    }
                }
            }
        } else {
            log.info(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date()) + "，没有下载到拼多多订单。");
        }
    }
    
    @Override
    public void upKalemaoWayBillNo() {
        List<CoolOrder> orderList = coolShopPushMapper.getShopPushOrder();
        if (CollectionUtils.isNotEmpty(orderList)) {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            String[] orderNo = new String[orderList.size()];
            try {
                for (int i = 0, len = orderList.size(); i < len; i++) {
                    if ("0d49230fd9504330918ab1b743224abd".equals(orderList.get(i).getSupplier())) {// 卡乐猫酷店id
                        Map<String, String> paramMap = new HashMap<String, String>();
                        paramMap.put("order_sn", orderList.get(i).getOrderOldno()); // 外部订单号
                        paramMap.put("waybill_no", orderList.get(i).getPsCode()); // 货运单号
                        String shippingCode = "";// 货运单号
                        String shippingName = "";// 发货方式
                        if ("中通速递".equals(orderList.get(i).getPsfs())) {
                            shippingCode = "zto";
                            shippingName = "中通";
                        } else if ("邮政速递".equals(orderList.get(i).getPsfs())) {
                            shippingCode = "cnpl";
                            shippingName = "中邮";
                        } else if ("圆通速递".equals(orderList.get(i).getPsfs())) {
                            shippingCode = "yto";
                            shippingName = "圆通";
                        } else if ("韵达快运".equals(orderList.get(i).getPsfs())) {
                            shippingCode = "yunda";
                            shippingName = "韵达";
                        } else if ("汇通快运".equals(orderList.get(i).getPsfs())) {
                            shippingCode = "huitong";
                            shippingName = "汇通";
                        } else if ("申通快递".equals(orderList.get(i).getPsfs())) {
                            shippingCode = "sto";
                            shippingName = "申通";
                        }
                        log.info("shippingCode:" + shippingCode + "shippingName" + shippingName);
                        paramMap.put("shipping_code", shippingCode);
                        paramMap.put("shipping_name", shippingName);
                        list.add(paramMap);
                        orderNo[i] = orderList.get(i).getOrderOldno();
                    }
            		
                }
                if(CollectionUtils.isNotEmpty(list)) {
	                 String result =
	                    		HttpUtils.httpPost(serviceDomain +"/kalemao/upWayBillNo", JsonUtil.writeValueAsString(list));
	                    KalemaoResult kalemao = JsonUtil.readValue(result, KalemaoResult.class);
	                    if(kalemao.isData())
	                    	coolShopPushMapper.deleteByOrderNo(orderNo); 
                }
            } catch (Exception e) {
                log.error(e);
            }
        }
        
    }
}

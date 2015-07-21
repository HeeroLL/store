package com.sell.ei.logistics.ecm.service;

import com.sell.ei.logistics.ecm.vo.EcmOrders;
import com.sell.ei.logistics.ecm.vo.EcmParam;
import com.sell.ei.logistics.ecm.vo.EcmResponse;

/**
 * 费舍尔ECM服务接口封装层
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public interface EcmService {
    /** bis外网ip */
    String IP = "127.0.0.1";
    
    /** ecm版本号 */
    String V = "1.0";
    
    /** ecm给的appKey */
    String APP_KEY = "AYCA";
    
    /** ecm给的sessionKey */
    String SESSION_KEY = "2015AYCAV1";
    
    /** ECM给的URL */
    String URL = "http://60.191.39.196:8001/ecm/interface/rest/AYCInterface/";
    
    /** 4. 推送商品信息接口（sendCommodity） URL */
    String SENDCOMMODITY_URL = URL + "sendCommodity";
    
    /** 1. 推送销售订单接口（pushSaleOrder） URL */
    String PUSHSALEORDER_URL = URL + "pushSaleOrder";
    
    /**
     * 推送销售订单
     *
     * @param ecmOrders 订单信息
     * @return 封装后的ecm处理结果
     */
    EcmResponse pushSaleOrder(EcmOrders ecmOrders);
    
    /**
     * ECM回调服务（订单生产状态回传）
     *
     * @param param 参数
     * @return 返回值
     */
    EcmResponse sendOrderStatus(EcmParam param);
    
    /**
     * ECM回调服务（订单批量发货）
     *
     * @param param 参数
     * @return 返回值
     */
    EcmResponse sendShipOrder(EcmParam param);
}

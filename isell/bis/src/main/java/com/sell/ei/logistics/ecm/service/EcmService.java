package com.sell.ei.logistics.ecm.service;

import com.sell.ei.logistics.ecm.vo.Commodities;
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
     * 发送商品信息
     * 
     * @param commodities 商品信息
     * @return ecm处理结果
     */
    EcmResponse sendCommodity(Commodities commodities);
}

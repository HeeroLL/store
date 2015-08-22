package com.sell.ei.logistics.ecm.service;

import com.sell.bis.auth.bean.RequestParameter;
import com.sell.ei.logistics.ecm.vo.EcmCommodities;
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
    
    // String URL = "http://60.191.39.196:8001/ecm/interface/rest/AYCInterface/"; // 测试地址
    
    /** ECM给的URL */
    String URL = "http://60.191.39.195:8001/ecm/interface/rest/AYCInterface/";// 正式地址
    
    /** 1. 推送销售订单接口（pushSaleOrder） URL */
    String PUSHSALEORDER_URL = URL + "pushSaleOrder";
    
    /** 4. 推送商品信息接口（sendCommodity） URL */
    String SENDCOMMODITY_URL = URL + "sendCommodity";
    
    /** 客户编码 */
    String CUSTOMER_CODE = "AYC";
    
    /**
     * 推送商品信息接口
     * 
     * @param param 商品信息
     * @return ecm处理结果
     */
    EcmResponse sendCommodity(EcmCommodities param);
    
    /**
     * 推送销售订单
     * 
     * @param ecmOrders 请求参数
     * @return ecm处理结果
     */
    EcmResponse pushSaleOrder(RequestParameter param);
    
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

package com.isell.ei.pay.ehking.service;

import com.isell.ei.pay.ehking.bean.EhkingCustomsRequest;
import com.isell.ei.pay.ehking.bean.EhkingPayInfo;

/**
 * 易汇金支付service层接口
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
public interface EhkingService {
    /**
     * 商户编号
     */
    String MERCHANTID = "120140230";
    
    /**
     * 密钥
     */
    String KEY = "6d187a30e6d6505e5b314d581290c387";
    
    /**
     * 请求地址
     */
    String SERVICE_URL = "https://api.ehking.com";
    
    /**
     * 在线支付地址
     */
    String ORDER_URL = SERVICE_URL + "/onlinePay/order";
    
    /**
     * 退款地址
     */
    String REFUND_URL = SERVICE_URL + "/onlinePay/refund";
    
    /**
     * 报关接口地址
     */
    String CUSTOMS_URL = SERVICE_URL + "/customs/order";
    
    /**
     * 获取支付url
     *
     * @param ehkingPayInfo 支付信息
     * @return url
     */
    String getPayUrl(EhkingPayInfo ehkingPayInfo);
    
    /**
     * 发送订单消息
     *
     * @param request 易汇金海关报关参数
     * @return 同步响应结果
     */
    String sendOrder(EhkingCustomsRequest request);
    
}

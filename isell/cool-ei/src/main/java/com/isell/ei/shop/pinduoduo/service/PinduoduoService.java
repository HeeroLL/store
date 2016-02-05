package com.isell.ei.shop.pinduoduo.service;

import java.util.Map;

import com.isell.core.util.JsonData;

/**
 * 拼多多业务层接口
 * 
 * @author lilin
 * @version [版本号, 2015年12月21日]
 */
public interface PinduoduoService {
    /**
     * 发送地址
     */
    String SEND_URL = "http://mms.yangkeduo.com:8889/OrderInfo";
    
    /**
     * 接入码，用于验证请求的有效性。主要用于区分店铺。
     */
    String UCODE = "111";
    
    /**
     * 密钥
     */
    String SECRET = "xiqFQnn0OcOt62eA";
    
    /**
     * 拼多多订单接口
     * 
     * @param paramMap 查询参数
     * @return 响应信息
     */
    String orderGoodsInfo(Map<String, String> paramMap);
    
    /**
     * 拼多多订单明细接口
     * 
     * @param paramMap 查询参数
     * @return 响应信息
     */
    JsonData saveOrder(Map<String, String> paramMap);
}

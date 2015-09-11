package com.isell.ei.logistics.yuantong.service;

import com.isell.ei.logistics.yuantong.bean.OrderRequest;
import com.isell.ei.logistics.yuantong.bean.OrderResponse;

/**
 * 圆通物流服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年9月7日]
 */
public interface YuantongService {
    /**
     * 商家账号
     */
    // String CLIENT_ID = "K51912835"; 
    String CLIENT_ID = "K24000154";
    
    /**
     * 商家密钥
     */
    // String PARTNER_ID = "B657MDM2";
    String PARTNER_ID = "weH71Rbq";
    
    /**
     * 下单地址
     */
    String PLACEORDER_URL = "http://58.32.246.71:8000/CommonOrderModeBServlet.action";
    
    /**
     * 通知圆通下单
     *
     * @param orderRequest 订单信息
     * @return 圆通返回信息
     */
    OrderResponse placeOrder(OrderRequest orderRequest);
}

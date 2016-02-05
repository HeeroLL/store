package com.isell.ei.pay.zjys.service;

import com.isell.ei.pay.zjys.vo.ZjysBuyTradeNoRes;
import com.isell.service.order.vo.CoolOrder;

/**
 * 浙江银商支付服务层接口
 * 
 * @author lilin
 * @version [版本号, 2016年1月28日]
 */
public interface ZjysService {
    /**
     * 购买支付流水请求地址
     */
    String BUYTRADENO_URL = "http://api.aigouguoji.com/api";
    
    /**
     * 账号
     */
    String ACCOUNT = "isell";
    
    /**
     * 密码
     */
    String PWD = "123123";
    
    /**
     * 购买支付流水号
     *
     * @param coolOrder 订单信息集合
     * @return 响应信息
     */
    ZjysBuyTradeNoRes buyTradeNo(CoolOrder... coolOrders);
}

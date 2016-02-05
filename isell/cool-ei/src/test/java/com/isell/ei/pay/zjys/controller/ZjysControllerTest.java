package com.isell.ei.pay.zjys.controller;

import java.math.BigDecimal;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.vo.CoolOrder;

/**
 * 浙江银商购买流水号
 * 
 * @author lilin
 * @version [版本号, 2016年1月28日]
 */
public class ZjysControllerTest {
    
 private static final String host = "http://localhost:8080";
    
    private static final String BUY_URL = host + "/bis/pay/zjys/buyTradeNo";
    
    @Test
    public void testBuyTradeNo() {
        CoolOrder coolOrder = new CoolOrder();
        coolOrder.setOrderNo("CO201601290923441373");
        coolOrder.setLinkman("李建功");
        coolOrder.setIdcard("620923199009233717");
        coolOrder.setMobile("18752942551");
        coolOrder.setTotal(new BigDecimal(95));
        
        String result = HttpUtils.httpPost(BUY_URL, JsonUtil.writeValueAsString(coolOrder));
        System.out.println(result);
    }
    
}

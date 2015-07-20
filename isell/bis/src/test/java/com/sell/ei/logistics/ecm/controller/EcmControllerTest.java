package com.sell.ei.logistics.ecm.controller;

import java.util.ArrayList;

import org.junit.Test;

import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.logistics.ecm.vo.Commodities;
import com.sell.ei.logistics.ecm.vo.Commodity;

/**
 * 费舍尔ECM接口测试类
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmControllerTest {
    
    @Test
    public void testSendCommodity() {
        Commodities commodities = new Commodities();
        Commodity commodity = new Commodity();
        commodity.setCommodityCode("15098765" + "0001");
        commodity.setCommodityName("九朵云 马油");
        commodity.setCommoditySpec("007"); // 个
        commodity.setUnit("007"); // 个
        commodity.setWeight("0.1"); // 重量 KG
        commodity.setTradeCountryCode("133");
        commodity.setTradeCountryName("韩国");
        
        commodities.setCommoditys(new ArrayList<Commodity>());
        commodities.getCommoditys().add(commodity);
        
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/ecm/sendCommodity", JsonUtil.writeValueAsString(commodities));
        System.out.println(result);
    }
    
}

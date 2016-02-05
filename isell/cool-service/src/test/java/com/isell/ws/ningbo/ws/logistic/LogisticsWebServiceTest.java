package com.isell.ws.ningbo.ws.logistic;

import org.junit.Test;

import com.isell.ws.ningbo.service.YoubeiService;

/**
 * 优贝获取物流单号测试类
 * 
 * @author lilin
 * @version [版本号, 2015年12月21日]
 */
public class LogisticsWebServiceTest {
    
    @Test
    public void test() {
        String orderNo = "CO201512181351469485";
        LogisticsWebServiceSoap serviceSoap = new LogisticsWebService().getLogisticsWebServiceSoap12();
        String result =
            serviceSoap.getKJB2CLogisticsInfo(YoubeiService.ERPKEY,
                YoubeiService.ERPSECRET,
                YoubeiService.SHOPID,
                orderNo);
        System.out.println(result);
    }
    
}

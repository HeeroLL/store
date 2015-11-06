package com.isell.ei.pay.alipay.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class AlipayControllerTest {
    
    @Test
    public void testSendOrder() {
        Map<String, String> map = new HashMap<String, String>();
        // 报关流水号，商户生成的用于唯一标识一次 报关操作的业务编号。 建议生成规则：yyyymmmdd 型 8位日期拼接 4 位序列号
        map.put("out_request_no", System.currentTimeMillis() + "");
        map.put("trade_no", "2015051446800462"); // 支付宝交易号
        map.put("merchant_customs_code", "hanguo"); // 商户海关备案编号
        map.put("amount", "2"); // 报关金额
        map.put("customs_place", "ZHENGZHOU"); // 海关编号
        map.put("merchant_customs_name", "jwyhanguo_card"); // 商户海关备案名称
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/pay/alipay/sendOrder", JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
}

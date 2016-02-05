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
        map.put("out_request_no", "isell" + System.currentTimeMillis());
        map.put("trade_no", "2016011821001004820018956698"); // 支付宝交易号
        map.put("merchant_customs_code", "3117964017"); // 商户海关备案编号
        map.put("amount", "68"); // 报关金额
        map.put("customs_place", "HENAN"); // 海关编号 如果您的支付单要报关到新郑综合保税区，请在报关接口的海关字段中填写HENAN。
        map.put("merchant_customs_name", "上海艾售电子商务有限公司"); // 商户海关备案名称 上海艾售电子商务有限公司jwyhanguo_card
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/pay/alipay/sendOrder", JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
    
    @Test
    public void testSendOrder2() {
        Map<String, String> map = new HashMap<String, String>();
        // 报关流水号，商户生成的用于唯一标识一次 报关操作的业务编号。 建议生成规则：yyyymmmdd 型 8位日期拼接 4 位序列号
        map.put("out_request_no", "isell" + System.currentTimeMillis());
        map.put("trade_no", "2016012021001004820017913038"); // 支付宝交易号
        map.put("merchant_customs_code", "3117964017"); // 商户海关备案编号
        map.put("amount", "78"); // 报关金额
        map.put("customs_place", "NINGBO"); // 海关编号 如果您的支付单要报关到新郑综合保税区，请在报关接口的海关字段中填写HENAN。
        map.put("merchant_customs_name", "上海艾售电子商务有限公司"); // 商户海关备案名称 
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/pay/alipay/sendOrder", JsonUtil.writeValueAsString(map));
        System.out.println(result);
    }
}

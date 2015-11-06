package com.isell.ei.logistics.zhongtong.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class ZhongtongControllerTest {
    
    @Test
    public void testGetMarkService() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("send_province", "河南省");
        param.put("send_address", "保税仓");
        param.put("receive_province", "江苏省");
        param.put("receive_city", "南京市");
        param.put("receive_district", "玄武区");
        param.put("receive_address", "aaaaa");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/logistics/zhongtong/getMarkService",
                JsonUtil.writeValueAsString(param));
        System.out.println(result);
    }
    
}

package com.isell.ei.logistics.zyb.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;

public class ZybControllerTest {
    
    @Test
    public void testQueryStatus() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("transfer_no", "TBA810001838066");
        // param.put("transfer_no", "585813206487");
        // param.put("transfer_no", "617777509390");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/logistics/zyb/queryStatus",
                JsonUtil.writeValueAsString(param));
        System.out.println(result);
    }
    
}

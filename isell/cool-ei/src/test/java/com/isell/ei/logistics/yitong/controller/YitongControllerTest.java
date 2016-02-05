package com.isell.ei.logistics.yitong.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;

public class YitongControllerTest {
    
    @Test
    public void testSendOrder() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNos", "CO201512041853215422");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/logistics/yitong/sendOrder", map);
        System.out.println(result);
    }
    
    @Test
    public void testDeleteOrder() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNos", "CO201512081000309061461,CO201512081000308366851,CO201512081000315546449,CO201512081000316697200,CO201512081000317826291,CO201512090945587297701,CO201512090945588297837,CO201512171637340219647,CO201512171634465231833,CO201512181516235833357,CO201512210925575810,CO201512211333038272,CO201512071419487502,CO201512140928426626,CO201512250920508755674,CO201512080852206510,CO201512251043049009,CO201512251045288862,CO201512211114258219");
        
        String result =
            HttpUtils.httpPost("http://service.i-coolshop.com//logistics/yitong/deleteOrder", map);
        System.out.println(result);
    }
}

package com.sell.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sell.ei.pay.weixin.bean.PayResultInfo;

@Controller
public class DemoController {
    
    @RequestMapping(value="/demo")
    @ResponseBody
    public Map<String, Object> demo(@RequestBody Map<String, Object> requestMap) {
        requestMap.put("result", true);
        
        return requestMap;
    }
    
    @RequestMapping(value="/demo2")
    @ResponseBody
    public Person demo2(@RequestBody Person person) {
        person.setName("serverName");
        return person;
    }
    
    @RequestMapping(value="/testPayResult")
    @ResponseBody
    public PayResultInfo testPayResult(@RequestBody PayResultInfo payResultInfo) {
        return payResultInfo;
    }
}

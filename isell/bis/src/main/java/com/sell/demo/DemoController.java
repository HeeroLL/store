package com.sell.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    
    @RequestMapping("/demo")
    @ResponseBody
    public Map<String, Object> demo(@RequestBody Map<String, Object> requestMap) {
        System.out.println(requestMap);
        requestMap.put("result", true);
        
        return requestMap;
    }
}

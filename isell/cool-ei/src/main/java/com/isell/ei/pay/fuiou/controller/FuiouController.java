package com.isell.ei.pay.fuiou.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isell.ei.pay.fuiou.service.FuiouService;

/**
 * 富友支付控制层
 * 
 * @author 毛伟杰
 * @version [版本号, 2016年3月8日]
 */
@Controller
@RequestMapping("pay/fuiou")
public class FuiouController {
	  /**
     * 富友支付service层接口
     */
    @Resource
    private FuiouService fuiouService;
	
    
    /**
     * 富友支付
     * 
     * @param paramMap 参数信息
     * @param map 返回值
     * @return 返回的参数字符串
     */
    @RequestMapping("fuyou")
    public String webPay(@RequestBody Map<String, String> paramMap,ModelMap map) {
    	map.putAll(paramMap);
        map.addAttribute("result", fuiouService.webPay(paramMap));
        return "result";
    }
	
}

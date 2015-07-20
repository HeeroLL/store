package com.sell.ei.logistics.ecm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sell.core.web.JsonData;
import com.sell.ei.logistics.ecm.service.EcmService;
import com.sell.ei.logistics.ecm.vo.Commodities;

/**
 * 费舍尔ECM接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
@Controller
@RequestMapping("logistics/ecm")
public class EcmController {
    /**
     * 费舍尔ECM服务接口
     */
    @Resource
    private EcmService ecmService;
    
    /**
     * ECM商品推送接口
     *
     * @param commodities 商品列表
     * @return 封装后的ECM返回的处理结果
     */
    @ResponseBody
    @RequestMapping("sendCommodity")
    public JsonData sendCommodity(@RequestBody Commodities commodities) {
        JsonData jsonData = new JsonData();
        jsonData.setData(ecmService.sendCommodity(commodities));
        return jsonData;
    }
}

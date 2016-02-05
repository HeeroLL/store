package com.isell.ei.shop.pinduoduo.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.shop.pinduoduo.service.PinduoduoService;

/**
 * 拼多多订单同步接口控制层
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@Controller
@RequestMapping("pinduoduo")
public class PinduoduoController {
    /**
     *  拼多多业务层接口
     */
    @Resource
    private PinduoduoService pinduoduoService;
    
    /**
     * 拼多多订单接口
     *
     * @param paramMap 查询参数
     * @return 响应信息
     */
    @ResponseBody
    @RequestMapping("orderGoodsInfo")
    public JsonData orderGoodsInfo(@RequestBody Map<String, String> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(pinduoduoService.orderGoodsInfo(paramMap));
        return jsonData;
    }
    
    /**
     * 拼多多订单明细接口
     *
     * @param paramMap 查询参数
     * @return 响应信息
     */
    @ResponseBody
    @RequestMapping("getOrder")
    public JsonData getOrder(@RequestBody Map<String, String> paramMap) {
        return pinduoduoService.saveOrder(paramMap);
    }
}

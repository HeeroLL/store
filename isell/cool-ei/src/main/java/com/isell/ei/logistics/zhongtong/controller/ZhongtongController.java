package com.isell.ei.logistics.zhongtong.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.logistics.zhongtong.bean.ZTOrderResponse;
import com.isell.ei.logistics.zhongtong.service.ZhongtongService;

/**
 * 中通国际接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年9月6日]
 */
@RequestMapping("logistics/zhongtong")
@Controller
public class ZhongtongController {
    /**
     * 中通接口
     */
    @Resource
    private ZhongtongService zhongtongService;
    
    /**
     * 获取大头笔
     *
     * @param param 请求信息
     * @return 封装后的中通返回信息
     */
    @RequestMapping("getMarkService")
    @ResponseBody
    public JsonData getMarkService(@RequestBody Map<String, String> param) {
        ZTOrderResponse orderResponse = zhongtongService.getMarkService(param);
        JsonData jsonData = new JsonData();
        jsonData.setSuccess(orderResponse.getStatus());
        jsonData.setData(orderResponse.getResult());
        jsonData.setMsg(orderResponse.getMessage());
        
        return jsonData;
    }
}

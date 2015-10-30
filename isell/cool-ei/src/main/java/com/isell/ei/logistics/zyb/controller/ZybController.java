package com.isell.ei.logistics.zyb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.logistics.zyb.bean.ZybResult;
import com.isell.ei.logistics.zyb.service.ZybService;

/**
 * 香港转运帮接口
 * 
 * @author lilin
 * @version [版本号, 2015年10月27日]
 */
@RequestMapping("logistics/zyb")
@Controller
public class ZybController {
    /**
     * 转运帮接口
     */
    @Resource
    private ZybService zybService;
    
    /**
     * 状态查询
     * 
     * @param content 参数
     * @return 封装后的状态数据
     */
    @RequestMapping("queryStatus")
    @ResponseBody
    public JsonData queryStatus(@RequestBody String content) {
        ZybResult result = zybService.queryStatus(content);
        JsonData jsonData = new JsonData();
        jsonData.setSuccess(result.getS() != null && result.getS() == 0);
        jsonData.setMsg(result.getMessage());
        jsonData.setData(result.getData());
        return jsonData;
    }
}

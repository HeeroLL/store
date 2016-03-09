package com.isell.ei.idcard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.idcard.service.IdcardService;

/**
 * 身份证验证接口控制层
 * 
 * @author wangpeng
 * @version [版本号, 2016年3月3日]
 */
@Controller
@RequestMapping("idcard")
public class IdcardController {
    /**
     *  身份证认证接口
     */
    @Resource
    private IdcardService idcardService;
    
    /**
     * 获取身份证信息接口
     *
     * @param paramMap 查询参数
     * @return 响应信息
     */
    @ResponseBody
    @RequestMapping("getIdcardInfo")
    public JsonData getIdcardInfo(String idCard){
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = idcardService.getIdcardInfo(idCard).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }        
        jsonData.setData(resultMap);
        return jsonData;
    }    
}

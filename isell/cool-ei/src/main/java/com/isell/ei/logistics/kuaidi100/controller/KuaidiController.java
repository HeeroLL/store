package com.isell.ei.logistics.kuaidi100.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.logistics.kuaidi100.service.KuaidiService;

/**
 * 快递100物流查询接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年8月9日]
 */
@Controller
@RequestMapping("logistics/kuaidi")
public class KuaidiController {
    /**
     * 快递100查询接口
     */
    @Resource
    private KuaidiService KuaidiService;
    
    /**
     * 快递查询接口，返回json格式
     * 
     * @param com 快递公司代码
     * @param nu 快递单号
     * @return 查询结果
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping("jsonService")
    public JsonData jsonService(String com, String nu) {
        return KuaidiService.jsonService(com, nu);
    }
    
    /**
     * 快递查询接口，返回web页面
     * 
     * @param com 快递公司代码
     * @param nu 快递单号
     * @param map 返回值
     * @return 页面
     */
    @RequestMapping("webService")
    public String webService(String com, String nu, ModelMap map) {
        map.addAttribute("result", KuaidiService.webService(com, nu));
        return "result";
    }
    
    /**
     * 已弃用。快递100不支持这种调用方式，请直接通过get方式访问快递100wap查询界面
     * <br>快递查询接口，返回wap页面(html5页面)
     * 
     * @param com 快递公司代码
     * @param nu 快递单号
     * @param callbackurl 点击"返回"跳转的地址
     * @param map 返回值
     * @return 页面
     */
    @RequestMapping("wapService")
    public String wapService(String com, String nu, String callbackurl, ModelMap map) {
        map.addAttribute("result", KuaidiService.wapService(com, nu, callbackurl));
        return "result";
    }
}

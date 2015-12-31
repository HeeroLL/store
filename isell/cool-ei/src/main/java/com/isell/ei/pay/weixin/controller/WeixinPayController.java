package com.isell.ei.pay.weixin.controller;

import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.pay.weixin.bean.WeixinCustomsResponse;
import com.isell.ei.pay.weixin.bean.WeixinPayResultInfo;
import com.isell.ei.pay.weixin.service.WeixinPayService;

/**
 * 微信支付控制层
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@Controller
@RequestMapping("pay/weixin")
public class WeixinPayController {
    
    /**
     * 微信支付服务接口
     */
    @Resource
    private WeixinPayService weixinPayService;
    
    /**
     * 统一下单接口(JSAPI)
     * 
     * @param paramMap map参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("unifiedorder")
    public JsonData unifiedorder(@RequestBody TreeMap<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinPayService.unifiedorder(paramMap));
        return jsonData;
    }
    
    /**
     * 查询订单接口
     * 
     * @param paramMap map参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("orderquery")
    public JsonData orderquery(@RequestBody TreeMap<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinPayService.orderquery(paramMap));
        return jsonData;
    }
    
    /**
     * 关闭订单接口
     * 
     * @param paramMap map参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("closeorder")
    public JsonData closeorder(@RequestBody TreeMap<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinPayService.closeorder(paramMap));
        return jsonData;
    }
    
    /**
     * 支付结果通知接口<br>
     * 该接口是通过【统一下单API】中提交的参数notify_url设置，如果链接无法访问，商户将无法接收到微信通知。<br>
     * 需要做权限认证
     * 
     * @param payResultInfo 请求参数
     * @return 返回Map
     */
    @ResponseBody
    @RequestMapping("sendPayResult")
    public WeixinPayResultInfo sendPayResult(@RequestBody WeixinPayResultInfo payResultInfo) {
        return weixinPayService.sendPayResult(payResultInfo);
    }
    
    /**
     * 财付通报关
     *
     * @param paramMap 财付通报关参数
     * @return 封装后的报关响应
     */
    @ResponseBody
    @RequestMapping("sendOrder")
    public JsonData sendOrder(@RequestBody TreeMap<String, String> paramMap) {
        JsonData jsonData = new JsonData();
        WeixinCustomsResponse response = weixinPayService.sendOrder(paramMap);
        jsonData.setSuccess("0".equals(response.getRetcode()));
        jsonData.setMsg(response.getRetmsg());
        jsonData.setData(response);
        return jsonData;
    }
    
    /**
     * 支付单申报海关状态查询
     *
     * @param paramMap 查询参数
     * @return 响应信息
     */
    @ResponseBody
    @RequestMapping("customQuery")
    public JsonData customQuery(@RequestBody TreeMap<String, String> paramMap) {
        JsonData jsonData = new JsonData();
        TreeMap<String, Object> responseMap = weixinPayService.customQuery(paramMap);
        jsonData.setSuccess("0".equals(responseMap.get("retcode")));
        jsonData.setMsg((String)responseMap.get("retmsg"));
        jsonData.setData(responseMap);
        return jsonData;
    }
    
    /**
     * 支付单申报结果下载
     *
     * @param paramMap 查询参数
     * @return 响应信息
     */
    @ResponseBody
    @RequestMapping("downloadBill")
    public JsonData downloadBill(@RequestBody TreeMap<String, String> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setData(weixinPayService.downloadBill(paramMap));
        return jsonData;
    }
}

package com.isell.ei.pay.weixin.service.impl;

import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.Identities;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.weixin.bean.WeixinCustomsResponse;
import com.isell.ei.pay.weixin.bean.WeixinPayResultInfo;
import com.isell.ei.pay.weixin.service.WeixinPayService;
import com.isell.ei.pay.weixin.util.WeixinPayUtil;

/**
 * 微信支付接口服务层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@Service("weixinPayService")
public class WeixinPayServiceImpl implements WeixinPayService {
    
    @Override
    public TreeMap<String, Object> unifiedorder(TreeMap<String, Object> paramMap) {
        // 修改通知url
        // paramMap.put("attach", paramMap.get("notify_url"));
        // paramMap.put("notify_url", bisHost + "/pay/weixin/sendPayResult");
        if (paramMap.get("trade_type") == null) {
            paramMap.put("trade_type", "JSAPI"); // APP或网页支付
        }
        generateMap(paramMap);
        
        // xml格式发送请求
        String xml = WeixinPayUtil.transMapToXml(paramMap);
        String result = HttpUtils.httpsPost(UNIFIEDORDER, xml, "application/xml");
        
        TreeMap<String, Object> resultMap = WeixinPayUtil.transXmlToMap(result);
        // 生成微信支付请求参数
        TreeMap<String, Object> requestMap = new TreeMap<String, Object>();
        
        if ("NATIVE".equals(resultMap.get("trade_type"))) { // 扫码支付
            requestMap.put("code_url", resultMap.get("code_url")); // 支付二维码的链接
        } else {
            requestMap.put("appId", APPID);
            requestMap.put("timeStamp", System.currentTimeMillis() / 1000 + ""); // 转为String
            requestMap.put("nonceStr", Identities.uuid());
            requestMap.put("package", "prepay_id=" + resultMap.get("prepay_id")); // 预付款id
            requestMap.put("signType", "MD5");
            requestMap.put("paySign", WeixinPayUtil.encryptString(WeixinPayUtil.getParameter(requestMap), KEY));
        }
        
        return requestMap;
    }
    
    @Override
    public TreeMap<String, Object> orderquery(TreeMap<String, Object> paramMap) {
        generateMap(paramMap);
        // xml格式发送请求
        String xml = WeixinPayUtil.transMapToXml(paramMap);
        String result = HttpUtils.httpsPost(ORDERQUERY, xml, "application/xml");
        
        return WeixinPayUtil.transXmlToMap(result);
    }
    
    @Override
    public TreeMap<String, Object> closeorder(TreeMap<String, Object> paramMap) {
        generateMap(paramMap);
        // xml格式发送请求
        String xml = WeixinPayUtil.transMapToXml(paramMap);
        String result = HttpUtils.httpsPost(CLOSEORDER, xml, "application/xml");
        
        return WeixinPayUtil.transXmlToMap(result);
    }
    
    @Override
    public WeixinPayResultInfo sendPayResult(WeixinPayResultInfo payResultInfo) {
        WeixinPayResultInfo response = new WeixinPayResultInfo();
        // 校验签名
        String sign = WeixinPayUtil.encryptString(WeixinPayUtil.generateSign(payResultInfo), KEY);
        if (!sign.equals(payResultInfo.getSign())) {
            response.setReturnCode("FAIL");
            response.setReturnMsg("签名验证失败");
            return response;
        }
        // 发消息给对应的系统
        WeixinPayResultInfo newParam = new WeixinPayResultInfo();
        BeanUtils.copyProperties(payResultInfo, newParam, "appid", "mchId", "nonceStr", "sign", "attach");
        String notifyUrl = payResultInfo.getAttach(); // 对应系统的通知URL存在参数attach中
        // json格式发送请求
        String result = HttpUtils.httpPost(notifyUrl, JsonUtil.writeValueAsString(newParam));
        
        return JsonUtil.readValue(result, WeixinPayResultInfo.class);
    }
    
    /**
     * 设置一些必填参数
     * 
     * @param paramMap 参数map
     */
    private void generateMap(TreeMap<String, Object> paramMap) {
        paramMap.put("appid", APPID);
        paramMap.put("mch_id", MCH_ID);
        paramMap.put("nonce_str", Identities.uuid());
        
        paramMap.put("sign", WeixinPayUtil.encryptString(WeixinPayUtil.getParameter(paramMap), KEY));
    }
    
    @Override
    public WeixinCustomsResponse sendOrder(TreeMap<String, String> paramMap) {
        paramMap.put("input_charset", "UTF-8"); // 字符集
        paramMap.put("partner", WeixinPayService.MCH_ID); // 微信商户号
        if (paramMap.get("mch_customs_no") == null) {
            paramMap.put("mch_customs_no", "3117964017"); // 商户海关备案号    
        }
        if (paramMap.get("customs") == null) {
            paramMap.put("customs", "9"); // 海关编号：9 郑州（综保区）
        } 
        paramMap.put("action_type", "1"); // 操作类型 1 新增 2修改（修改暂只支持广州海关）
        paramMap.put("sign", WeixinPayUtil.encryptString(WeixinPayUtil.getCustomsParameter(paramMap), KEY));
        
        String result = HttpUtils.httpPost(SENDORDER, paramMap);
        
        return JaxbUtil.converyToJavaBean(result, WeixinCustomsResponse.class);
    }
    
    @Override
    public TreeMap<String, Object> customQuery(TreeMap<String, String> paramMap) {
        paramMap.put("input_charset", "UTF-8"); // 字符集
        paramMap.put("partner", WeixinPayService.MCH_ID); // 微信商户号
        paramMap.put("customs", "9"); // 海关编号：9 郑州（综保区）
        paramMap.put("sign", WeixinPayUtil.encryptString(WeixinPayUtil.getCustomsParameter(paramMap), KEY));
        
        String result = HttpUtils.httpPost(CUSTOMQUERY, paramMap);
        return WeixinPayUtil.transXmlToMap(result);
    }

    @Override
    public String downloadBill(TreeMap<String, String> paramMap) {
        paramMap.put("input_charset", "UTF-8"); // 字符集
        paramMap.put("partner", WeixinPayService.MCH_ID); // 微信商户号
        paramMap.put("sign", WeixinPayUtil.encryptString(WeixinPayUtil.getCustomsParameter(paramMap), WeixinPayService.KEY));
        
        String result = HttpUtils.httpPost(DOWNLOADBILL, paramMap);
        return result;
    }
}

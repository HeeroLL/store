package com.sell.ei.pay.weixin.service.impl;

import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sell.core.util.HttpUtils;
import com.sell.core.util.Identities;
import com.sell.core.util.JsonUtil;
import com.sell.ei.pay.weixin.bean.PayResultInfo;
import com.sell.ei.pay.weixin.service.WeixinPayService;
import com.sell.ei.pay.weixin.util.WeixinUtil;

/**
 * 微信支付接口服务层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@Service("weixinPayService")
public class WeixinPayServiceImpl implements WeixinPayService {
    /**
     * bis域名地址
     */
    @Value("${bis.host}")
    private String bisHost;
    
    @Override
    public TreeMap<String, Object> unifiedorder(TreeMap<String, Object> paramMap) {
        // 修改通知url
        paramMap.put("attach", paramMap.get("notify_url"));
        paramMap.put("notify_url", bisHost + "/pay/weixin/sendPayResult");
        generateMap(paramMap);
        
        // xml格式发送请求
        String xml = WeixinUtil.transMapToXml(paramMap);
        System.out.println(xml);
        String result = HttpUtils.httpsPost(UNIFIEDORDER, xml, "application/xml");
        System.out.println(result);
        
        return WeixinUtil.transXmlToMap(result);
    }
    
    @Override
    public TreeMap<String, Object> orderquery(TreeMap<String, Object> paramMap) {
        generateMap(paramMap);
        // xml格式发送请求
        String xml = WeixinUtil.transMapToXml(paramMap);
        System.out.println(xml);
        String result = HttpUtils.httpsPost(ORDERQUERY, xml, "application/xml");
        System.out.println(result);
        
        return WeixinUtil.transXmlToMap(result);
    }
    
    @Override
    public TreeMap<String, Object> closeorder(TreeMap<String, Object> paramMap) {
        generateMap(paramMap);
        // xml格式发送请求
        String xml = WeixinUtil.transMapToXml(paramMap);
        System.out.println(xml);
        String result = HttpUtils.httpsPost(CLOSEORDER, xml, "application/xml");
        System.out.println(result);
        
        return WeixinUtil.transXmlToMap(result);
    }
    
    @Override
    public PayResultInfo sendPayResult(PayResultInfo payResultInfo) {
        PayResultInfo response = new PayResultInfo();
        // 校验签名
        String sign = WeixinUtil.encryptString(WeixinUtil.generateSign(payResultInfo), KEY);
        if (!sign.equals(payResultInfo.getSign())) {
            response.setReturnCode("FAIL");
            response.setReturnMsg("签名验证失败");
            return response;
        }
        // 发消息给对应的系统
        PayResultInfo newParam = new PayResultInfo();
        BeanUtils.copyProperties(payResultInfo, newParam, "appid", "mchId", "nonceStr", "sign", "attach");
        String notifyUrl = payResultInfo.getAttach(); // 对应系统的通知URL存在参数attach中
        // json格式发送请求
        System.out.println(newParam);
        String result = HttpUtils.httpPost(notifyUrl, JsonUtil.writeValueAsString(newParam));
        System.out.println(result);
        
        return JsonUtil.readValue(result, PayResultInfo.class);
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
        
        paramMap.put("sign", WeixinUtil.encryptString(WeixinUtil.getParameter(paramMap), KEY));
    }
}

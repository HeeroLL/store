package com.isell.ei.pay.alipay.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.ei.pay.alipay.bean.SendOrderResponse;
import com.isell.ei.pay.alipay.service.AlipayService;
import com.isell.ei.pay.alipay.util.AlipayUtil;

/**
 * 支付宝支付service层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月26日]
 */
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {
    
    /**
     * log
     */
    private Logger log = Logger.getLogger(AlipayServiceImpl.class);
    
    /**
     * 服务的域名
     */
    @Value("${service_domain}")
    private String serviceDomain;
    
    @Override
    public String getPayParams(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = AlipayUtil.paraFilter(paramMap);
        paramMap.put("partner", PARTNER);
        paramMap.put("seller_id", PARTNER);
        paramMap.put("_input_charset", INPUT_CHARSET);
        paramMap.put("sign_type", SIGN_TYPE);
        paramMap.put("payment_type", PAYMENT_TYPE);
        paramMap.put("notify_url", serviceDomain + "/payNotify/alipay");
        // 如果需要使用网银功能才设此参数
        // paramMap.put("defaultbank", "CCB");
        
        paramMap.put("sign", AlipayUtil.encryptString(AlipayUtil.getParameter(paramMap), KEY));
        
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id='alipaysubmit' name='alipaysubmit' action='" + ALIPAY_GATEWAY_NEW + "' method='"
            + METHOD + "'>");
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type='submit' value='确认' style='display:none;'></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString();
    }
    
    @Override
    public String getBatchTransParams(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = AlipayUtil.paraFilter(paramMap);
        paramMap.put("partner", PARTNER);
        paramMap.put("_input_charset", INPUT_CHARSET);
        paramMap.put("sign_type", SIGN_TYPE);
        paramMap.put("account_name", ACCOUNT_NAME);
        paramMap.put("email", EMAIL);
        
        paramMap.put("sign", AlipayUtil.encryptString(AlipayUtil.getParameter(paramMap), KEY));
        
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id='alipaysubmit' name='alipaysubmit' action='" + ALIPAY_GATEWAY_NEW + "' method='get'>"); // 必须get提交
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type='submit' value='确认' style='display:none;'></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString();
    }
    
    @Override
    public String getPayParamsNew(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = AlipayUtil.paraFilter(paramMap);
        paramMap.put("partner", PARTNER);
        paramMap.put("seller_id", PARTNER);
        paramMap.put("_input_charset", INPUT_CHARSET);
        paramMap.put("sign_type", SIGN_TYPE);
        paramMap.put("product_code", PRODUCT_CODE);
        paramMap.put("notify_url", serviceDomain + "/payNotify/alipay");
        
        paramMap.put("sign", AlipayUtil.encryptString(AlipayUtil.getParameter(paramMap), KEY));
        
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id='alipaysubmit' name='alipaysubmit' action='" + ALIPAY_GATEWAY_NEW + "' method='"
            + METHOD + "'>");
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type='submit' value='确认' style='display:none;'></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString();
    }
    
    @Override
    public SendOrderResponse sendOrder(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = AlipayUtil.paraFilter(paramMap);
        paramMap.put("partner", PARTNER);
        paramMap.put("_input_charset", INPUT_CHARSET);
        paramMap.put("sign", AlipayUtil.encryptString(AlipayUtil.getParameter(paramMap), KEY));
        paramMap.put("sign_type", SIGN_TYPE);
        String result = HttpUtils.httpsPost(ALIPAY_GATEWAY_NEW, paramMap, "application/x-www-form-urlencoded");
        return JaxbUtil.converyToJavaBean(result, SendOrderResponse.class);
    }
}

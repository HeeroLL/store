package com.isell.ei.pay.yijifu.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.Identities;
import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.yijifu.service.YijifuService;
import com.isell.ei.pay.yijifu.util.YijifuUtil;

/**
 * 易极付支付service层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月26日]
 */
@Service("yijifuService")
public class YijifuServiceImpl implements YijifuService {
    
    /**
     * log
     */
    private Logger log = Logger.getLogger(YijifuServiceImpl.class);
    
    /**
     * 服务的域名
     */
    @Value("${service_domain}")
    private String serviceDomain;
    
    @Override
    public String getPayParams(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("partnerId", PAY_PARTNERID);
        paramMap.put("sellerUserId", PAY_PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("version", "2.0"); // 2.0接口可以返回outOrderNo 外部订单号
        // paramMap.put("returnUrl", "http://www.i-coolshop.com");
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifu");
        
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), PAY_KEY));
        
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id=\"yijifusubmit\" name=\"yijifusubmit\" action=\"" + YIJIFU_GATEWAY
            + "\" method=\"POST\">");
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type=\"hidden\" name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"确认\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['yijifusubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String> paymentBillV2Order(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("partnerId", PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        // paramMap.put("version", "1.0"); // 只支持1.0
        paramMap.put("eshopType", "A"); // A: 特殊业务（易极付实现支付单生成，无支付信息的流程可设置）
        paramMap.put("customsCode", "4600"); // 申请海关代码 4600：郑州关区 5100：广州海关
        paramMap.put("eshopEntCode", "aa"); // TODO:电商平台的海关备案编号或代码
        paramMap.put("eshopEntName", "bb"); // TODO:电商平台的海关备案名称
        paramMap.put("payerDocType", "01"); // 支付人证件类型 01:居民身份证。
        paramMap.put("goodsCurrency", "142"); // 货款币种编号 142：人民币
        paramMap.put("taxCurrency", "142"); // 税款币种编号 142：人民币
        paramMap.put("freightCurrency", "142"); // 物流币种编号 142：人民币
        paramMap.put("ieType", "1"); // 郑州海关必填 1：进口 2：出口 默认值为1
        
        // paramMap.put("returnUrl", "http://www.i-coolshop.com");
        // paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifu");
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), KEY));
        String result = HttpUtils.httpsPost(YIJIFU_GATEWAY, paramMap);
        
        return JsonUtil.readValue(result, Map.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String> realNameQuery(Map<String, String> paramMap) {
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("partnerId", PARTNERID);
        paramMap.put("orderNo", Identities.uuid());
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), KEY));
        
        String result = HttpUtils.httpsPost(YIJIFU_GATEWAY, paramMap);
        
        return JsonUtil.readValue(result, Map.class);
    }
}

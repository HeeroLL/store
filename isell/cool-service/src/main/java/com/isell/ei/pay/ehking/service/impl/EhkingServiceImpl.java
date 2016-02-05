package com.isell.ei.pay.ehking.service.impl;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.ehking.bean.EhkingCustomsInfo;
import com.isell.ei.pay.ehking.bean.EhkingCustomsRequest;
import com.isell.ei.pay.ehking.bean.EhkingPayInfo;
import com.isell.ei.pay.ehking.bean.EhkingProductDetail;
import com.isell.ei.pay.ehking.service.EhkingService;
import com.isell.ei.pay.ehking.util.EhkingSignUtils;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.dao.CoolOrderYihuijinMapper;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderYihuijin;

/**
 * 易汇金支付service层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
@Service("ehkingService")
@SuppressWarnings("unchecked")
public class EhkingServiceImpl implements EhkingService {
    /**
     * 服务的域名
     */
    @Value("${service_domain}")
    private String serviceDomain;
    
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    @Resource
    private CoolOrderYihuijinMapper coolOrderYihuijinMapper;
    
    @Override
    public String getPayUrl(EhkingPayInfo ehkingPayInfo) {
        // test
        String order_no = ehkingPayInfo.getRequestId();
        String redirectUrl = null;
        CoolOrder coolOrder = coolOrderMapper.getCoolOrderByOrderNo(order_no);
        Integer id = coolOrder.getId();
        CoolOrderYihuijin cOrderYihuijin = coolOrderYihuijinMapper.getCoolOrderYihuijinByOrderNo(order_no);
        if (cOrderYihuijin == null) {
            ehkingPayInfo.setNotifyUrl(serviceDomain + "/payNotify/ehking"); // 异步通知url
            
            StringBuilder hmacSource = new StringBuilder();
            hmacSource.append(StringUtils.defaultString(ehkingPayInfo.getMerchantId()))
                .append((String)ObjectUtils.defaultIfNull(ehkingPayInfo.getOrderAmount(), ""))
                .append(StringUtils.defaultString(ehkingPayInfo.getOrderCurrency(), ""))
                .append(StringUtils.defaultString(ehkingPayInfo.getRequestId(), ""))
                .append(StringUtils.defaultString(ehkingPayInfo.getNotifyUrl(), ""))
                .append(StringUtils.defaultString(ehkingPayInfo.getCallbackUrl(), ""))
                .append(StringUtils.defaultString(ehkingPayInfo.getRemark(), ""))
                .append(StringUtils.defaultString(ehkingPayInfo.getPaymentModeCode(), ""));
            if (ehkingPayInfo.getProductDetails() != null) {
                EhkingProductDetail productDetail;
                for (Iterator<EhkingProductDetail> i$ = ehkingPayInfo.getProductDetails().iterator(); i$.hasNext(); hmacSource.append(StringUtils.defaultString(productDetail.getName()))
                    .append(ObjectUtils.defaultIfNull(productDetail.getQuantity(), ""))
                    .append(ObjectUtils.defaultIfNull(productDetail.getAmount(), ""))
                    .append(StringUtils.defaultString(productDetail.getReceiver()))
                    .append(StringUtils.defaultString(productDetail.getDescription())))
                    productDetail = i$.next();
                
            }
            if (ehkingPayInfo.getPayer() != null)
                hmacSource.append(StringUtils.defaultString(ehkingPayInfo.getPayer().getName()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getPayer().getPhoneNum()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getPayer().getIdNum()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getPayer().getBankCardNum()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getPayer().getEmail()));
            if (ehkingPayInfo.getBankCard() != null)
                hmacSource.append(StringUtils.defaultString(ehkingPayInfo.getBankCard().getName()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getBankCard().getCardNo()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getBankCard().getCvv2()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getBankCard().getIdNo()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getBankCard().getExpiryDate()))
                    .append(StringUtils.defaultString(ehkingPayInfo.getBankCard().getMobileNo()));
            hmacSource.append(StringUtils.defaultString(ehkingPayInfo.getCashierVersion(), ""));
            hmacSource.append(StringUtils.defaultString(ehkingPayInfo.getForUse(), ""));
            hmacSource.append(StringUtils.defaultString(ehkingPayInfo.getMerchantUserId(), ""));
            hmacSource.append(StringUtils.defaultString(ehkingPayInfo.getBindCardId(), ""));
            
            // 签名
            ehkingPayInfo.setHmac(EhkingSignUtils.signMd5(hmacSource.toString(), KEY));
            String json = JsonUtil.writeValueAsString(ehkingPayInfo);
            
            String result = HttpUtils.httpsPost(ORDER_URL, json, "application/vnd.ehking-v1.0+json");
            Map<String, String> resultMap = JsonUtil.readValue(result, Map.class);
            redirectUrl = resultMap.get("redirectUrl");
            CoolOrderYihuijin coolOrderYihuijin = new CoolOrderYihuijin();
            coolOrderYihuijin.setOrderId(id);
            coolOrderYihuijin.setOrderNo(order_no);
            coolOrderYihuijin.setUrl(redirectUrl);
            coolOrderYihuijinMapper.saveCoolOrderYihuijin(coolOrderYihuijin);
        } else {
            redirectUrl = cOrderYihuijin.getUrl();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<script>window.location.href='");
        builder.append(redirectUrl);
        builder.append("'</script>");
        
        return builder.toString();
    }
    
    @Override
    public String sendOrder(EhkingCustomsRequest request) {
        request.setNotifyUrl(serviceDomain + "/payNotify/ehkingBg"); // 异步通知url
        
        StringBuilder hmacSource = new StringBuilder();
        hmacSource.append(StringUtils.defaultString(request.getMerchantId()))
            .append(StringUtils.defaultString(request.getPaySerialNumber(), ""))
            .append(StringUtils.defaultString(request.getNotifyUrl(), ""));
        if (request.getPayer() != null)
            hmacSource.append(StringUtils.defaultString(request.getPayer().getPayerName()))
                .append(StringUtils.defaultString(request.getPayer().getIdNum()))
                .append(StringUtils.defaultString(request.getPayer().getPhoneNum()));
        if (request.getCustomsInfos() != null) {
            EhkingCustomsInfo customsInfo;
            for (Iterator<EhkingCustomsInfo> iterator = request.getCustomsInfos().iterator(); iterator.hasNext(); hmacSource.append(StringUtils.defaultString(customsInfo.getCustomsChannel()))
                .append(ObjectUtils.defaultIfNull(customsInfo.getAmount(), ""))
                .append(ObjectUtils.defaultIfNull(customsInfo.getFreight(), ""))
                .append(ObjectUtils.defaultIfNull(customsInfo.getGoodsAmount(), ""))
                .append(ObjectUtils.defaultIfNull(customsInfo.getTax(), ""))
                .append(StringUtils.defaultString(customsInfo.getMerchantCommerceName()))
                .append(StringUtils.defaultString(customsInfo.getMerchantCommerceCode())))
                customsInfo = iterator.next();
        }
        
        // 签名
        request.setHmac(EhkingSignUtils.signMd5(hmacSource.toString(), KEY));
        String json = JsonUtil.writeValueAsString(request);
        
        String result = HttpUtils.httpsPost(CUSTOMS_URL, json, "application/vnd.ehking-v1.0+json");
        Map<String, String> resultMap = JsonUtil.readValue(result, Map.class);
        return resultMap.get("status");
    }
    
}

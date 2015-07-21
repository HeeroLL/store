package com.sell.ei.logistics.ecm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sell.core.util.Coder;
import com.sell.core.util.DateUtil;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.logistics.ecm.service.EcmService;
import com.sell.ei.logistics.ecm.vo.EcmCommodities;
import com.sell.ei.logistics.ecm.vo.EcmCommodity;
import com.sell.ei.logistics.ecm.vo.EcmOrder;
import com.sell.ei.logistics.ecm.vo.EcmOrders;
import com.sell.ei.logistics.ecm.vo.EcmParam;
import com.sell.ei.logistics.ecm.vo.EcmResponse;
import com.sell.ei.logistics.ecm.vo.EcmResponseBody;

/**
 * 费舍尔ECM服务接口封装层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
@Service("ecmService")
public class EcmServiceImpl implements EcmService {
    
    @Override
    public EcmResponse pushSaleOrder(EcmOrders ecmOrders) {
        // 先推送商品
        EcmCommodities commodities = new EcmCommodities();
        commodities.setCommoditys(new ArrayList<EcmCommodity>());
        
        for (EcmOrder order : ecmOrders.getOrders()) {
            commodities.getCommoditys().addAll(order.getOrderDtls());
        }
        Map<String, String> paramMap = getParamMap(commodities);
        System.out.println(paramMap);
        String result = HttpUtils.httpPost(SENDCOMMODITY_URL, paramMap);
        System.out.println(result);
        EcmResponse response = JsonUtil.readValue(result, EcmResponse.class);
        if (response == null || response.getRowset() == null || !"1000".equals(response.getRowset().getResultCode())) {
            return response;
        }
        
        // 再推送订单
        paramMap = getParamMap(ecmOrders);
        System.out.println(result);
        result = HttpUtils.httpPost(PUSHSALEORDER_URL, paramMap);
        System.out.println(result);
        return JsonUtil.readValue(result, EcmResponse.class);
    }
    
    @Override
    public EcmResponse sendOrderStatus(EcmParam param) {
        EcmResponse response = new EcmResponse();
        EcmResponseBody rowset = new EcmResponseBody();
        if (validateSign(param)) {
            rowset.setResultCode("1000");
            rowset.setResultMsg("接收成功");
        } else {
            rowset.setResultCode("1001");
            rowset.setResultMsg("验证失败");
        }
        
        response.setRowset(rowset);
        return response;
    }
    
    @Override
    public EcmResponse sendShipOrder(EcmParam param) {
        EcmResponse response = new EcmResponse();
        EcmResponseBody rowset = new EcmResponseBody();
        if (validateSign(param)) {
            rowset.setResultCode("1000");
            rowset.setResultMsg("接收成功");
        } else {
            rowset.setResultCode("1001");
            rowset.setResultMsg("验证失败");
        }
        
        response.setRowset(rowset);
        return response;
    }
    
    /**
     * 通用参数封装
     * 
     * @param jsonObj jsonObj
     * @return 参数Map
     */
    private Map<String, String> getParamMap(Object jsonObj) {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        String datetime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
        
        paramMap.put("ip", IP); // bis的外网ip
        paramMap.put("v", V); // 接口版本号
        // paramMap.put("appKey", APP_KEY); // ECM给的key
        paramMap.put("sessionKey", SESSION_KEY);
        paramMap.put("datetime", datetime);
        
        paramMap.put("sign", getSign(V + IP + SESSION_KEY + datetime + APP_KEY)); // 校验码
        paramMap.put("JSON_OBJ", Coder.encryptBASE64(JsonUtil.writeValueAsString(jsonObj))); // BASE64编码后的jsonObj
        
        return paramMap;
    }
    
    private boolean validateSign(EcmParam param) {
        String sign = param.getV() + param.getIp() + param.getSessionKey() + param.getDatetime() + APP_KEY;
        return getSign(sign).equals(param.getSign());
    }
    
    private String getSign(String sign) {
        return Coder.encodeMd5(sign).toUpperCase();
    }
}

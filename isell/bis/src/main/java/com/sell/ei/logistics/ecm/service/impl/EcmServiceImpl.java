package com.sell.ei.logistics.ecm.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sell.bis.auth.BisValidator;
import com.sell.bis.auth.bean.RequestParameter;
import com.sell.bis.config.BisConfig;
import com.sell.bis.config.dao.RequestAccsysRefMapper;
import com.sell.bis.config.vo.RequestAccsysRef;
import com.sell.core.util.Coder;
import com.sell.core.util.DateUtil;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.logistics.ecm.service.EcmService;
import com.sell.ei.logistics.ecm.vo.EcmCommodities;
import com.sell.ei.logistics.ecm.vo.EcmOrder;
import com.sell.ei.logistics.ecm.vo.EcmOrderStatusListInfo;
import com.sell.ei.logistics.ecm.vo.EcmOrderStatuses;
import com.sell.ei.logistics.ecm.vo.EcmOrders;
import com.sell.ei.logistics.ecm.vo.EcmParam;
import com.sell.ei.logistics.ecm.vo.EcmResponse;
import com.sell.ei.logistics.ecm.vo.EcmResponseBody;
import com.sell.ei.logistics.ecm.vo.EcmShipOrder;
import com.sell.ei.logistics.ecm.vo.EcmShipOrders;

/**
 * 费舍尔ECM服务接口封装层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
@Service("ecmService")
public class EcmServiceImpl implements EcmService {
    /**
     * 验证器
     */
    @Qualifier(value = "basicValidator")
    @Resource
    private BisValidator validator;
    
    /**
     * 请求与接入系统关系Mapper
     */
    @Resource
    private RequestAccsysRefMapper requestAccsysRefMapper;
    
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    @Override
    public EcmResponse sendCommodity(EcmCommodities param) {
        // 推送商品
        Map<String, String> paramMap = getParamMap(param);
        String result = HttpUtils.httpPost(SENDCOMMODITY_URL, paramMap);
        EcmResponse response = JsonUtil.readValue(result, EcmResponse.class);
        return response;
    }
    
    @Override
    public EcmResponse pushSaleOrder(RequestParameter param) {
        // 先验证参数合法性
        validator.validate(param);
        
        EcmOrders ecmOrders = JsonUtil.readValue(param.getJsonObj(), EcmOrders.class);
        // 请求与接入信息关系
        RequestAccsysRef[] requestAccsysRefs = new RequestAccsysRef[ecmOrders.getOrders().size()];
        int index = 0;
        for (EcmOrder order : ecmOrders.getOrders()) {
            requestAccsysRefs[index++] = new RequestAccsysRef(order.getOrderCode(), param.getAccessCode());
        }
        
        // 推送订单
        Map<String, String> paramMap = getParamMap(ecmOrders);
        String result = HttpUtils.httpPost(PUSHSALEORDER_URL, paramMap);
        EcmResponse response = JsonUtil.readValue(result, EcmResponse.class);
        if (response == null || response.getRowset() == null || !"1000".equals(response.getRowset().getResultCode())) {
            return response;
        }
        // 将订单号和接入系统编码关系存入数据库
        requestAccsysRefMapper.saveRequestAccsysRef(requestAccsysRefs);
        
        return response;
    }
    
    @Override
    public EcmResponse sendOrderStatus(EcmParam param) {
        EcmResponse response = getResponse(param);
        
        // 验证通过才推送
        if ("1000".equals(response.getRowset().getResultCode())) {
            String paramJson = getJsonObj(param.getJsonObj());
            EcmOrderStatuses ecmOrderStatuses = JsonUtil.readValue(paramJson, EcmOrderStatuses.class);
            for (EcmOrderStatusListInfo ecmOrderStatusListInfo : ecmOrderStatuses.getStatusList()) {
                RequestAccsysRef requestAccsysRef =
                    requestAccsysRefMapper.getRequestAccsysRefByRequestId(ecmOrderStatusListInfo.getOrderCode());
                if (requestAccsysRef == null) {
                    continue;
                }
                String accessCode = requestAccsysRef.getAccessCode();
                String notifyUrl = config.getSysMappingMap().get(accessCode + "sendOrderStatus");
                
                if (StringUtils.isNotEmpty(notifyUrl)) {
                    EcmOrderStatuses request = new EcmOrderStatuses();
                    request.setStatusList(new ArrayList<EcmOrderStatusListInfo>());
                    request.getStatusList().add(ecmOrderStatusListInfo);
                    
                    HttpUtils.httpPost(notifyUrl, JsonUtil.writeValueAsString(request));
                }
            }
        }
        
        return response;
    }
    
    @Override
    public EcmResponse sendShipOrder(EcmParam param) {
        EcmResponse response = getResponse(param);
        
        // 验证通过才推送
        if ("1000".equals(response.getRowset().getResultCode())) {
            String paramJson = getJsonObj(param.getJsonObj());
            EcmShipOrders ecmShipOrders = JsonUtil.readValue(paramJson, EcmShipOrders.class);
            for (EcmShipOrder ecmShipOrder : ecmShipOrders.getShipporders()) {
                RequestAccsysRef requestAccsysRef =
                    requestAccsysRefMapper.getRequestAccsysRefByRequestId(ecmShipOrder.getOrderCode());
                if (requestAccsysRef == null) {
                    continue;
                }
                // 根据接入编码和业务编码获取通知URL
                String accessCode = requestAccsysRef.getAccessCode();
                String notifyUrl = config.getSysMappingMap().get(accessCode + "sendShipOrder");
                
                if (StringUtils.isNotEmpty(notifyUrl)) {
                    EcmShipOrders request = new EcmShipOrders();
                    request.setShipporders(new ArrayList<EcmShipOrder>());
                    request.getShipporders().add(ecmShipOrder);
                    
                    String result = HttpUtils.httpPost(notifyUrl, JsonUtil.writeValueAsString(request));
                    EcmResponse res = JsonUtil.readValue(result, EcmResponse.class);
                    if (!"1000".equals(res.getRowset().getResultCode())) {
                        response = res;
                    }
                }
            }
        }
        
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
        paramMap.put("sessionKey", SESSION_KEY);
        paramMap.put("datetime", datetime);
        
        paramMap.put("sign", getSign(V + IP + SESSION_KEY + datetime + APP_KEY)); // 校验码
        paramMap.put("JSON_OBJ", Coder.encryptBASE64(JsonUtil.writeValueAsString(jsonObj))); // BASE64编码后的jsonObj
        
        return paramMap;
    }
    
    private String getJsonObj(String jsonObj) {
        // 参数解密
        try {
            return new String(Coder.decryptBASE64(jsonObj), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    private EcmResponse getResponse(EcmParam param) {
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
    
    private boolean validateSign(EcmParam param) {
        String sign = param.getV() + param.getIp() + param.getSessionKey() + param.getDatetime() + APP_KEY;
        return getSign(sign).equals(param.getSign());
    }
    
    private String getSign(String sign) {
        return Coder.encodeMd5(sign).toUpperCase();
    }
}

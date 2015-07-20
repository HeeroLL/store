package com.sell.ei.logistics.ecm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sell.core.util.Coder;
import com.sell.core.util.DateUtil;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.logistics.ecm.service.EcmService;
import com.sell.ei.logistics.ecm.vo.EcmCommodities;
import com.sell.ei.logistics.ecm.vo.EcmResponse;

/**
 * 费舍尔ECM服务接口封装层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
@Service("ecmService")
public class EcmServiceImpl implements EcmService {
    
    @Override
    public EcmResponse sendCommodity(EcmCommodities commodities) {
        Map<String, String> paramMap = getParamMap(commodities);
        System.out.println(paramMap);
        
        // 还要添加HTTP BASIC验证
        String result = HttpUtils.httpPost(SENDCOMMODITY_URL, paramMap);
        System.out.println(result);
        
        return JsonUtil.readValue(result, EcmResponse.class);
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
        paramMap.put("appKey", APP_KEY); // ECM给的key
        paramMap.put("sessionKey", SESSION_KEY);
        paramMap.put("datetime", datetime);
        
        paramMap.put("sign", Coder.encodeMd5(V + IP + SESSION_KEY + datetime + APP_KEY).toUpperCase()); // 校验码
        paramMap.put("JSON_OBJ", Coder.encryptBASE64(JsonUtil.writeValueAsString(jsonObj))); // BASE64编码后的jsonObj
        
        return paramMap;
    }
    
}

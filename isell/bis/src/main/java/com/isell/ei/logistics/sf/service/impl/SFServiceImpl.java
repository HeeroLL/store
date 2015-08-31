package com.isell.ei.logistics.sf.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.ei.logistics.sf.service.SFService;
import com.isell.ei.logistics.sf.vo.SBody;
import com.isell.ei.logistics.sf.vo.SOrder;
import com.isell.ei.logistics.sf.vo.SOrderSearch;
import com.isell.ei.logistics.sf.vo.SRequest;
import com.isell.ei.logistics.sf.vo.SResponse;
import com.isell.ei.logistics.sf.vo.SRouteRequest;

/**
 * 
 * 顺丰物流接口封装业务层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月4日]
 */
@Service("sfService")
public class SFServiceImpl implements SFService {
    @Override
    public SResponse orderService(SOrder order) {
        SRequest request = new SRequest();
        request.setService("OrderService");
        request.setHead(HEAD);
        SBody body = new SBody();
        body.setOrder(order);
        request.setBody(body);
        
        return sendHttpsPost(request);
    }
    
    @Override
    public SResponse routeService(SRouteRequest routeRequest) {
        SRequest request = new SRequest();
        request.setService("RouteService");
        request.setHead(HEAD);
        SBody body = new SBody();
        body.setRouteRequest(routeRequest);
        request.setBody(body);
        
        return sendHttpsPost(request);
    }
    
    @Override
    public SResponse orderSearchService(SOrderSearch orderSearch) {
        SRequest request = new SRequest();
        request.setService("OrderSearchService");
        request.setHead(HEAD);
        SBody body = new SBody();
        body.setOrderSearch(orderSearch);
        request.setBody(body);
        
        return sendHttpsPost(request);
    }
    
    /**
     * 根据请求的xml和顺丰密钥生成校验码
     * 
     * @param xml xml
     * @return 校验码
     */
    private String getVerifyCode(String xml) {
        return new String(Coder.encryptBASE64(Coder.encryptMD5(xml + CHECKWORD)));
    }
    
    /**
     * 组装请求消息体并发送
     * 
     * @param request 请求消息体参数
     * @return 顺丰响应信息
     */
    private SResponse sendHttpsPost(SRequest request) {
        String xml = JaxbUtil.convertToXml(request);
        String verifyCode = getVerifyCode(xml);
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", verifyCode);
        paramMap.put("xml", xml);
        
        String result = HttpUtils.httpsPost(URL, paramMap);
        return JaxbUtil.converyToJavaBean(result, SResponse.class);
    }
}

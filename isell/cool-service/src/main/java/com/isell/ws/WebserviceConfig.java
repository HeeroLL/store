package com.isell.ws;

import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.isell.ws.zhengzhou.service.impl.ZzWebServiceImpl;

/**
 * webservice初始化
 * 
 * @author lilin
 * @version [版本号, 2015年10月23日]
 */
@Component
public class WebserviceConfig {
    
    /**
     * webservice地址
     */
    @Value("${ws_domain}")
    private String wsDomain;
    
    /**
     * 初始化
     */
    // @PostConstruct
    public void init() {
        System.out.println("begin ws init.");
        Endpoint.publish(wsDomain + "/ws/zzWebService", new ZzWebServiceImpl());
        System.out.println("end ws init.");
    }
}

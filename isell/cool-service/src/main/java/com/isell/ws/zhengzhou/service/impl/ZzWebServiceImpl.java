package com.isell.ws.zhengzhou.service.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.isell.ws.zhengzhou.service.ZzWebService;

/**
 * 订单回执webservice
 * 
 * @author lilin
 * @version [版本号, 2015年10月23日]
 */
@WebService(endpointInterface = "com.isell.ws.zhengzhou.service.ZzWebService")  
public class ZzWebServiceImpl implements ZzWebService {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(ZzWebServiceImpl.class);
    
    @Override
    public String orderBack(String xml) {
        log.info("郑州海关进出口订单回执：\n" + xml);
        return "SUCCESS";
    }
}

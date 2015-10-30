package com.isell.ws.zhengzhou.service.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import com.isell.ws.zhengzhou.service.ZzWebService;

public class ZzWebServiceImplTest {
    
    @Test
    public void testOrderBack() throws MalformedURLException {
        // http://121.41.113.175:8083/ws/zzWebService?wsdl
        URL url = new URL("http://121.41.113.175:8083/ws/zzWebService?wsdl");  
        // 第一个参数是服务的URI  
        // 第二个参数是在WSDL发布的服务名  
        QName qname = new QName("http://impl.service.zhengzhou.ws.isell.com/","ZzWebServiceImplService");  
        // 创建服务  
        Service service = Service.create(url, qname);  
        // 提取端点接口，服务“端口”。  
        ZzWebService zzWebService = service.getPort(ZzWebService.class);
        System.out.println(zzWebService.orderBack("This is just a test."));
    }
    
}

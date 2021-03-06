package com.isell.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.demo.Address;
import com.isell.demo.Person;
import com.thoughtworks.xstream.XStream;

public class HttpUtilsTest {
    
    @Test
    public void testInstace() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", "gBWGwSga2cpavGJYg85e7Q==");
        paramMap.put("xml",
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Request lang=\"zh-CN\" service=\"RouteService\"><Head>BSPdevelop</Head><Body><RouteRequest method_type=\"1\" tracking_number=\"444029276937\" tracking_type=\"1\"/></Body></Request>");
        
        HttpUtils http = HttpUtils.getInstance(HttpUtils.HTTP_CONNECTION_TYPE_HTTPS);
        System.err.println(http.sendPost("https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService", paramMap));
        System.err.println(http.sendGet("https://www.baidu.com"));
        
        http.closeConn();
    }
    
    @Test
    public void testStatic() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", "gBWGwSga2cpavGJYg85e7Q==");
        paramMap.put("xml",
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Request lang=\"zh-CN\" service=\"RouteService\"><Head>BSPdevelop</Head><Body><RouteRequest method_type=\"1\" tracking_number=\"444029276937\" tracking_type=\"1\"/></Body></Request>");
        
        System.err.println(HttpUtils.httpsPost("https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService",
            paramMap));
        System.err.println(HttpUtils.httpsGet("https://www.baidu.com"));
    }
    
    @Test
    public void testJsonMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aa", 1);
        map.put("bb", "2");
        
        System.out.println(HttpUtils.httpPost("http://localhost:18080/bis/demo", JsonUtil.writeValueAsString(map)));
    }
    
    @Test
    public void testJsonObject() {
        Person person = new Person();
        person.setName("requestName");
        person.setPhoneNuber(123);
        person.setAddresses(new ArrayList<Address>());
        
        Address address = new Address();
        address.setHouseNo(1);
        address.setStreet("街道1");
        person.getAddresses().add(address);
        address = new Address();
        address.setHouseNo(2);
        address.setStreet("街道2");
        person.getAddresses().add(address);
        address = new Address();
        address.setHouseNo(3);
        address.setStreet("街道3");
        person.getAddresses().add(address);
        
        System.out.println(HttpUtils.httpPost("http://localhost:18080/bis/demo2", JsonUtil.writeValueAsString(person)));
    }
    
    @Test
    public void testXmlObject() {
        XStream xStream = new XStream();
        // 设置XStream支持注解
        xStream.autodetectAnnotations(true);
        
        Person person = new Person();
        person.setName("requestName");
        person.setPhoneNuber(123);
        person.setAddresses(new ArrayList<Address>());
        
        Address address = new Address();
        address.setHouseNo(1);
        address.setStreet("街道1");
        person.getAddresses().add(address);
        address = new Address();
        address.setHouseNo(2);
        address.setStreet("街道2");
        person.getAddresses().add(address);
        address = new Address();
        address.setHouseNo(3);
        address.setStreet("街道3");
        person.getAddresses().add(address);
        
        String xml = xStream.toXML(person);
        System.out.println("requestXml=\n" + xml);
        System.out.println("responseXml=\n"
            + HttpUtils.httpPost("http://localhost:18080/bis/demo2", xml, "application/xml"));
    }
}

package com.sell.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sell.core.util.JsonUtil;
import com.sell.demo.Address;
import com.sell.demo.Person;
import com.sell.ei.logistics.sf.vo.SResponse;
import com.thoughtworks.xstream.XStream;

public class HttpUtilsTest {
    
    // @Test
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
    
    // @Test
    public void testStatic() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", "gBWGwSga2cpavGJYg85e7Q==");
        paramMap.put("xml",
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Request lang=\"zh-CN\" service=\"RouteService\"><Head>BSPdevelop</Head><Body><RouteRequest method_type=\"1\" tracking_number=\"444029276937\" tracking_type=\"1\"/></Body></Request>");
        
        System.err.println(HttpUtils.httpsPost("https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService",
            paramMap));
        System.err.println(HttpUtils.httpsGet("https://www.baidu.com"));
    }
    
    // @Test
    public void testJsonMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aa", 1);
        map.put("bb", "2");
        
        System.out.println(HttpUtils.httpPost("http://localhost:8080/bis/demo", JsonUtil.writeValueAsString(map)));
    }
    
    // @Test
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
        
        System.out.println(HttpUtils.httpPost("http://localhost:8080/bis/demo2", JsonUtil.writeValueAsString(person)));
    }
    
    // @Test
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
            + HttpUtils.httpPost("http://localhost:8080/bis/demo2", xml, "application/xml"));
    }
    
    @Test
    public void textOrderService() {
        String json =
            "{\"orderid\":\"11255\",\"mailno\":\"\",\"is_gen_bill_no\":1,\"j_company\":\"韩国\",\"j_contact\":\"1111\",\"j_tel\":\"1111\",\"j_mobile\":null,\"j_shippercode\":\"100\",\"j_country\":\"\",\"j_province\":null,\"j_city\":null,\"j_county\":null,\"j_address\":\"11111\",\"j_post_code\":\"213000\",\"d_company\":\"\",\"d_contact\":\"张三\",\"d_tel\":\"13512345678\",\"d_mobile\":null,\"d_deliverycode\":\"CN\",\"d_country\":\"中国\",\"d_province\":\"江苏省\",\"d_city\":\"常州市\",\"d_county\":\"天宁区\",\"d_address\":\"aaaaaaaaa\",\"d_post_code\":\"213000\",\"custid\":null,\"pay_method\":1,\"express_type\":\"19\",\"parcel_quantity\":0,\"cargo_length\":null,\"cargo_width\":null,\"cargo_height\":null,\"volume\":null,\"cargo_total_weight\":\"\",\"declared_value\":\"0.01\",\"declared_value_currency\":\"CNY\",\"customs_batchs\":null,\"sendstarttime\":null,\"is_docall\":null,\"need_return_tracking_no\":null,\"return_tracking\":null,\"d_tax_no\":null,\"tax_pay_type\":null,\"tax_set_accounts\":null,\"original_number\":\"CO201506301414276880\",\"payment_tool\":\"连连支付\",\"payment_number\":null,\"goods_code\":\"\",\"in_process_waybill_no\":null,\"brand\":\"\",\"specifications\":\"\",\"temp_range\":null,\"order_name\":\"李霖\",\"order_cert_type\":\"01\",\"order_cert_no\":\"320402198408273738\",\"order_source\":null,\"template\":null,\"remark\":null,\"cargo\":[{\"name\":\"【艾易售】九朵云 淡斑滋润乳液 改善肤色淡斑面霜 韩国直邮\",\"count\":1,\"unit\":\"个\",\"weight\":\"\",\"amount\":\"0.01\",\"currency\":\"CNY\",\"source_area\":\"133\",\"product_record_no\":null,\"good_prepard_no\":null}]}";
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/sf/orderService", json);
        System.out.println("result=" + result);
    }
    
    // @Test
    public void textOrderServiceResponse() {
        String xml2 =
            "<?xml version='1.0' encoding='UTF-8'?><Response service=\"OrderService\"><Head>ERR</Head><ERROR code=\"8014\">校验码错误</ERROR></Response>";
        SResponse sresponse = JaxbUtil.converyToJavaBean(xml2, SResponse.class);
        System.out.println(sresponse);
    }
}

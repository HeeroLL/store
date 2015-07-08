package com.sell.ei.logistics.sf.controller;

import org.junit.Test;

import com.sell.core.util.HttpUtils;
import com.sell.core.util.JsonUtil;
import com.sell.ei.logistics.sf.vo.SOrderSearch;
import com.sell.ei.logistics.sf.vo.SRouteRequest;

/**
 * 
 * 顺丰接口测试类
 * 
 * @author lilin
 * @version [版本号, 2015年7月5日]
 */
public class SFLogisticsControllerTest {
    
    /**
     * 测试顺丰下订单接口
     */
    @Test
    public void testOrderService() {
        String json =
            "{\"orderid\":\"11255\",\"mailno\":\"\",\"is_gen_bill_no\":1,\"j_company\":\"韩国\",\"j_contact\":\"1111\",\"j_tel\":\"1111\",\"j_mobile\":null,\"j_shippercode\":\"100\",\"j_country\":\"\",\"j_province\":null,\"j_city\":null,\"j_county\":null,\"j_address\":\"11111\",\"j_post_code\":\"213000\",\"d_company\":\"\",\"d_contact\":\"张三\",\"d_tel\":\"13512345678\",\"d_mobile\":null,\"d_deliverycode\":\"CN\",\"d_country\":\"中国\",\"d_province\":\"江苏省\",\"d_city\":\"常州市\",\"d_county\":\"天宁区\",\"d_address\":\"aaaaaaaaa\",\"d_post_code\":\"213000\",\"custid\":null,\"pay_method\":1,\"express_type\":\"19\",\"parcel_quantity\":0,\"cargo_length\":null,\"cargo_width\":null,\"cargo_height\":null,\"volume\":null,\"cargo_total_weight\":\"\",\"declared_value\":\"0.01\",\"declared_value_currency\":\"CNY\",\"customs_batchs\":null,\"sendstarttime\":null,\"is_docall\":null,\"need_return_tracking_no\":null,\"return_tracking\":null,\"d_tax_no\":null,\"tax_pay_type\":null,\"tax_set_accounts\":null,\"original_number\":\"CO201506301414276880\",\"payment_tool\":\"连连支付\",\"payment_number\":null,\"goods_code\":\"\",\"in_process_waybill_no\":null,\"brand\":\"\",\"specifications\":\"\",\"temp_range\":null,\"order_name\":\"李霖\",\"order_cert_type\":\"01\",\"order_cert_no\":\"320402198408273738\",\"order_source\":null,\"template\":null,\"remark\":null,\"cargo\":[{\"name\":\"【艾易售】九朵云 淡斑滋润乳液 改善肤色淡斑面霜 韩国直邮\",\"count\":1,\"unit\":\"个\",\"weight\":\"\",\"amount\":\"0.01\",\"currency\":\"CNY\",\"source_area\":\"133\",\"product_record_no\":null,\"good_prepard_no\":null}]}";
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/sf/orderService", json);
        System.out.println("result=" + result);
    }
    
    /**
     * 测试顺丰查询订单接口
     */
    @Test
    public void testRouteService() {
        SRouteRequest routeRequest = new SRouteRequest();
        routeRequest.setTrackingNumber("444029276937"); //如果是多个用,分开
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/sf/routeService", JsonUtil.writeValueAsString(routeRequest));
        System.out.println("result=" + result);
    }
    
    /**
     * 测试顺丰订单结果查询接口
     */
    @Test
    public void testOrderSearchService() {
        SOrderSearch orderSearch = new SOrderSearch();
        orderSearch.setOrderid("140");
        String result = HttpUtils.httpPost("http://localhost:8080/bis/logistics/sf/orderSearchService", JsonUtil.writeValueAsString(orderSearch));
        System.out.println("result=" + result);
    }
}

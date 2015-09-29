package com.isell.ei.pay.lianlian.controller;


import org.junit.Test;

import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.lianlian.bean.LianlianPayInfo;

public class LianlianPayControllerTest {
    
    @Test
    public void testWapPay() {
        String request = "{\"merchant_userid\":\"23\",\"url_notify\":\"http://www.xiaocoon.com/common/payNotify/302\",\"bill_validdate\":\"20150901125828\",\"url_return\":\"http://www.xiaocoon.com/user/checksuccess/302\",\"email\":\"13961295157\",\"phone\":\"13961295157\",\"shipping_address\":\"江苏省常州市新北区太湖东路9号A座1801室（常州林海信息技术有限公司）\",\"goods_type\":\"4999\",\"merchant_orderno\":\"CO201508311258284476\",\"merchant_trans_date\":\"20150831125828\",\"trans_amt\":\"119000\",\"url_order\":\"\",\"info_order\":\"\",\"goods_no\":\"\",\"goods_name\":\"Guerisson奇迹马油祛疤霜 70g\",\"remark\":\"\",\"risk_request_json\":\"{\"frms_ware_category\":\"4999\",\"user_info_mercht_userno\":\"23\",\"user_info_bind_phone\":\"13961295157\",\"user_info_dt_register\":\"2015/06/30\",\"delivery_addr_full\":\"江苏省常州市新北区太湖东路9号A座1801室（常州林海信息技术有限公司）\",\"delivery_full_namet\":\"王聚金\",\"delivery_phone\":\"18068567725\",\"logistics_mode\":\"5\",\"delivery_cycle\":\"48h\"}\",\"shareing_data\":\"\",\"declare_notify\":\"http://www.xiaocoon.com/common/declareNotify/302\"}";
        LianlianPayInfo lianlianPayInfo = JsonUtil.readValue(request, LianlianPayInfo.class);
        System.out.println(lianlianPayInfo);
    }
    
    @Test
    public void testWebPay() {
       
    }
    
    @Test
    public void testRefund() {
        
    }
    
}

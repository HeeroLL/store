package com.isell.ei.pay.ehking.controller;

import java.util.ArrayList;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.ehking.bean.EhkingCustomsInfo;
import com.isell.ei.pay.ehking.bean.EhkingCustomsPayer;
import com.isell.ei.pay.ehking.bean.EhkingCustomsRequest;
import com.isell.ei.pay.ehking.bean.EhkingPayInfo;
import com.isell.ei.pay.ehking.bean.EhkingProductDetail;

public class EhkingControllerTest {
    
    @Test
    public void testWebPay() {
        EhkingPayInfo ehkingPayInfo = new EhkingPayInfo();
        ehkingPayInfo.setOrderAmount("7900"); // 订单金额 单位分
        ehkingPayInfo.setRequestId("CO201601271357225735932"); // 订单号
        ehkingPayInfo.setCallbackUrl("http://www.baidu.com"); // 服务器通知： 支付成功后会向该地址发送两次成功通知，该地址可以带参数
        EhkingProductDetail detail = new EhkingProductDetail();
        detail.setName("九朵云");  //商品名称
        detail.setQuantity("1"); // 商品数量
        detail.setAmount("7900"); // 商品金额 单位分
        ehkingPayInfo.getProductDetails().add(detail);
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/pay/ehking/webPay",
                JsonUtil.writeValueAsString(ehkingPayInfo));
        System.out.println(result);
    }
    
    @Test
    public void testSendOrder() {
        EhkingCustomsRequest request = new EhkingCustomsRequest();
        request.setPaySerialNumber("64efe17d48b6476194db5471cc0beec1");
        request.setCustomsInfos(new ArrayList<EhkingCustomsInfo>());
        EhkingCustomsInfo info = new EhkingCustomsInfo();
        info.setAmount(1l);
        info.setFreight(0l);
        info.setGoodsAmount(1l);
        
        request.getCustomsInfos().add(info);
        request.setPayer(new EhkingCustomsPayer());
        request.getPayer().setPayerName("王伟华");
        request.getPayer().setIdNum("445121199410022056");
        request.getPayer().setPhoneNum("15895035176");
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/pay/ehking/sendOrder",
                JsonUtil.writeValueAsString(request));
        System.out.println(result);
    }
}

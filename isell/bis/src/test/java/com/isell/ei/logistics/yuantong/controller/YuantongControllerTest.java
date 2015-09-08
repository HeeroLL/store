package com.isell.ei.logistics.yuantong.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.yuantong.bean.AddressInfo;
import com.isell.ei.logistics.yuantong.bean.ItemInfo;
import com.isell.ei.logistics.yuantong.bean.OrderRequest;

public class YuantongControllerTest {
    
    @Test
    public void testPlaceOrder() {
        OrderRequest request = new OrderRequest();
        request.setTxLogisticID("CO2015090711111111");
        AddressInfo sender = new AddressInfo();
        sender.setName("张三");
        sender.setPostCode(213000);
        sender.setMobile("13512345678");
        sender.setProv("江苏省");
        sender.setCity("常州市,新北区");
        sender.setAddress("软件园1801");
        
        AddressInfo receiver = new AddressInfo();
        receiver.setName("李四");
        receiver.setPostCode(213000);
        receiver.setMobile("13587654321");
        receiver.setProv("江苏省");
        receiver.setCity("常州市,武进区");
        receiver.setAddress("吾悦广场A-1080");
        
        request.setSender(sender);
        request.setReceiver(receiver);
        
        List<ItemInfo> list = new ArrayList<ItemInfo>();
        ItemInfo item1 = new ItemInfo();
        item1.setItemName("马油");
        item1.setNumber(1);
        item1.setItemValue(99.01);
        
        ItemInfo item2 = new ItemInfo();
        item2.setItemName("九朵云");
        item2.setNumber(2);
        item2.setItemValue(99.11);
        list.add(item1);
        list.add(item2);
        
        request.setItems(list);
        
        request.setGoodsValue(99.11 * 2 + 99.01);
        
       // String requestStr = "<RequestOrder><logisticProviderID>YTO</logisticProviderID><txLogisticID>LP07082300225709</txLogisticID><tradeNo>2007082300225709</tradeNo><totalServiceFee>32.0</totalServiceFee><codSplitFee>200</codSplitFee><type>0</type><orderType>1</orderType><serviceType>0</serviceType><flag>1</flag><sender><name>张三</name><postCode>310013</postCode><phone>231234134</phone><mobile>13575745195</mobile><prov>浙江</prov><city>上海,浦东新区</city><address>新龙科技大厦9层</address></sender><receiver><name>李四</name><postCode>100000</postCode><phone>231234134</phone><mobile>13112345678</mobile><prov>北京</prov><city>北京市,朝阳区</city><address>新龙科技大厦9层</address></receiver><sendStartTime>2005-08-24 08:00:00</sendStartTime><sendEndTime>2005-08-24 12:00:00</sendEndTime><goodsValue>1900</goodsValue><itemsValue>2000</itemsValue><agencyFund>2000</agencyFund><items><item><itemName>Nokia N73</itemName><number>2</number><itemValue>2</itemValue></item><item><itemName>Nokia N72</itemName><number>1</number><itemValue>2</itemValue></item></items><insuranceValue>0.0</insuranceValue><special>0</special><remark>易碎品</remark></RequestOrder>";        
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/logistics/yuantong/placeOrder", //JsonUtil.writeValueAsString(JaxbUtil.converyToJavaBean(requestStr, OrderRequest.class)));
                JsonUtil.writeValueAsString(request));
        System.out.println(result);
    }
}

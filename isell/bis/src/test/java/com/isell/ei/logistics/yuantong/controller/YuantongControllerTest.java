package com.isell.ei.logistics.yuantong.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
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
        
        String requestStr = "<RequestOrder><clientID>K24000154</clientID><logisticProviderID>YTO</logisticProviderID><customerId>K24000154</customerId><txLogisticID>LP81132426447</txLogisticID><tradeNo>1</tradeNo><totalServiceFee>1</totalServiceFee><codSplitFee>1</codSplitFee><orderType>1</orderType><serviceType>1</serviceType><flag>1</flag><sendStartTime>2014-03-06 12:12:12</sendStartTime><sendEndTime>2014-03-06 12:12:12</sendEndTime><goodsValue>1</goodsValue><itemsValue>1</itemsValue><insuranceValue>1</insuranceValue><special>1</special><remark>1</remark><deliverNo>1</deliverNo><type>1</type><totalValue>1</totalValue><itemsWeight>1</itemsWeight><packageOrNot>1</packageOrNot><orderSource>1</orderSource><sender><name>汪明新</name><postCode>123456</postCode><phone>1234567</phone><mobile>18221885929</mobile><prov>上海</prov><city>上海,青浦区</city><address>上海市青浦区华徐公路民兴大道</address></sender><receiver><name>汪明新</name><postCode>123456</postCode><phone>1234567</phone><mobile>18221885929</mobile><prov>浙江省</prov><city>金华市,浦江县</city><address>赤松路308号</address></receiver><items><item><itemName>windows pc</itemName><number>2</number><itemValue>50</itemValue></item><item><itemName>windows pc</itemName><number>2</number><itemValue>50</itemValue></item></items></RequestOrder>";        
        
        String result =
            HttpUtils.httpPost("http://localhost:8080/bis/logistics/yuantong/placeOrder", JsonUtil.writeValueAsString(JaxbUtil.converyToJavaBean(requestStr, OrderRequest.class)));
               // JsonUtil.writeValueAsString(request));
        System.out.println(result);
    }
}

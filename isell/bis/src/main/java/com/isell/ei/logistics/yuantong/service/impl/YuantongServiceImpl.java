package com.isell.ei.logistics.yuantong.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.ei.logistics.yuantong.bean.OrderRequest;
import com.isell.ei.logistics.yuantong.bean.OrderResponse;
import com.isell.ei.logistics.yuantong.service.YuantongService;

/**
 * 圆通业务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月7日]
 */
@Service("yuantongService")
public class YuantongServiceImpl implements YuantongService {
    
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Map<String, String> paramMap = new HashMap<String, String>();
        String xml = JaxbUtil.convertToXml(orderRequest);
        xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");

        paramMap.put("logistics_interface", xml);
        paramMap.put("clientId", CLIENT_ID);
        paramMap.put("data_digest", getDataDigest(xml));
        
        String result = HttpUtils.httpPost(PLACEORDER_URL, paramMap);
        
        return JaxbUtil.converyToJavaBean(result, OrderResponse.class);
    }
    
    private String getDataDigest(String xml) {
        return Coder.encryptBASE64(Coder.encryptMD5(xml + PARTNER_ID));
    }
}

package com.sell.ei.logistics.sf.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sell.ei.logistics.sf.service.SFService;
import com.sell.ei.logistics.sf.vo.SBody;
import com.sell.ei.logistics.sf.vo.SOrder;
import com.sell.ei.logistics.sf.vo.SRequest;
import com.sell.ei.logistics.sf.vo.SResponse;
import com.sell.util.Coder;
import com.sell.util.HttpUtils;
import com.sell.util.JaxbUtil;

/**
 * 
 * 顺丰物流接口封装业务层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月4日]
 */
@Service("sfService")
public class SFServiceImpl implements SFService {
    
    @Override
    public SResponse orderService(SOrder order) {
        SRequest request = new SRequest();
        request.setService("OrderService");
        request.setHead(HEAD);
        SBody body = new SBody();
        body.setOrder(order);
        request.setBody(body);
        
        String xml = JaxbUtil.convertToXml(request);
        String checkword = xml + CHECKWORD;
        String verifyCode = new String(Coder.encryptBASE64(Coder.encryptMD5(checkword)));
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", verifyCode);
        paramMap.put("xml", xml);
        
        String reuslt = HttpUtils.httpsPost(URL, paramMap);
        System.out.println(reuslt);
        
        return JaxbUtil.converyToJavaBean(reuslt, SResponse.class);
    }
    
}

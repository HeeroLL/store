package com.isell.ei.logistics.chongqing.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.JaxbUtil;
import com.isell.ei.logistics.chongqing.bean.Goods;
import com.isell.ei.logistics.chongqing.bean.Order;
import com.isell.ei.logistics.chongqing.bean.PayInfo;
import com.isell.ei.logistics.chongqing.service.ChongqingService;
import com.isell.ei.logistics.chongqing.util.HttpRequest;

@Service("chongqingService")
public class ChongqingServiceImpl implements ChongqingService {
    
    @Override
    public String sendOrder(Order order) {
        String data = JaxbUtil.convertToXml(order);
        data = data.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        data = Coder.encryptBASE64(data);
        try {
            data = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = HttpRequest.sendPost("http://113.204.136.28/KJClientReceiver/Data.aspx", "data=" + data);
        System.out.println(result);
        return result;
    }
    
    @Override
    public String goodsRecord(Goods goods) {
        String data = JaxbUtil.convertToXml(goods);
        data = data.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        data = Coder.encryptBASE64(data);
        try {
            data = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = HttpRequest.sendPost("http://113.204.136.28/KJClientReceiver/Data.aspx", "data=" + data);
        System.out.println(result);
        return result;
    }
    
    @Override
    public String goodsRecord(PayInfo payInfo) {
        String data = JaxbUtil.convertToXml(payInfo);
        data = data.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        data = Coder.encryptBASE64(data);
        try {
            data = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = HttpRequest.sendPost("http://113.204.136.28/KJClientReceiver/Data.aspx", "data=" + data);
        System.out.println(result);
        return result;
    }
    
}

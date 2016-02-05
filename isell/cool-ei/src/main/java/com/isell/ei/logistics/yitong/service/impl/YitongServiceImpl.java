package com.isell.ei.logistics.yitong.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.ei.logistics.yitong.bean.YitongRequestRow;
import com.isell.ei.logistics.yitong.bean.YitongRequestRows;
import com.isell.ei.logistics.yitong.bean.YitongResponse;
import com.isell.ei.logistics.yitong.service.YitongService;

/**
 * 易通服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年12月4日]
 */
@Service("yitongService")
public class YitongServiceImpl implements YitongService {
    
    @Override
    public YitongResponse sendOrder(YitongRequestRows yitongRequestRows) {
        for (YitongRequestRow row : yitongRequestRows.getRows()) {
            row.setCheckData(Coder.encodeMd5(row.getStoCustomerID() + row.getOrderNo() + "@Sto.2014"));
        }
        String xml = JaxbUtil.convertToXml(yitongRequestRows);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("message", xml);
        
        String result = HttpUtils.httpPostGBK(URL, paramMap);
        return JaxbUtil.converyToJavaBean(result, YitongResponse.class);
    }
    
}

package com.isell.ei.logistics.zyb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.zyb.bean.ZybResult;
import com.isell.ei.logistics.zyb.service.ZybService;

/**
 * 转运帮接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年10月27日]
 */
@Service("zybService")
public class ZybServiceImpl implements ZybService {
    
    @Override
    public ZybResult queryStatus(String content) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("user", USER);
        paramMap.put("content", Coder.encryptBASE64(content));
        
        // 遍历排序后的字典，将所有参数按 "key=value" 格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        basestring.append("content=").append(paramMap.get("content"));
        basestring.append("user=").append(USER);
        basestring.append(SECRET_KEY);
        paramMap.put("sign", Coder.encodeMd5(basestring.toString()).toLowerCase());
        
        String result = HttpUtils.httpPost(QUERY, paramMap);
        
        return JsonUtil.readValue(result, ZybResult.class);
    }
    
}

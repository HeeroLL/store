package com.isell.ei.logistics.kuaidi100.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.kuaidi100.service.KuaidiService;
import com.isell.ei.logistics.kuaidi100.vo.ResultInfo;

/**
 * 快递100查询接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年8月9日]
 */
@Service("kuaidiService")
public class KuaidiServiceImpl implements KuaidiService {
    /**
     * 快递公司map集合
     */
    private Map<String, String> comMap;
    
    /**
     * 默认构造函数
     */
    public KuaidiServiceImpl() {
        comMap = new HashMap<String, String>();
        comMap.put("yuantong", "yuantong");
        comMap.put("yunda", "yunda");
        comMap.put("huitongkuaidi", "huitongkuaidi");
        comMap.put("zhongtong", "zhongtong");
        comMap.put("shentong", "shentong");
        comMap.put("ems", "ems");
        comMap.put("中通速递", "zhongtong");
        comMap.put("邮政速递", "ems");
        comMap.put("圆通速递", "yuantong");
        comMap.put("韵达快运", "yunda");
        comMap.put("汇通快运", "huitongkuaidi");
        comMap.put("申通快递", "shentong");
    }
    
    @Override
    public JsonData jsonService(String com, String nu) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("id", KEY);
        paramMap.put("com", comMap.get(com));
        paramMap.put("nu", nu);
        
        String result = HttpUtils.httpPost(API_URL, paramMap);
        ResultInfo resultInfo = JsonUtil.readValue(result, ResultInfo.class);
        
        JsonData jsonData = new JsonData();
        if ("ok".equalsIgnoreCase(resultInfo.getMessage())) {
            jsonData.setData(resultInfo.getData());
        } else {
            jsonData.setSuccess(false);
            jsonData.setMsg(resultInfo.getMessage());
        }
        return jsonData;
    }

    @Override
    public String webService(String com, String nu) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key", KEY);
        paramMap.put("com", comMap.get(com));
        paramMap.put("nu", nu);
        
        return HttpUtils.httpPost(WEB_URL, paramMap);
    }

    @Deprecated
    @Override
    public String wapService(String type, String postid, String callbackurl) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("type", type);
        paramMap.put("postid", postid);
        paramMap.put("callbackurl", callbackurl);
        
        return HttpUtils.httpPost(WAP_URL, paramMap);
    }
    
}

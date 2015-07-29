package com.sell.ei.weixinop.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sell.core.util.Coder;
import com.sell.core.util.HttpUtils;
import com.sell.core.util.Identities;
import com.sell.core.util.JsonUtil;
import com.sell.ei.weixinop.bean.WeixinConfig;
import com.sell.ei.weixinop.bean.WeixinTocken;
import com.sell.ei.weixinop.service.WeixinopService;

/**
 * 微信开放平台（公众号）开发服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
@Service("weixinopService")
public class WeixinopServiceImpl implements WeixinopService {
    private static final Logger log = Logger.getLogger(WeixinopServiceImpl.class);
    
    @Override
    public WeixinTocken getTicket() {
        // TODO : 非付费版公众号要缓存token，因为有调用次数
        String result = HttpUtils.httpsGet(GET_TOKEN_URL);
        WeixinTocken weixinTocken = JsonUtil.readValue(result, WeixinTocken.class);
        
        if (StringUtils.isEmpty(weixinTocken.getAccessToken())) {
            throw new RuntimeException(weixinTocken.getErrmsg());
        }
        // 请求微信获得ticket
        result = HttpUtils.httpsGet(GET_TICKET_URL + weixinTocken.getAccessToken());
        return JsonUtil.readValue(result, WeixinTocken.class);
    }
    
    @Override
    public WeixinConfig getWeixinConfig(String url) {
        WeixinConfig weixinConfig = new WeixinConfig();
        weixinConfig.setAppId(APPID);
        weixinConfig.setNonceStr(Identities.uuid());
        weixinConfig.setTimestamp(System.currentTimeMillis() / 1000 + "");
        // 获取tocken
        WeixinTocken tocken = getTicket();
        if (StringUtils.isEmpty(tocken.getTicket())) {
            throw new RuntimeException(tocken.getErrmsg());
        }
        // 生成签名
        StringBuilder builder = new StringBuilder();
        builder.append("jsapi_ticket=").append(tocken.getTicket());
        builder.append("&noncestr=").append(weixinConfig.getNonceStr());
        builder.append("&timestamp=").append(weixinConfig.getTimestamp());
        builder.append("&url=").append(url);
        log.debug(builder.toString());
        weixinConfig.setSignature(Coder.encodeSHA1(builder.toString()));
        
        return weixinConfig;
    }
}

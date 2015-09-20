package com.isell.ei.weixinop.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.isell.cache.service.JVMCacheService;
import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.Identities;
import com.isell.core.util.JsonUtil;
import com.isell.ei.weixinop.bean.WeixinConfig;
import com.isell.ei.weixinop.bean.WeixinTocken;
import com.isell.ei.weixinop.service.WeixinopService;

/**
 * 微信开放平台（公众号）开发服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月29日]
 */
@Service("weixinopService")
public class WeixinopServiceImpl implements WeixinopService {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(WeixinopServiceImpl.class);
    
    /**
     * 缓存模块
     */
    @Resource
    private JVMCacheService jvmCacheService;
    
    @Override
    public String getTicket() {
        // 先从缓存中取key
        String ticket = jvmCacheService.get(WEIXIN_TICKET_KEY);
        if (ticket != null) {
            return ticket;
        }
        String result = HttpUtils.httpsGet(GET_TOKEN_URL);
        WeixinTocken weixinTocken = JsonUtil.readValue(result, WeixinTocken.class);
        
        if (StringUtils.isEmpty(weixinTocken.getAccessToken())) {
            throw new RuntimeException(weixinTocken.getErrmsg());
        }
        // 请求微信获得ticket
        result = HttpUtils.httpsGet(GET_TICKET_URL + weixinTocken.getAccessToken());
        weixinTocken = JsonUtil.readValue(result, WeixinTocken.class);
        if (StringUtils.isEmpty(weixinTocken.getTicket())) {
            throw new RuntimeException(weixinTocken.getErrmsg());
        }
        // 放入缓存 公众号要缓存ticket，因为接口有调用次数限制
        // 默认有效期2小时，这里缓存1小时
        jvmCacheService.set(WEIXIN_TICKET_KEY, weixinTocken.getTicket(), weixinTocken.getExpiresIn() / 2);
        return weixinTocken.getTicket();
    }
    
    @Override
    public WeixinConfig getWeixinConfig(String url) {
        WeixinConfig weixinConfig = new WeixinConfig();
        weixinConfig.setAppId(APPID);
        weixinConfig.setNonceStr(Identities.uuid());
        weixinConfig.setTimestamp(System.currentTimeMillis() / 1000 + "");
        // 生成签名
        StringBuilder builder = new StringBuilder();
        builder.append("jsapi_ticket=").append(getTicket()); // 获取ticket
        builder.append("&noncestr=").append(weixinConfig.getNonceStr());
        builder.append("&timestamp=").append(weixinConfig.getTimestamp());
        builder.append("&url=").append(Coder.encodeUrl(url));
        log.debug(builder.toString());
        weixinConfig.setSignature(Coder.encodeSHA1(builder.toString()));
        log.debug("signature=" + weixinConfig.getSignature());
        
        return weixinConfig;
    }
    
    @Override
    public String getAuthCode(String redirectUrl) {
        StringBuilder builder = new StringBuilder();
        builder.append("<script>window.location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        builder.append(APPID);
        builder.append("&redirect_uri=");
        builder.append(Coder.encodeUrl(redirectUrl));
        builder.append("&response_type=code&scope=snsapi_base#wechat_redirect'</script>");
        
        return builder.toString();
    }
    
    @Override
    public WeixinTocken getOpenid(String code) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        builder.append(APPID);
        builder.append("&secret=");
        builder.append(SECRET);
        builder.append("&code=");
        builder.append(code);
        builder.append("&grant_type=authorization_code");
        
        String result = HttpUtils.httpsGet(builder.toString());
        
        return JsonUtil.readValue(result, WeixinTocken.class);
    }
}

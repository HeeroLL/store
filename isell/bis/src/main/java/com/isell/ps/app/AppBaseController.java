package com.isell.ps.app;

import javax.annotation.Resource;

import com.isell.bis.sys.BisException;
import com.isell.cache.service.JVMCacheService;
import com.isell.core.config.BisConfig;
import com.isell.service.member.vo.UserInfo;
import com.isell.service.shop.vo.CoonShop;

/**
 * app的controller父类
 * 
 * @author lilin
 * @version [版本号, 2015年11月10日]
 */
public abstract class AppBaseController {
    /**
     * JVM缓存服务接口
     */
    @Resource
    protected JVMCacheService jvmCacheService;
    
    /**
     * 系统配置信息
     */
    @Resource
    private BisConfig config;
    
    /**
     * 获取用户信息
     *
     * @param accessCode 接入码
     * @return 用户信息
     */
    protected UserInfo getUserInfo(String accessCode) {
        String key = "user_" + accessCode;
        UserInfo userInfo = jvmCacheService.get(key, UserInfo.class);
        if (userInfo == null) {
            throw new BisException("-2", "exception.access.login-invalidate"); // -2：登录失效
        }
        // 续期
        jvmCacheService.expire(key, config.getUserCacheTime());
        return userInfo;
    }
    
    /**
     * 获取店铺信息
     *
     * @param accessCode 接入码
     * @return 店铺信息
     */
    protected CoonShop getShopInfo(String accessCode) {
        String key = "shop_" + accessCode;
        CoonShop shopInfo = jvmCacheService.get(key, CoonShop.class);
        if (shopInfo == null) {
            throw new BisException("-2", "exception.access.login-invalidate"); // -2：登录失效
        }
        // 续期
        jvmCacheService.expire(key, config.getUserCacheTime());
        return shopInfo;
    }
}

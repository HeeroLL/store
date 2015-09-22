package com.heero.redis.main.service;

import java.util.Set;

import com.heero.redis.user.po.UserInfo;

/**
 * 主页面service层接口
 * 
 * @author lilin
 * @version [1.0, 2014年11月6日]
 */
public interface MainService {
    /**
     * 获取在线用户
     * @return 在线用户列表
     */
    Set<UserInfo> getOnlineUser();
}

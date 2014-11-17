package com.heero.redis.user.service;

import com.heero.redis.user.po.UserInfo;
import com.heero.redis.user.vo.ResultMessageVo;

/**
 * 用户service层接口
 * 
 * @author lilin
 * @version [1.0, 2014年11月2日]
 */
public interface UserService {
    /**
     * 用户注册
     * 
     * @param userInfo 用户信息
     * @return 注册是否成功
     */
    ResultMessageVo reg(UserInfo userInfo);

    /**
     * 用户登录
     * 
     * @param userInfo 用户信息
     * @return 登录是否成功
     */
    ResultMessageVo login(UserInfo userInfo);

    /**
     * 用户注销
     * 
     * @param sessionId cookie中存储的sessionId
     * @return 注销是否成功
     */
    ResultMessageVo logout(String sessionId);
    
    /**
     * 根据用户id获取用户信息
     * 
     * @param userId 用户id
     * @return 用户信息
     */
    UserInfo getUserInfoById(String userId);
    
    /**
     * 根据sessionId获取用户信息
     * 
     * @param sessionId 会话id
     * @return 用户信息
     */
    UserInfo getUserInfoBySessionId(String sessionId);
}

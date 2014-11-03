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
     * @param userInfo 用户信息 
     * @return 注册是否成功
     */
    ResultMessageVo reg(UserInfo userInfo);
    
    /**
     * 用户登录
     * @param userInfo 用户信息
     * @return 登录是否成功
     */
    ResultMessageVo login(UserInfo userInfo);
    
}

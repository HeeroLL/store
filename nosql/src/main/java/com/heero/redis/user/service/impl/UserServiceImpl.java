package com.heero.redis.user.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.heero.redis.user.po.UserInfo;
import com.heero.redis.user.service.UserService;
import com.heero.redis.user.vo.ResultMessageVo;

/**
 * 用户service层实现类
 * 
 * @author lilin
 * @version [1.0, 2014年11月2日]
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * redisTemplate
     */
    @Resource
    private RedisTemplate<String, UserInfo> redisTemplate;

    /**
     * stringRedisTemplate
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultMessageVo reg(UserInfo userInfo) {
        ResultMessageVo result = new ResultMessageVo();
        // 判断用户名是否存在
        boolean flag = stringRedisTemplate.hasKey("user:username:" + userInfo.getUserName());
        if (flag) {
            result.setSuccess(false);
            result.setErrMsg("UserName already exists!");
            return result;
        }

        // 判断昵称是否存在
        flag = stringRedisTemplate.hasKey("user:nickname:" + userInfo.getNickname());
        if (flag) {
            result.setSuccess(false);
            result.setErrMsg("Nickname already exists!");
            return result;
        }
        // 开启事务
        // stringRedisTemplate.multi();
        // 设置用户id
        userInfo.setUserId(stringRedisTemplate.boundValueOps("user:id:count").increment(1));

        stringRedisTemplate.boundValueOps("user:username:" + userInfo.getUserName()).set(
                String.valueOf(userInfo.getUserId()));
        stringRedisTemplate.boundValueOps("user:nickname:" + userInfo.getNickname()).set(
                String.valueOf(userInfo.getUserId()));

        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userName", userInfo.getUserName());
        userMap.put("nickname", userInfo.getNickname());
        userMap.put("password", userInfo.getPassword());
        userMap.put("sex", userInfo.getSex());
        userMap.put("email", userInfo.getEmail());
        stringRedisTemplate.boundHashOps("users:" + userInfo.getUserId()).putAll(userMap);

        // 提交事务
        // stringRedisTemplate.exec();

        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultMessageVo login(UserInfo userInfo) {
        ResultMessageVo result = new ResultMessageVo();
        String id = stringRedisTemplate.boundValueOps("user:username:" + userInfo.getUserName()).get();
        if (id == null) {
            result.setSuccess(false);
            result.setErrMsg("User not exists!");
            return result;
        }

        if (!stringRedisTemplate.hasKey("users:" + id)) {
            result.setSuccess(false);
            result.setErrMsg("User not exists!");
            return result;
        }
        BoundHashOperations<String, String, Object> hash = stringRedisTemplate.boundHashOps("users:" + id);
        if (!userInfo.getUserName().equals(hash.get("userName"))
                || !userInfo.getPassword().equals(hash.get("password"))) {
            result.setSuccess(false);
            result.setErrMsg("UserName or password is not correct!");
            return result;
        }
        // 存入session
        // stringRedisTemplate.boundListOps("user:online").leftPush(userInfo.getSessionid());
        String onlineUserKey = "user:online:" + userInfo.getSessionid();
        stringRedisTemplate.boundValueOps(onlineUserKey).set(id);
        // 设置超时时间
        stringRedisTemplate.expire(onlineUserKey, UserInfo.SESSION_TIME, TimeUnit.SECONDS);

        result.setSuccess(true);
        return result;
    }

    /**
     * 用户注销
     * 
     * @param sessionId cookie中存储的sessionId
     * @return 注销是否成功
     */
    @Override
    public ResultMessageVo logout(String sessionId) {
        ResultMessageVo result = new ResultMessageVo();
        if (sessionId != null) {
            String onlineUserKey = "user:online:" + sessionId;
            // 删除缓存数据
            stringRedisTemplate.delete(onlineUserKey);
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据用户id获取用户信息
     * 
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public UserInfo getUserInfoById(String userId) {
        UserInfo userInfo = null;

        BoundHashOperations<String, String, String> hash = stringRedisTemplate.boundHashOps("users:" + userId);
        if (hash != null) {
            userInfo = new UserInfo();
            userInfo.setUserId(Long.parseLong(userId));
            userInfo.setUserName(hash.get("userName"));
            userInfo.setNickname(hash.get("nickname"));
            userInfo.setSex(hash.get("sex"));
            userInfo.setEmail(hash.get("email"));
        }
        return userInfo;
    }
    
    /**
     * 根据sessionId获取用户信息
     * 
     * @param sessionId 会话id
     * @return 用户信息
     */
    @Override
    public UserInfo getUserInfoBySessionId(String sessionId) {
        String onlineUserKey = "user:online:" + sessionId;
        BoundValueOperations<String, String> hash = stringRedisTemplate.boundValueOps(onlineUserKey);
        if (hash != null) {
            return getUserInfoById(hash.get());
        }
        
        return null;
    }

}

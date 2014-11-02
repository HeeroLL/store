package com.heero.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.heero.user.po.UserInfo;
import com.heero.user.service.UserService;
import com.heero.user.vo.ResultMessageVo;

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
        //stringRedisTemplate.multi();
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
        //stringRedisTemplate.exec();

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

        result.setSuccess(true);
        return result;
    }

}

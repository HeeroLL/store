package com.heero.redis.main.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.heero.redis.main.service.MainService;
import com.heero.redis.user.po.UserInfo;

/**
 * 主页面service层接口实现类
 * 
 * @author lilin
 * @version [1.0, 2014年11月6日]
 */
@Service("mainService")
public class MainServiceImpl implements MainService {

    /**
     * stringRedisTemplate
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public Set<UserInfo> getOnlineUser() {
        Set<String> keySet = stringRedisTemplate.keys("user:online:*");
        //System.out.println(keySet.size());
        Set<UserInfo> userInfoSet = new HashSet<UserInfo>(keySet.size());
        for (String key : keySet) {
            // 获取用户id
            String userId = stringRedisTemplate.boundValueOps(key).get();
            
            BoundHashOperations<String, String, String> oper = stringRedisTemplate.boundHashOps("users:" + userId);
            
            UserInfo user = new UserInfo();
            user.setUserId(Long.parseLong(userId));
            user.setUserName(oper.get("userName"));
            user.setNickname(oper.get("nickname"));
            user.setSex(oper.get("sex"));
            user.setEmail(oper.get("email"));
            
            userInfoSet.add(user);
        }
        return userInfoSet;
    }

}

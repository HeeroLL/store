package com.zebone.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebone.security.UserDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-redis.xml"})
public class RedisServiceImplTest {
    
    @Resource
    private RedisService<UserDetails> redisService;
    
    @Test
    public void testGetStr() {
        System.out.println(redisService.getStr("test.mho.kv.100102101"));
    }
    
    @Test
    public void testGetObj() {
        System.out.println(redisService.getObj("test.mho.obj.100102101", UserDetails.class));
    }
    
    @Test
    public void testHget() {
        System.out.println(redisService.hget("test.mho.hash", "100102101"));
    }
    
    @Test
    public void testHmget() {
        System.out.println(redisService.hmget("test.mho.hash", "100102101", "100105"));
    }
    
    @Test
    public void testHgetAll() {
        System.out.println(redisService.hgetAll("test.mho.hash"));
    }
    
    @Test
    public void testLpushStringStringArray() {
    }
    
    @Test
    public void testRpushStringStringArray() {
    }
    
    @Test
    public void testLpushStringObjectArray() {
    }
    
    @Test
    public void testRpushStringObjectArray() {
    }
    
    @Test
    public void testLpopStr() {
    }
    
    @Test
    public void testRpopStr() {
    }
    
    @Test
    public void testLpopObj() {
    }
    
    @Test
    public void testRpopObj() {
    }
    
    @Test
    public void testLrangeObj() {
    }
    
    @Test
    public void testLrangeStr() {
    }
    
}

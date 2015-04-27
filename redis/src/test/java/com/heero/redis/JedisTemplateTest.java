package com.heero.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring3-mvc.xml"})
public class JedisTemplateTest {
    
    @Autowired
    private JedisTemplate<?> jedisTemplate;
    
    @Test
    public void testSetStringString() {
        jedisTemplate.set("testKey", "testValue");
    }
    
    @Test
    public void testSetStringObject() {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        jedisTemplate.set("testList", list);
    }
    
    @Test
    public void testGetStr() {
        System.out.println(jedisTemplate.getStr("testKey"));
    }
    
    @Test
    public void testGet() {
        System.out.println(jedisTemplate.getObj("testList"));
    }
    
    @Test
    public void testHset() {
        
    }
    
    @Test
    public void testHget() {
        
    }
    
    @Test
    public void testHmget() {
        
    }
    
    @Test
    public void testHgetAll() {
        
    }
    
    @Test
    public void testExecute() {
        
    }
    
}
